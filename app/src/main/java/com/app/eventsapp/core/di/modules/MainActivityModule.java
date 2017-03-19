package com.app.eventsapp.core.di.modules;

import com.app.eventsapp.core.mvp.main.MainActivityPresenterImpl;
import com.app.eventsapp.core.mvp.main.MainActivityView;
import com.app.eventsapp.modules.user.presenters.AuthPresenterImpl;
import com.app.eventsapp.modules.user.presenters.UserProfilePresenterImpl;
import com.app.eventsapp.modules.postline.presenters.DetailPostPresenterImpl;
import com.app.eventsapp.modules.postline.presenters.PostLinePresenterImpl;
import com.app.eventsapp.rest.postapi.EventsService;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Grigory Kalyashov on 30.10.2016.
 */
@Module
public class MainActivityModule
{
    private MainActivityView view;

    public MainActivityModule(MainActivityView view) {
        this.view = view;
    }

    @Provides
    public MainActivityView provideView()
    {
        return view;
    }

    @Provides
    public MainActivityPresenterImpl providesMainActivityPresenterImpl(MainActivityView view)
    {
        return new MainActivityPresenterImpl(view);
    }

    @Provides
    public PostLinePresenterImpl providesPostLinePresenterImpl(EventsService eventsService)
    {
        return new PostLinePresenterImpl(eventsService);
    }

    @Provides
    public DetailPostPresenterImpl providesDetailPostPresenterImpl()
    {
        return new DetailPostPresenterImpl();
    }

    @Provides
    public AuthPresenterImpl providesAuthPresenterImpl()
    {
        return new AuthPresenterImpl();
    }

    @Provides
    public UserProfilePresenterImpl providesUserProfilePresenterImpl()
    {
        return new UserProfilePresenterImpl();
    }

    @Provides
    public EventsService providesPostService()
    {
        return new EventsService();
    }
}
