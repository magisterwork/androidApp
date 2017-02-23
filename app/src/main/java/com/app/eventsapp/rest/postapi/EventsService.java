package com.app.eventsapp.rest.postapi;

import com.app.eventsapp.core.cache.PostCacheUtils;
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
    public void getPosts(final RequestListener<List<Post>> requestListener,
                         int offset, int count)
    {
        sendPostRequest(requestListener, offset, count);
    }

    private void sendPostRequest(RequestListener<List<Post>> requestListener,
                                 int offset, int count)
    {
        EventsAPI eventsAPI = buildRetrofit(PostJsonBuilder.buildPostGson()).create(EventsAPI.class);
        Call<List<Post>> call = eventsAPI.getPosts(offset, count);
        call.enqueue(new PostRequestCallback<>(requestListener));
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
                PostCacheUtils.addPostsToCache((List<Post>) response.body());
            }

        }
    }
}
