package com.app.eventsapp.modules.auth.views;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.app.eventsapp.R;
import com.app.eventsapp.core.base.BaseFragment;

/**
 * Created by Grigory Kalyashov on 21.02.2017.
 */

public class UserFavoritesFragment extends BaseFragment
{
    public static String FRAGMENT_TAG = "UserFavoritesFragment";

    @NonNull
    @Override
    protected View initRootView(LayoutInflater inflater, ViewGroup container)
    {
        rootView = inflater.inflate(R.layout.favorites_fragment, container, false);

        initToolbar();

        return rootView;
    }

    private void initToolbar()
    {
        Toolbar toolbar = (Toolbar) rootView.findViewById(R.id.favorites_toolbar);
        context.setSupportActionBar(toolbar);
        context.getSupportActionBar().setTitle(getString(R.string.liked));
    }
}
