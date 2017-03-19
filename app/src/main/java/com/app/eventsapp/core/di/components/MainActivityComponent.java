package com.app.eventsapp.core.di.components;

import com.app.eventsapp.MainActivity;
import com.app.eventsapp.core.di.modules.MainActivityModule;
import com.app.eventsapp.core.di.scopes.ActivityScope;
import com.app.eventsapp.modules.user.views.AuthFragment;
import com.app.eventsapp.modules.user.views.UserProfileFragment;
import com.app.eventsapp.modules.postline.views.DetailPostFragment;
import com.app.eventsapp.modules.postline.views.PostLineFragment;

import dagger.Component;

/**
 * Created by Grigory Kalyashov on 30.10.2016.
 */
@ActivityScope
@Component(
        dependencies = EventsAppComponent.class,
        modules = MainActivityModule.class
)
public interface MainActivityComponent
{
    void inject(MainActivity activity);
    void inject(PostLineFragment postLineFragment);
    void inject(DetailPostFragment detailPostFragment);
    void inject(AuthFragment authFragment);
    void inject(UserProfileFragment userProfileFragment);
}
