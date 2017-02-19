package com.app.eventsapp.core.mvp.main;

import android.support.v4.app.FragmentManager;

import com.app.eventsapp.modules.auth.session.UserSessionManager;

/**
 * Created by Grigory Kalyashov on 30.10.2016.
 */
public interface MainActivityPresenter
{
    void onProfileImageClick(FragmentManager fragmentManager, UserSessionManager userSessionManager);
}
