package com.app.eventsapp;

import android.app.FragmentManager;
import android.os.Bundle;

import android.support.v7.widget.Toolbar;

import com.app.eventsapp.core.app.EventsApp;
import com.app.eventsapp.core.base.BaseActivity;
import com.app.eventsapp.core.di.HasComponent;
import com.app.eventsapp.core.di.components.DaggerMainActivityComponent;
import com.app.eventsapp.core.di.components.EventsAppComponent;
import com.app.eventsapp.core.di.components.MainActivityComponent;
import com.app.eventsapp.core.di.modules.MainActivityModule;
import com.app.eventsapp.core.mvp.main.MainActivityPresenterImpl;
import com.app.eventsapp.core.mvp.main.MainActivityView;
import com.app.eventsapp.modules.postline.views.PostLineFragment;

import javax.inject.Inject;

public class MainActivity extends BaseActivity implements MainActivityView, HasComponent<MainActivityComponent> {

    @Inject
    public MainActivityPresenterImpl presenter;

    private MainActivityComponent mainActivityComponent;
    private FragmentManager fragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setupComponent(EventsApp.get(this).getAppComponent());
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        fragmentManager = getFragmentManager();
        PostLineFragment postLineFragment = (PostLineFragment) fragmentManager.findFragmentByTag("PostLineFragment");

        if (postLineFragment == null)
        {
            postLineFragment = new PostLineFragment();
        }

        if (savedInstanceState == null) {
            fragmentManager.beginTransaction()
                    .replace(R.id.fragment_container, postLineFragment)
                    .commit();
        }
    }

    @Override
    protected void setupComponent(EventsAppComponent appComponent) {
        mainActivityComponent = DaggerMainActivityComponent.builder()
                .eventsAppComponent(appComponent)
                .mainActivityModule(new MainActivityModule(this))
                .build();

        mainActivityComponent.inject(this);
    }

    @Override
    public MainActivityComponent getComponent() {
        return mainActivityComponent;
    }
}
