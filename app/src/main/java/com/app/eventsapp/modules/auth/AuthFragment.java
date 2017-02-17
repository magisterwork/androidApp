package com.app.eventsapp.modules.auth;

import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.app.eventsapp.R;
import com.app.eventsapp.core.base.DetailFragmentBase;

/**
 * Created by Grigory Kalyashov on 16.02.2017.
 *
 * Фрагмент аутентификации
 */
public class AuthFragment extends DetailFragmentBase
{
    public static String FRAGMENT_TAG = "AuthFragment";

    @NonNull
    @Override
    protected View initRootView(LayoutInflater inflater, ViewGroup container)
    {
        rootView = inflater.inflate(R.layout.auth_fragment, container, false);
        return rootView;
    }
}
