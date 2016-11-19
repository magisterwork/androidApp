package com.app.eventsapp.modules.postline.presenters;

import com.app.eventsapp.modules.postline.models.Post;
import com.app.eventsapp.modules.postline.views.PostLineFragmentView;
import com.app.eventsapp.rest.PostService;
import com.app.eventsapp.rest.RequestListener;

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

    @Inject
    public PostLinePresenterImpl()
    {}

    @Override
    public void onResume()
    {
        sendRequest();
    }

    @Override
    public void onPause()
    {

    }

    @Override
    public void onLoadMore()
    {

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

    private void sendRequest()
    {
        PostService.getInstance().getPosts(new RequestListener<List<Post>>()
        {
            @Override
            public void onSuccess(Call<List<Post>> call, Response<List<Post>> response)
            {
                view.setRecyclerViewAdapter(response.body());
            }

            @Override
            public void onErrorResponse(Call<List<Post>> call, Response<List<Post>> response)
            {

            }

            @Override
            public void onFailure(Call<List<Post>> call, Throwable t)
            {

            }
        });
    }
}
