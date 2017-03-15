package com.app.eventsapp.core.mvp.main;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import com.app.eventsapp.modules.user.util.UserDataManager;
import com.app.eventsapp.modules.user.views.AuthFragment;
import com.app.eventsapp.modules.user.views.UserProfileFragment;

import javax.inject.Inject;

import static com.app.eventsapp.MainActivity.FRAGMENT_CONTAINER;

/**
 * Created by Grigory Kalyashov on 30.10.2016.
 */
public class MainActivityPresenterImpl implements MainActivityPresenter
{

    private MainActivityView view;

    @Inject
    public MainActivityPresenterImpl(MainActivityView view)
    {
        this.view = view;
    }

    @Override
    public void onProfileImageClick(FragmentManager fragmentManager,
                                    UserDataManager userDataManager)
    {
        if(userDataManager.isUserLoggedIn())
        {
            UserProfileFragment userProfileFragment = (UserProfileFragment)
                    fragmentManager.findFragmentByTag(UserProfileFragment.FRAGMENT_TAG);

            if (userProfileFragment == null)
            {
                userProfileFragment = new UserProfileFragment();
            }

            fragmentManager.beginTransaction()
                    .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                    .replace(FRAGMENT_CONTAINER, userProfileFragment)
                    .addToBackStack(AuthFragment.FRAGMENT_TAG)
                    .commit();
        }
        else
        {
            AuthFragment authFragment = (AuthFragment)
                    fragmentManager.findFragmentByTag(AuthFragment.FRAGMENT_TAG);

            if (authFragment == null)
            {
                authFragment = new AuthFragment();
            }

            fragmentManager.beginTransaction()
                    .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                    .replace(FRAGMENT_CONTAINER, authFragment)
                    .addToBackStack(AuthFragment.FRAGMENT_TAG)
                    .commit();
        }
    }
}
