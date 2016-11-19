package com.app.eventsapp.rest;

import com.app.eventsapp.core.cache.PostCacheUtils;
import com.app.eventsapp.modules.postline.models.Post;

import java.util.List;

import retrofit2.Call;
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

    private static volatile PostService instance;

    /**
     * @return PostService
     */
    public static PostService getInstance()
    {
        PostService localInstance = instance;

        if(localInstance == null)
        {
            synchronized (PostService.class)
            {
                localInstance = instance;

                if(localInstance == null)
                {
                    instance = localInstance = new PostService();
                }
            }
        }

        return localInstance;
    }

    public Post getPost(Long id)
    {
        Post post = PostCacheUtils.getPostFromCache(id);

        if(post != null)
        {
            return post;
        }
        else
        {
            //TODO иначе тянем с сервера
            return null;
        }
    }

    /**
     * @return список постов
     */
    public void getPosts(final RequestListener<List<Post>> requestListener)
    {
        sendPostsRequest(requestListener);
    }

    private void sendPostsRequest(RequestListener<List<Post>> requestListener)
    {
        try
        {
            PostAPI postAPI = buildRetrofit().create(PostAPI.class);
            Call<List<Post>> call = postAPI.getPosts();
            call.enqueue(new PostRequestCallback<>(requestListener));
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    private static Retrofit buildRetrofit()
    {
        return new Retrofit.Builder()
                .baseUrl(SERVER_END_POINT)
                .addConverterFactory(GsonConverterFactory.create(PostJsonBuilder.buildPostGson()))
                .build();
    }

    private class PostRequestCallback<T> extends  RequestCallback<T>
    {

        PostRequestCallback(RequestListener<T> listener)
        {
            super(listener);
        }

        @Override
        public void onResponse(Call<T> call, Response<T> response)
        {
            super.onResponse(call, response);

            if(response.isSuccessful())
            {
                PostCacheUtils.addPostsToCache((List<Post>) response.body());
            }

        }
    }
}
