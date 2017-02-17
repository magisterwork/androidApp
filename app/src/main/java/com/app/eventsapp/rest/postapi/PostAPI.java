package com.app.eventsapp.rest.postapi;

import com.app.eventsapp.modules.postline.models.Post;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by Grigory Kalyashov on 04.11.2016.
 *
 * Интерфейс для получения событий
 */
public interface PostAPI
{
    /**
     * Получить список событий
     * @param offset сдвиг
     * @param count количество событий
     * @return список
     */
    @GET("events/list")
    Call<List<Post>> getPosts(@Query("offset") int offset, @Query("count") int count);

    /**
     * @param id - id события
     * @return пост
     */
    @GET("get/{id}")
    Call<Post> getPost(@Path("id") Long id);
}