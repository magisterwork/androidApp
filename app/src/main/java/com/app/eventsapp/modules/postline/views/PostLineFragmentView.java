package com.app.eventsapp.modules.postline.views;

import com.app.eventsapp.modules.postline.models.Post;

import java.util.List;

/**
 * Created by Grigory Kalyashov on 31.10.2016.
 */
public interface PostLineFragmentView
{
    void openPostDetails();
    void addPostsToAdapter(List<Post> posts);
    void setRecyclerViewAdapter(List<Post> posts);
}
