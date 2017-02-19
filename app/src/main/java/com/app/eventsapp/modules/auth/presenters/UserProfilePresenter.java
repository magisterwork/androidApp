package com.app.eventsapp.modules.auth.presenters;

import com.app.eventsapp.core.base.BaseFragmentPresenter;
import com.app.eventsapp.modules.auth.session.UserSessionManager;
import com.app.eventsapp.modules.auth.views.UserProfileFragmentView;
import com.google.android.gms.common.api.GoogleApiClient;

/**
 * Created by Grigory Kalyashov on 19.02.2017.
 */

public interface UserProfilePresenter extends BaseFragmentPresenter<UserProfileFragmentView>
{
    void onLogOutBtnClick(UserSessionManager userSessionManager, GoogleApiClient googleApiClient);
}
