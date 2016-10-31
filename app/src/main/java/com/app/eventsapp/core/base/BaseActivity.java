package com.app.eventsapp.core.base;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;

import com.app.eventsapp.core.app.EventsApp;
import com.app.eventsapp.core.di.components.EventsAppComponent;

/**
 * Created by Grigory Kalyashov on 30.10.2016.
 *
 * Базовый класс для активити
 */
public abstract class BaseActivity extends AppCompatActivity
{
    @Override
    public void onCreate(Bundle savedInstanceState, PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
        setupComponent(EventsApp.get(this).getAppComponent());

    }

    /**
     * Определяет компонент для каждой активити
     * @param appComponent
     */
    protected abstract void setupComponent(EventsAppComponent appComponent);
}
