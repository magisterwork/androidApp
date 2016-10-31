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
    void onLoadMore();
    void onItemClick();
    void addListToAdapter(List<Post> postList);
}
