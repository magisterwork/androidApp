package com.app.eventsapp;

import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentManager;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.app.eventsapp.core.app.EventsApp;
import com.app.eventsapp.core.base.BaseActivity;
import com.app.eventsapp.core.base.NavigationDrawerActivity;
import com.app.eventsapp.core.di.HasComponent;
import com.app.eventsapp.core.di.components.DaggerMainActivityComponent;
import com.app.eventsapp.core.di.components.EventsAppComponent;
import com.app.eventsapp.core.di.components.MainActivityComponent;
import com.app.eventsapp.core.di.modules.MainActivityModule;
import com.app.eventsapp.core.mvp.main.MainActivityPresenterImpl;
import com.app.eventsapp.core.mvp.main.MainActivityView;
import com.app.eventsapp.modules.navigation.ContentFragment;

import javax.inject.Inject;

public class MainActivity extends BaseActivity implements MainActivityView,
        HasComponent<MainActivityComponent>, NavigationDrawerActivity
{
    @Inject
    public MainActivityPresenterImpl presenter;

    public static final int FRAGMENT_CONTAINER = R.id.fragment_container;

    private MainActivityComponent mainActivityComponent;
    private FragmentManager fragmentManager;
    private ActionBarDrawerToggle drawerToggle;
    private DrawerLayout drawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setupComponent(EventsApp.get(this).getAppComponent());
        setContentView(R.layout.activity_main);

        fragmentManager = getSupportFragmentManager();
        ContentFragment contentFragment = (ContentFragment)
                fragmentManager.findFragmentByTag("ContentFragment");

        if (contentFragment == null)
        {
            contentFragment = new ContentFragment();
        }

        if (savedInstanceState == null) {
            fragmentManager.beginTransaction()
                    .replace(FRAGMENT_CONTAINER, contentFragment)
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
    public MainActivityComponent getComponent()
    {
        return mainActivityComponent;
    }

    @Override
    public void initDrawer(Toolbar toolbar)
    {
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        drawerToggle = new ActionBarDrawerToggle(this, drawerLayout,
                R.string.drawer_open,  R.string.drawer_close);

        drawerLayout.addDrawerListener(drawerToggle);

        drawerToggle.syncState();

        navigationView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(MenuItem menuItem)
                    {
                        drawerLayout.closeDrawers();
                        return true;
                    }
                });

        drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        //TODO
        if(drawerToggle != null && drawerToggle.onOptionsItemSelected(item))
        {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void setDrawerLockMode()
    {
        drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);
    }
}
