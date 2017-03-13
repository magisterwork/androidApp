package com.app.eventsapp.core.mvp.main;

import android.support.v4.app.FragmentManager;

import com.app.eventsapp.modules.auth.session.UserSessionManager;

/**
 * Created by Grigory Kalyashov on 30.10.2016.
 *
 * Презентер для MainActivity
 */
public interface MainActivityPresenter
{
    /**
     * Обработка нажатия на картинку профиля (в боковом меню)
     *
     * @param fragmentManager
     * @param userSessionManager
     */
    void onProfileImageClick(FragmentManager fragmentManager, UserSessionManager userSessionManager);
}
