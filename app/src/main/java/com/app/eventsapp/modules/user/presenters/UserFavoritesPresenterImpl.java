package com.app.eventsapp.modules.user.presenters;

import com.app.eventsapp.modules.user.rest.UserDataService;
import com.app.eventsapp.modules.user.rest.response.FavoritesResponse;
import com.app.eventsapp.modules.user.rest.response.ResponseStatus;
import com.app.eventsapp.modules.user.util.UserDataManager;
import com.app.eventsapp.modules.user.views.UserFavoritesView;
import com.app.eventsapp.rest.request.RequestListener;

import org.apache.commons.lang3.StringUtils;

import retrofit2.Call;
import retrofit2.Response;

/**
 * Created by Grigory Kalyashov on 23.02.2017.
 */
public class UserFavoritesPresenterImpl implements UserFavoritesPresenter
{
    private UserFavoritesView view;
    private UserDataService userDateService = new UserDataService();

    @Override
    public void init(UserFavoritesView view)
    {
        this.view = view;
    }

    @Override
    public void onResume(UserDataManager sessionManager)
    {
        sendFavoritesRequest(sessionManager);
    }

    private void sendFavoritesRequest(final UserDataManager sessionManager)
    {
        String token = sessionManager.getUserToken();

        if(!StringUtils.isEmpty(token))
        {
            view.showProgressBar();

            userDateService.getFavorites(token, new RequestListener<FavoritesResponse>()
            {
                @Override
                public void onSuccess(Call<FavoritesResponse> call, Response<FavoritesResponse> response)
                {
                    FavoritesResponse favoritesResponse = response.body();

                    String status = favoritesResponse.getStatus();

                    if(status.equals(ResponseStatus.SUCCESS))
                    {
                        view.setRecyclerViewAdapter(favoritesResponse.getFavorites());
                    }
                    else
                    {
                        view.onErrorLoading();
                    }

                    view.hideProgressBar();
                }

                @Override
                public void onErrorResponse(Call<FavoritesResponse> call, Response<FavoritesResponse> response)
                {
                    view.onErrorLoading();
                    view.hideProgressBar();
                }

                @Override
                public void onFailure(Call<FavoritesResponse> call, Throwable t)
                {
                    view.onErrorLoading();
                    view.hideProgressBar();
                }
            });
        }
        else
        {
            view.showNeedAuthMessage();
        }
    }
}
