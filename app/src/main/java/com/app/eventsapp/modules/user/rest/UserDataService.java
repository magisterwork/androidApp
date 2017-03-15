package com.app.eventsapp.modules.user.rest;

import com.app.eventsapp.modules.user.rest.request.FavoriteRq;

import com.app.eventsapp.modules.user.rest.request.IsFavoriteRq;
import com.app.eventsapp.modules.user.rest.request.Token;
import com.app.eventsapp.modules.user.rest.response.FavoritesResponse;
import com.app.eventsapp.modules.user.rest.response.IsFavoriteResponse;
import com.app.eventsapp.modules.user.rest.response.SimpleResponse;
import com.app.eventsapp.rest.base.RestService;
import com.app.eventsapp.rest.postapi.PostJsonBuilder;
import com.app.eventsapp.rest.request.RequestCallback;
import com.app.eventsapp.rest.request.RequestListener;

import javax.inject.Inject;

import retrofit2.Call;

/**
 * Created by Grigory Kalyashov on 23.02.2017.
 *
 * Сервис для работы с данными пользователя
 */
public class UserDataService extends RestService
{
    @Inject
    public UserDataService()
    {}

    /**
     * Добавить событие в избранное
     *
     * @param request
     * @param requestListener
     */
    public void addFavorite(FavoriteRq request, RequestListener<SimpleResponse> requestListener)
    {
        UserAPI userAPI = buildRetrofit().create(UserAPI.class);
        Call<SimpleResponse> call = userAPI.addFavorite(request);
        call.enqueue(new RequestCallback<>(requestListener));
    }

    /**
     * Удалить событие из избранного
     *
     * @param request - запрос
     * @param requestListener
     */
    public void removeFavorite(FavoriteRq request, RequestListener<SimpleResponse> requestListener)
    {
        UserAPI userAPI = buildRetrofit().create(UserAPI.class);
        Call<SimpleResponse> call = userAPI.removeFavorite(request);
        call.enqueue(new RequestCallback<>(requestListener));
    }

    /**
     * Список избранных событий пользователя
     *
     * @param userToken - токен пользователя
     * @param requestListener
     */
    public void getFavorites(String userToken, RequestListener<FavoritesResponse> requestListener)
    {
        UserAPI userAPI = buildRetrofit(PostJsonBuilder.buildPostGson()).create(UserAPI.class);
        Call<FavoritesResponse> call = userAPI.getFavorites(new Token(userToken));
        call.enqueue(new RequestCallback<>(requestListener));
    }

    /**
     * Проверка находится ли событие в избранном у пользователя
     *
     * @param userToken - токен пользователя
     * @param eventId - идентификатор события
     * @param requestListener
     */
    public void isFavorite(String userToken, long eventId, RequestListener<IsFavoriteResponse> requestListener)
    {
        UserAPI userAPI = buildRetrofit(PostJsonBuilder.buildPostGson()).create(UserAPI.class);
        Call<IsFavoriteResponse> call = userAPI.isFavorite(new IsFavoriteRq(userToken, eventId));
        call.enqueue(new RequestCallback<>(requestListener));
    }
}
