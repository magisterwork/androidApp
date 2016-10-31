package com.app.eventsapp.core.mvp.main;

import javax.inject.Inject;

/**
 * Created by Grigory Kalyashov on 30.10.2016.
 */
public class MainActivityPresenterImpl implements MainActivityPresenter {

    private MainActivityView view;

    @Inject
    public MainActivityPresenterImpl(MainActivityView view)
    {
        this.view = view;
    }

    @Override
    public void onBackPressed() {

    }
}
