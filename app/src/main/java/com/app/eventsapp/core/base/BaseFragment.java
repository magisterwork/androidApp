package com.app.eventsapp.core.base;

import android.app.Fragment;

import com.app.eventsapp.core.di.HasComponent;

/**
 * Created by Grigory Kalyashov on 30.10.2016.
 *
 * Базовый класс для фрагментов
 */
public class BaseFragment extends Fragment
{
    @SuppressWarnings("unchecked")
    protected <T> T getComponent(Class<T> componentType)
    {
        return componentType.cast(((HasComponent<T>)getActivity()).getComponent());
    }
}
