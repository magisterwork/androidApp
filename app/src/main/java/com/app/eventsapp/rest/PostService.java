package com.app.eventsapp.rest;

import com.app.eventsapp.modules.postline.models.Post;

import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Grigory Kalyashov on 04.11.2016.
 *
 * Сервис для получения постов (событий)
 */
public class PostService
{
    private static final String SERVER_END_POINT = "http://webapp.bbifqmci6u.us-west-2.elasticbeanstalk.com/";

    /**
     * @return список постов
     */
    public static List<Post> getPosts()
    {
        ExecutorService es = Executors.newSingleThreadExecutor();

        Future<List<Post>> result = es.submit(new Callable<List<Post>>()
        {
            @Override
            public List<Post> call() throws Exception
            {
                return PostService.sendPostsRequest();
            }
        });

        try
        {
            return result.get();
        }
        catch (InterruptedException | ExecutionException e)
        {
            e.printStackTrace();
        }

        return Collections.emptyList();
    }

    private static List<Post> sendPostsRequest()
    {
        try
        {
            PostAPI postAPI = buildRetrofit().create(PostAPI.class);
            Response<List<Post>> response = postAPI.getPosts().execute();
            return response.body();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

        return Collections.emptyList();
    }

    private static Retrofit buildRetrofit()
    {
        return new Retrofit.Builder()
                .baseUrl(SERVER_END_POINT)
                .addConverterFactory(GsonConverterFactory.create(PostJsonBuilder.buildPostGson()))
                .build();
    }
}
