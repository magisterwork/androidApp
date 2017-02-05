package com.app.eventsapp.core.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;

/**
 * Created by Grigory Kalyashov on 21.01.2017.
 */
public abstract class NavigationDrawerFragment extends BaseFragment
{
    protected Toolbar toolbar;

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState)
    {
        super.onActivityCreated(savedInstanceState);

        NavigationDrawerActivity activity = (NavigationDrawerActivity) context;
        activity.initDrawer(toolbar);
    }
}
