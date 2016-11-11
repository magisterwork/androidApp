package com.app.eventsapp.modules.postline.presenters;

import com.app.eventsapp.modules.postline.models.Post;
import com.app.eventsapp.modules.postline.views.PostLineFragmentView;
import com.app.eventsapp.rest.PostService;

import java.util.List;

import javax.inject.Inject;

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
        // TODO может быть заюзать RequestListener
        view.setRecyclerViewAdapter(sendRequest());
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
    public void onItemClick()
    {

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

    private List<Post> sendRequest()
    {
        return PostService.getPosts();
    }
}
