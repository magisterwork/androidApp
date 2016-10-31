package com.app.eventsapp.core.di.components;

import com.app.eventsapp.core.app.EventsApp;
import com.app.eventsapp.core.di.modules.EventsAppModule;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by Grigory Kalyashov on 30.10.2016.
 */
@Singleton
@Component(
        modules = {
                EventsAppModule.class
        }
)
public interface EventsAppComponent
{
    void inject(EventsApp app);
}
