package com.app.eventsapp.modules.auth.presenters;

import com.app.eventsapp.modules.auth.session.UserSessionManager;
import com.app.eventsapp.modules.auth.views.UserProfileFragmentView;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;

/**
 * Created by Grigory Kalyashov on 19.02.2017.
 */

public class UserProfilePresenterImpl implements UserProfilePresenter
{
    private UserProfileFragmentView view;

    @Override
    public void init(UserProfileFragmentView view)
    {
        this.view = view;
    }

    @Override
    public void onLogOutBtnClick(UserSessionManager userSessionManager,
                                 GoogleApiClient googleApiClient)
    {
        userSessionManager.clearUserData();

        Auth.GoogleSignInApi.signOut(googleApiClient).setResultCallback(
                new ResultCallback<Status>()
                {
                    @Override
                    public void onResult(Status status)
                    {
                        if(status.isSuccess())
                        {
                            view.successLogOut();
                        }
                        else
                        {
                            view.failureLogOut();
                        }
                    }
                });
    }
}
