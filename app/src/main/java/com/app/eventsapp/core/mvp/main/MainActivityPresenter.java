package com.app.eventsapp.core.mvp.main;

import android.support.v4.app.FragmentManager;

import com.app.eventsapp.modules.user.util.UserDataManager;

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
     * @param userDataManager
     */
    void onProfileImageClick(FragmentManager fragmentManager, UserDataManager userDataManager);
}
