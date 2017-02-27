package com.app.eventsapp.modules.auth.rest;

import com.app.eventsapp.modules.auth.rest.request.FavoriteRq;
import com.app.eventsapp.modules.auth.rest.request.IsFavoriteRq;
import com.app.eventsapp.modules.auth.rest.request.Token;
import com.app.eventsapp.modules.auth.rest.response.FavoritesResponse;
import com.app.eventsapp.modules.auth.rest.response.IsFavoriteResponse;
import com.app.eventsapp.modules.auth.rest.response.SimpleResponse;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * Created by Grigory Kalyashov on 23.02.2017.
 *
 * API действий пользователя
 */
public interface UserAPI
{
    /**
     * Добавить событие в избранное
     * @param request
     * @return
     */
    @POST("user/favorites/add")
    Call<SimpleResponse> addFavorite(@Body FavoriteRq request);

    /**
     * Удалить событие из избранного
     * @param request
     * @return
     */
    @POST("user/favorites/remove")
    Call<SimpleResponse> removeFavorite(@Body FavoriteRq request);


    /**
     * Список избранных событий
     * @param token
     * @return
     */
    @POST("user/favorites/list")
    Call<FavoritesResponse> getFavorites(@Body Token token);

    /**
     * Проверка находится ли событие в избранном у пользователя
     *
     * @param request
     * @return
     */
    @POST("user/favorites/isFavorite")
    Call<IsFavoriteResponse> isFavorite(@Body IsFavoriteRq request);
}
