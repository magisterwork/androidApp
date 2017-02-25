package com.app.eventsapp.modules.auth.presenters;

import com.app.eventsapp.core.base.BaseFragmentPresenter;
import com.app.eventsapp.modules.auth.session.UserSessionManager;
import com.app.eventsapp.modules.auth.views.UserFavoritesView;

/**
 * Created by Grigory Kalyashov on 23.02.2017.
 *
 * Презентер для избранных событий пользователя
 */
public interface UserFavoritesPresenter extends BaseFragmentPresenter<UserFavoritesView>
{
    void onResume(UserSessionManager sessionManager);
}
