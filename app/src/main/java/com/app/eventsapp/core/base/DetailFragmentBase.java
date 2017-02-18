package com.app.eventsapp.core.base;

import android.os.Bundle;
import android.support.annotation.Nullable;

/**
 * Created by Grigory Kalyashov on 21.01.2017.
 *
 * Фрагмент без боковой навигации
 */
public abstract class DetailFragmentBase extends BaseFragment
{
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState)
    {
        super.onActivityCreated(savedInstanceState);

        NavigationDrawerActivity navigationActivity = (NavigationDrawerActivity) context;
        navigationActivity.setDrawerLockMode();
    }
}
