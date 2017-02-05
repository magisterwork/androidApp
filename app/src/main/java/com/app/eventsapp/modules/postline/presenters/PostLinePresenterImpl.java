package com.app.eventsapp.modules.postline.presenters;

import com.app.eventsapp.modules.postline.models.Post;
import com.app.eventsapp.modules.postline.views.PostLineFragmentView;
import com.app.eventsapp.rest.postapi.PostService;
import com.app.eventsapp.rest.request.RequestListener;

import java.util.List;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Response;

/**
 * Created by Grigory Kalyashov on 31.10.2016.
 */
public class PostLinePresenterImpl implements PostLinePresenter
{
    private PostLineFragmentView view;

    private int offset = 0;
    private final int count = 20;
    private int totalItemsCount = 0;

    private PostService postService;

    @Inject
    public PostLinePresenterImpl(PostService postService)
    {
        this.postService = postService;
    }

    @Override
    public void onResume()
    {
        sendPostRequest(offset, count);
    }

    @Override
    public void onPause()
    {

    }

    @Override
    public void onLoadMore()
    {
        if(offset <= totalItemsCount)
        {
            offset+=count;
            sendPostRequest(offset, count);
        }
    }

    @Override
    public void onItemClick(int position)
    {
        view.openPostDetails(position);
    }

    @Override
    public void addPostsToAdapter(List<Post> postList)
    {
        view.addPostsToAdapter(postList);
    }

    @Override
    public void init(PostLineFragmentView view)
    {
        this.view = view;
    }

    private void sendPostRequest(int offset, final int count)
    {
        view.showProgressBar();

        postService.getPosts(new RequestListener<List<Post>>()
        {
            @Override
            public void onSuccess(Call<List<Post>> call, Response<List<Post>> response)
            {
                view.hideProgressBar();
                view.setRecyclerViewAdapter(response.body());
                totalItemsCount += count;
            }

            @Override
            public void onErrorResponse(Call<List<Post>> call, Response<List<Post>> response)
            {
                view.hideProgressBar();
                view.onErrorLoading();
            }

            @Override
            public void onFailure(Call<List<Post>> call, Throwable t)
            {
                view.hideProgressBar();
                view.onFailureLoading();
            }
        }, offset, count);
    }
}
