package com.app.eventsapp.rest.postapi;

import com.app.eventsapp.modules.auth.rest.request.Token;
import com.app.eventsapp.modules.auth.rest.response.SimpleResponse;
import com.app.eventsapp.modules.postline.models.Post;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Created by Grigory Kalyashov on 04.11.2016.
 *
 * Интерфейс для получения событий
 */
public interface EventsAPI
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
    @GET("events/get")
    Call<Post> getPost(@Query("id") long id);

    /**
     * Повысить рейтинг события
     * @param token
     * @return
     */
    @POST("events/rateup")
    Call<SimpleResponse> rateUp(@Body Token token);

    /**
     * Понизить рейтинг события
     * @param token
     * @return
     */
    @POST("events/ratedown")
    Call<SimpleResponse> rateDown(@Body Token token);
}