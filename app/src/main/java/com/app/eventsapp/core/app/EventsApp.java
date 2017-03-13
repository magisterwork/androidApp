package com.app.eventsapp.core.app;

import android.app.Application;
import android.content.Context;

import com.app.eventsapp.core.di.components.DaggerEventsAppComponent;
import com.app.eventsapp.core.di.components.EventsAppComponent;
import com.app.eventsapp.core.di.modules.EventsAppModule;
import com.crashlytics.android.Crashlytics;

import io.fabric.sdk.android.Fabric;

/**
 * Created by Grigory Kalyashov on 30.10.2016.
 */
public class EventsApp extends Application
{
    private EventsAppComponent appComponent;
    private static EventsApp instance;

    @Override
    public void onCreate()
    {
        super.onCreate();
        buildGraphAndInject();
        instance = this;
        Fabric.with(this, new Crashlytics());
    }

    /**
     * Создание графа DI
     */
    public void buildGraphAndInject()
    {
        appComponent = DaggerEventsAppComponent.builder()
                .eventsAppModule(new EventsAppModule(this))
                .build();

        appComponent.inject(this);
    }

    public static EventsApp get(Context context)
    {
        return (EventsApp) context.getApplicationContext();
    }

    public EventsAppComponent getAppComponent()
    {
        return appComponent;
    }

    public static EventsApp getInstance()
    {
        return instance;
    }

    public static Context getAppContext()
    {
        return instance.getApplicationContext();
    }
}
