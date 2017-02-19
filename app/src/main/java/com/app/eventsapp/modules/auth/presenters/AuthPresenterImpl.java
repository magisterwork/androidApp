package com.app.eventsapp.modules.auth.presenters;

import android.content.Context;

import com.app.eventsapp.modules.auth.models.User;
import com.app.eventsapp.modules.auth.session.UserSessionManager;
import com.app.eventsapp.modules.auth.views.AuthFragmentView;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;

/**
 * Created by Grigory Kalyashov on 19.02.2017.
 */
public class AuthPresenterImpl implements AuthPresenter
{
    private AuthFragmentView view;
    private UserSessionManager userSessionManager;

    @Override
    public void init(AuthFragmentView view)
    {
        this.view = view;
    }

    @Override
    public void saveUserData(GoogleSignInAccount account, Context context)
    {
        userSessionManager = new UserSessionManager(context);
        userSessionManager.saveUserData(new User(account));
    }
}
