package com.app.eventsapp.core.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;

import com.app.eventsapp.R;

/**
 * Created by Grigory Kalyashov on 21.01.2017.
 */
public abstract class NavigationDrawerFragment extends BaseFragment
{
    protected Toolbar toolbar;
    protected int checkedItem = R.id.nav_feed;

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState)
    {
        super.onActivityCreated(savedInstanceState);

        NavigationDrawerContainer activity = (NavigationDrawerContainer) context;
        activity.initDrawer(toolbar, checkedItem);
    }
}
