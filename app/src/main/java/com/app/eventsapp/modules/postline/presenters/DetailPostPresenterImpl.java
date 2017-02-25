package com.app.eventsapp.modules.postline.presenters;

import android.app.Dialog;
import android.content.Context;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.app.eventsapp.R;
import com.app.eventsapp.core.cache.PostCacheUtils;
import com.app.eventsapp.core.managers.PicassoImageManager;
import com.app.eventsapp.modules.auth.rest.UserService;
import com.app.eventsapp.modules.auth.rest.request.FavoriteRq;
import com.app.eventsapp.modules.auth.rest.response.ResponseStatus;
import com.app.eventsapp.modules.auth.rest.response.SimpleResponse;
import com.app.eventsapp.modules.auth.session.UserSessionManager;
import com.app.eventsapp.modules.postline.models.Post;
import com.app.eventsapp.modules.postline.views.DetailPostFragmentView;
import com.app.eventsapp.rest.postapi.EventsService;
import com.app.eventsapp.rest.request.RequestListener;
import com.app.eventsapp.utils.NetworkUtil;
import com.squareup.picasso.Picasso;

import org.apache.commons.lang3.StringUtils;

import javax.inject.Inject;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Response;
import uk.co.senab.photoview.PhotoView;

/**
 * Created by Grigory Kalyashov on 12.02.2017.
 */
public class DetailPostPresenterImpl implements DetailPostPresenter
{
    private DetailPostFragmentView view;

    private UserService userService = new UserService();
    private EventsService eventsService = new EventsService();
    private UserSessionManager sessionManager;

    @Inject
    public DetailPostPresenterImpl()
    {

    }

    @Override
    public void init(DetailPostFragmentView view)
    {
        this.view = view;
    }

    @Override
    public void showFullEventImage(Context context, String fullImageUrl)
    {
        if(NetworkUtil.isInternetAvailable(context) && !StringUtils.isEmpty(fullImageUrl))
        {
            //TODO можно использовать DialogFragment, чтобы окно не пропадало после смены ориентации
            final Dialog fullImageDialog = new Dialog(context,
                    android.R.style.Theme_Black_NoTitleBar);

            fullImageDialog.setContentView(R.layout.full_image_dialog);

            Button btnClose = (Button) fullImageDialog.findViewById(R.id.btnIvClose);
            PhotoView fullImage = (PhotoView) fullImageDialog.findViewById(R.id.detail_full_image);

            PicassoImageManager.getInstance().loadResource(fullImageUrl,
                    fullImage, Picasso.Priority.HIGH);

            btnClose.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View arg0) {

                    fullImageDialog.dismiss();
                }
            });

            fullImageDialog.show();
        }
    }

    @Override
    public void getPost(long id)
    {
        Post postFromCache = PostCacheUtils.getPostFromCache(id);

        if(postFromCache != null)
        {
            view.setCurrentPost(postFromCache);
            view.setPostDetails(postFromCache);
        }
        else
        {
            eventsService.getPost(id, new RequestListener<Post>()
            {
                @Override
                public void onSuccess(Call<Post> call, Response<Post> response)
                {
                    Post post = response.body();

                    if(post != null)
                    {
                        view.setCurrentPost(post);
                        view.setPostDetails(post);
                    }
                    else
                    {
                        view.onFailureGetPost();
                    }
                }

                @Override
                public void onErrorResponse(Call<Post> call, Response<Post> response)
                {
                    view.onFailureGetPost();
                }

                @Override
                public void onFailure(Call<Post> call, Throwable t)
                {
                    view.onFailureGetPost();
                }
            });
        }
    }

    public void saveToFavorites(Long eventId, final UserSessionManager sessionManager)
    {
        String userToken = sessionManager.getUserToken();

        userService.addFavorite(new FavoriteRq(userToken, eventId), new RequestListener<SimpleResponse>()
        {
            @Override
            public void onSuccess(Call<SimpleResponse> call, Response<SimpleResponse> response)
            {
                SimpleResponse rs = response.body();

                if(rs.getStatus().equals(ResponseStatus.SUCCESS))
                {
                    sessionManager.saveUserToken(rs.getToken());
                    view.onSuccessfulAddToFavorites();
                }
                else
                {
                    view.onUnsuccessfulAddToFavorites();
                }

            }

            @Override
            public void onErrorResponse(Call<SimpleResponse> call, Response<SimpleResponse> response)
            {
                view.onUnsuccessfulAddToFavorites();
            }

            @Override
            public void onFailure(Call<SimpleResponse> call, Throwable t)
            {
                view.onUnsuccessfulAddToFavorites();
            }
        });
    }
}
