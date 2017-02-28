package com.app.eventsapp.modules.auth.presenters;

import com.app.eventsapp.modules.auth.rest.UserService;
import com.app.eventsapp.modules.auth.rest.response.FavoritesResponse;
import com.app.eventsapp.modules.auth.rest.response.ResponseStatus;
import com.app.eventsapp.modules.auth.session.UserSessionManager;
import com.app.eventsapp.modules.auth.views.UserFavoritesView;
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
    private UserService userService = new UserService();

    @Override
    public void init(UserFavoritesView view)
    {
        this.view = view;
    }

    @Override
    public void onResume(UserSessionManager sessionManager)
    {
        view.showProgressBar();
        sendFavoritesRequest(sessionManager);
    }

    private void sendFavoritesRequest(final UserSessionManager sessionManager)
    {
        String token = sessionManager.getUserToken();

        if(!StringUtils.isEmpty(token))
        {
            userService.getFavorites(token, new RequestListener<FavoritesResponse>()
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
    }
}
