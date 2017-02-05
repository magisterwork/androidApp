package com.app.eventsapp.core.di.modules;

import com.app.eventsapp.core.mvp.main.MainActivityPresenterImpl;
import com.app.eventsapp.core.mvp.main.MainActivityView;
import com.app.eventsapp.modules.postline.presenters.PostLinePresenterImpl;
import com.app.eventsapp.rest.postapi.PostService;

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
    public PostLinePresenterImpl providesPostLinePresenterImpl(PostService postService)
    {
        return new PostLinePresenterImpl(postService);
    }

    @Provides
    public PostService providesPostService()
    {
        return new PostService();
    }
}
