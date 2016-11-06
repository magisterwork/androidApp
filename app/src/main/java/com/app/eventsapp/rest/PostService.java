package com.app.eventsapp.rest;

import com.app.eventsapp.modules.postline.models.Post;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Grigory Kalyashov on 04.11.2016.
 *
 * Сервис для получения события
 */
public class PostService
{
    private static final String SERVER_END_POINT = "http://agregator.ymjanqz6py.us-west-2.elasticbeanstalk.com";

    /**
     * @return список постов
     */
    public static List<Post> getPosts()
    {
        try
        {
            PostAPI postAPI = buildRetrofit().create(PostAPI.class);
            Response<List<Post>> response = postAPI.getPosts().execute();
            return response.body();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }

        return Collections.emptyList();
    }

    private static Retrofit buildRetrofit()
    {
        return new Retrofit.Builder()
                .baseUrl(SERVER_END_POINT)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }
}
