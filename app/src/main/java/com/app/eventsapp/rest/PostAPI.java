package com.app.eventsapp.rest;

import com.app.eventsapp.modules.postline.models.Post;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by Grigory Kalyashov on 04.11.2016.
 *
 * Интерфейс для получения событий
 */
public interface PostAPI
{
    /**
     * @return список постов
     */
    @GET("/events/api/list")
    Call<List<Post>> getPosts();
}