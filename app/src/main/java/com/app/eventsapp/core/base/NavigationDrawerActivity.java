package com.app.eventsapp.core.base;

import android.support.v7.widget.Toolbar;
/**
 * Created by Grigory Kalyashov on 21.01.2017.
 */
public interface NavigationDrawerActivity
{
    void initDrawer(Toolbar toolbar, int checkedItem);
    void setDrawerLockMode();
}
