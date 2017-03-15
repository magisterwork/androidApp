package com.app.eventsapp.modules.user.presenters;

import com.app.eventsapp.modules.user.util.UserDataManager;
import com.app.eventsapp.modules.user.views.UserProfileFragmentView;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;

import javax.inject.Inject;

/**
 * Created by Grigory Kalyashov on 19.02.2017.
 */
public class UserProfilePresenterImpl implements UserProfilePresenter
{
    private UserProfileFragmentView view;

    @Inject
    public UserProfilePresenterImpl()
    {}

    @Override
    public void init(UserProfileFragmentView view)
    {
        this.view = view;
    }

    @Override
    public void onLogOutBtnClick(UserDataManager userDataManager,
                                 GoogleApiClient googleApiClient)
    {
        userDataManager.clearUserData();

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
