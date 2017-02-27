package com.app.eventsapp.modules.auth.rest;

import com.app.eventsapp.modules.auth.rest.request.FavoriteRq;

import com.app.eventsapp.modules.auth.rest.request.IsFavoriteRq;
import com.app.eventsapp.modules.auth.rest.request.Token;
import com.app.eventsapp.modules.auth.rest.response.FavoritesResponse;
import com.app.eventsapp.modules.auth.rest.response.IsFavoriteResponse;
import com.app.eventsapp.modules.auth.rest.response.SimpleResponse;
import com.app.eventsapp.rest.base.RestService;
import com.app.eventsapp.rest.postapi.PostJsonBuilder;
import com.app.eventsapp.rest.request.RequestCallback;
import com.app.eventsapp.rest.request.RequestListener;

import javax.inject.Inject;

import okhttp3.ResponseBody;
import retrofit2.Call;

/**
 * Created by Grigory Kalyashov on 23.02.2017.
 */

public class UserService extends RestService
{

    @Inject
    public UserService()
    {}

    public void addFavorite(FavoriteRq request, RequestListener<SimpleResponse> requestListener)
    {
        UserAPI userAPI = buildRetrofit().create(UserAPI.class);
        Call<SimpleResponse> call = userAPI.addFavorite(request);
        call.enqueue(new RequestCallback<>(requestListener));
    }

    public void removeFavorite(FavoriteRq request, RequestListener<SimpleResponse> requestListener)
    {
        UserAPI userAPI = buildRetrofit().create(UserAPI.class);
        Call<SimpleResponse> call = userAPI.removeFavorite(request);
        call.enqueue(new RequestCallback<>(requestListener));
    }

    public void getFavorites(String userToken, RequestListener<FavoritesResponse> requestListener)
    {
        UserAPI userAPI = buildRetrofit(PostJsonBuilder.buildPostGson()).create(UserAPI.class);
        Call<FavoritesResponse> call = userAPI.getFavorites(new Token(userToken));
        call.enqueue(new RequestCallback<>(requestListener));
    }

    public void isFavorite(String userToken, long eventId, RequestListener<IsFavoriteResponse> requestListener)
    {
        UserAPI userAPI = buildRetrofit(PostJsonBuilder.buildPostGson()).create(UserAPI.class);
        Call<IsFavoriteResponse> call = userAPI.isFavorite(new IsFavoriteRq(userToken, eventId));
        call.enqueue(new RequestCallback<>(requestListener));
    }
}
