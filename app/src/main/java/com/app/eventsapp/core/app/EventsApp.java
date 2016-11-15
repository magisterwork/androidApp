package com.app.eventsapp.core.app;

import android.app.Application;
import android.content.Context;

import com.app.eventsapp.core.di.components.DaggerEventsAppComponent;
import com.app.eventsapp.core.di.components.EventsAppComponent;
import com.app.eventsapp.core.di.modules.EventsAppModule;

/**
 * Created by Grigory Kalyashov on 30.10.2016.
 *
 * Необходим для создания графа DI
 */
public class EventsApp extends Application
{
    private EventsAppComponent appComponent;
    private static EventsApp instance;

    public static EventsApp get(Context context)
    {
        return (EventsApp) context.getApplicationContext();
    }

    @Override
    public void onCreate() {
        super.onCreate();
        buildGraphAndInject();

        instance = this;
    }

    public EventsAppComponent getAppComponent()
    {
        return appComponent;
    }

    public void buildGraphAndInject()
    {
        appComponent = DaggerEventsAppComponent.builder()
                .eventsAppModule(new EventsAppModule(this))
                .build();

        appComponent.inject(this);
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
