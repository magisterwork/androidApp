package com.app.eventsapp.rest;

import com.app.eventsapp.modules.postline.models.Post;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

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
    @GET("events/api/list")
    Call<List<Post>> getPosts();

    /**
     * Получить список событий
     * @param offset сдвиг
     * @param count количество событий
     * @return список
     */
    @GET("events/api/list?offset={offset}&count={count}")
    Call<List<Post>> getPosts(@Path("offset") int offset, @Path("count") int count);

    /**
     * @param id - id события
     * @return пост
     */
    @GET("events/api/get/{id}")
    Call<Post> getPost(@Path("id") Long id);
}