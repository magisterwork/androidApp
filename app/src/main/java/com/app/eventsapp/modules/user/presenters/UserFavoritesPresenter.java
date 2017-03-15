package com.app.eventsapp.modules.user.presenters;

import com.app.eventsapp.core.base.BaseFragmentPresenter;
import com.app.eventsapp.modules.user.util.UserDataManager;
import com.app.eventsapp.modules.user.views.UserFavoritesView;

/**
 * Created by Grigory Kalyashov on 23.02.2017.
 *
 * Презентер для избранных событий пользователя
 */
public interface UserFavoritesPresenter extends BaseFragmentPresenter<UserFavoritesView>
{
    void onResume(UserDataManager sessionManager);
}
