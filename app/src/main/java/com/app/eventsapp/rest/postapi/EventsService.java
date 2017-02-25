package com.app.eventsapp.rest.postapi;

import com.app.eventsapp.core.cache.PostCacheUtils;
import com.app.eventsapp.modules.auth.rest.request.Token;
import com.app.eventsapp.modules.auth.rest.response.SimpleResponse;
import com.app.eventsapp.modules.postline.models.Post;
import com.app.eventsapp.rest.base.RestService;
import com.app.eventsapp.rest.request.RequestCallback;
import com.app.eventsapp.rest.request.RequestListener;

import java.util.List;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Response;

/**
 * Created by Grigory Kalyashov on 04.11.2016.
 *
 * Сервис для получения постов (событий)
 */
public class EventsService extends RestService
{
    @Inject
    public EventsService()
    {}

    public void getPost(long id, final RequestListener<Post> requestListener)
    {
        getPostById(requestListener, id);
    }

    /**
     * @return список постов
     */
    public void getPosts(final RequestListener<List<Post>> requestListener,
                         int offset, int count)
    {
        sendPostRequest(requestListener, offset, count);
    }

    public void rateUp(String token, RequestListener<SimpleResponse> requestListener)
    {
        EventsAPI eventsAPI = buildRetrofit().create(EventsAPI.class);
        Call<SimpleResponse> call = eventsAPI.rateUp(new Token(token));
        call.enqueue(new RequestCallback<>(requestListener));
    }

    public void rateDown(String token, RequestListener<SimpleResponse> requestListener)
    {
        EventsAPI eventsAPI = buildRetrofit().create(EventsAPI.class);
        Call<SimpleResponse> call = eventsAPI.rateDown(new Token(token));
        call.enqueue(new RequestCallback<>(requestListener));
    }

    private void sendPostRequest(RequestListener<List<Post>> requestListener,
                                 int offset, int count)
    {
        EventsAPI eventsAPI = buildRetrofit(PostJsonBuilder.buildPostGson()).create(EventsAPI.class);
        Call<List<Post>> call = eventsAPI.getPosts(offset, count);
        call.enqueue(new PostListRequestCallback<>(requestListener));
    }

    private void getPostById(RequestListener<Post> requestListener, long id)
    {
        EventsAPI eventsAPI = buildRetrofit(PostJsonBuilder.buildPostGson()).create(EventsAPI.class);
        Call<Post> call = eventsAPI.getPost(id);
        call.enqueue(new PostRequestCallback<>(requestListener));
    }

    private class PostListRequestCallback<T> extends RequestCallback<T>
    {

        PostListRequestCallback(RequestListener<T> listener)
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

    private class PostRequestCallback<T> extends RequestCallback<T>
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
                PostCacheUtils.addPostToCache((Post) response.body());
            }

        }
    }
}
