package com.app.eventsapp;

import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentManager;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.app.eventsapp.core.app.EventsApp;
import com.app.eventsapp.core.base.BaseActivity;
import com.app.eventsapp.core.base.NavigationDrawerContainer;
import com.app.eventsapp.core.di.HasComponent;
import com.app.eventsapp.core.di.components.DaggerMainActivityComponent;
import com.app.eventsapp.core.di.components.EventsAppComponent;
import com.app.eventsapp.core.di.components.MainActivityComponent;
import com.app.eventsapp.core.di.modules.MainActivityModule;
import com.app.eventsapp.core.managers.PicassoImageManager;
import com.app.eventsapp.core.mvp.main.MainActivityPresenterImpl;
import com.app.eventsapp.core.mvp.main.MainActivityView;
import com.app.eventsapp.modules.auth.models.User;
import com.app.eventsapp.modules.auth.session.UserSessionManager;
import com.app.eventsapp.modules.auth.views.UserFavoritesFragment;
import com.app.eventsapp.modules.navigation.ContentFragment;
import com.squareup.picasso.Picasso;

import javax.inject.Inject;

public class MainActivity extends BaseActivity implements MainActivityView,
        HasComponent<MainActivityComponent>, NavigationDrawerContainer
{
    @Inject
    public MainActivityPresenterImpl presenter;

    public static final int FRAGMENT_CONTAINER = R.id.fragment_container;

    private MainActivityComponent mainActivityComponent;
    private FragmentManager fragmentManager;
    private ActionBarDrawerToggle drawerToggle;
    private DrawerLayout drawerLayout;
    private UserSessionManager userSessionManager;

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

        userSessionManager = new UserSessionManager(getApplicationContext());
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
    public void initDrawer(Toolbar toolbar, int checkedItem)
    {
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        drawerToggle = new ActionBarDrawerToggle(this, drawerLayout,
                R.string.drawer_open,  R.string.drawer_close);

        drawerLayout.addDrawerListener(drawerToggle);

        drawerToggle.syncState();

        navigationView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener()
                {
                    @Override
                    public boolean onNavigationItemSelected(MenuItem menuItem)
                    {
                        fragmentManager = getSupportFragmentManager();

                        switch (menuItem.getItemId())
                        {
                            case R.id.nav_feed:
                            {
                                ContentFragment contentFragment = (ContentFragment)
                                        fragmentManager.findFragmentByTag("ContentFragment");

                                if (contentFragment == null)
                                {
                                    contentFragment = new ContentFragment();
                                }

                                fragmentManager.beginTransaction()
                                        .replace(FRAGMENT_CONTAINER, contentFragment)
                                        .commit();

                                break;
                            }
                            case R.id.nav_favorites:
                            {
                                UserFavoritesFragment userFavoritesFragment = (UserFavoritesFragment)
                                        fragmentManager.findFragmentByTag(UserFavoritesFragment.FRAGMENT_TAG);

                                if (userFavoritesFragment == null)
                                {
                                    userFavoritesFragment = new UserFavoritesFragment();
                                }

                                fragmentManager.beginTransaction()
                                        .replace(FRAGMENT_CONTAINER, userFavoritesFragment)
                                        .commit();

                                break;
                            }
                        }

                        drawerLayout.closeDrawers();
                        return true;
                    }
                });

        navigationView.setCheckedItem(checkedItem);
        drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED);

        ImageView profileImage = (ImageView) navigationView.getHeaderView(0)
                .findViewById(R.id.profile_image);

        TextView userName = (TextView) navigationView.getHeaderView(0).
                findViewById(R.id.username);

        profileImage.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                presenter.onProfileImageClick(fragmentManager, userSessionManager);
            }
        });

        if(userSessionManager.isUserLoggedIn())
        {
            User currentUser = userSessionManager.getUserData();
            String userPhotoUrl = currentUser.getPhotoUrl();

            if(userPhotoUrl != null)
            {
                PicassoImageManager.getInstance()
                        .loadResource(userPhotoUrl, profileImage, Picasso.Priority.HIGH);
            }

            userName.setText(currentUser.getFullName());
        }
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
        if(drawerLayout != null)
        {
            drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);
        }
    }
}
