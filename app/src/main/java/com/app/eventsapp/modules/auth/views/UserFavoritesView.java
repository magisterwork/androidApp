package com.app.eventsapp.modules.auth.views;

import com.app.eventsapp.modules.postline.models.Post;

import java.util.List;

/**
 * Created by Grigory Kalyashov on 23.02.2017.
 *
 * Избранное пользователя
 */
public interface UserFavoritesView
{
    void setRecyclerViewAdapter(List<Post> favorites);
    void onErrorLoading();
    void showProgressBar();
    void hideProgressBar();
    void showNeedAuthMessage();
}
