package com.app.eventsapp.core.di.modules;

import android.app.Application;

import com.app.eventsapp.core.app.EventsApp;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Grigory Kalyashov on 30.10.2016.
 */
@Module
public class EventsAppModule
{
    private final EventsApp app;

    public EventsAppModule(EventsApp app)
    {
        this.app = app;
    }

    @Provides
    @Singleton
    public Application providesApplication()
    {
        return app;
    }
}
