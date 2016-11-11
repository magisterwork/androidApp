package com.app.eventsapp.modules.postline.presenters;

import com.app.eventsapp.core.base.BaseFragmentPresenter;
import com.app.eventsapp.modules.postline.models.Post;
import com.app.eventsapp.modules.postline.views.PostLineFragmentView;

import java.util.List;

/**
 * Created by Grigory Kalyashov on 31.10.2016.
 */
public interface PostLinePresenter extends BaseFragmentPresenter<PostLineFragmentView>
{
    void onResume();
    void onPause();
    void onLoadMore();
    void onItemClick();
    void addPostsToAdapter(List<Post> postList);
}
