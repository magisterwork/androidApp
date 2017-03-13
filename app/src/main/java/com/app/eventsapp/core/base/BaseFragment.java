package com.app.eventsapp.core.base;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.app.eventsapp.core.di.HasComponent;

/**
 * Created by Grigory Kalyashov on 30.10.2016.
 *
 * Базовый класс для фрагментов
 */
public abstract class BaseFragment extends Fragment
{
    //корневой layout
    protected View rootView;
    protected FragmentManager fragmentManager;
    protected AppCompatActivity context;

    /**
     * Инициализировать корневой layout
     * @param inflater
     * @param container
     * @return
     */
    @NonNull
    protected abstract View initRootView(LayoutInflater inflater, ViewGroup container);

    //TODO мб стоит вынести в отдельный класс
    @SuppressWarnings("unchecked")
    protected <T> T getComponent(Class<T> componentType)
    {
        return componentType.cast(((HasComponent<T>)getActivity()).getComponent());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        initialize(savedInstanceState);
        initRootView(inflater, container);

        return rootView;
    }

    /**
     * Инициализация фрагмента
     * @param savedInstanceState
     */
    private void initialize(Bundle savedInstanceState)
    {
        context = (AppCompatActivity) getActivity();
        fragmentManager = context.getSupportFragmentManager();
    }
}
