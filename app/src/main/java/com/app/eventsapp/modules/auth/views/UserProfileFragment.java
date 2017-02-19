package com.app.eventsapp.modules.auth.views;

import android.media.Image;
import android.support.annotation.NonNull;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.app.eventsapp.R;
import com.app.eventsapp.core.base.DetailFragmentBase;
import com.app.eventsapp.core.managers.PicassoImageManager;
import com.app.eventsapp.modules.auth.models.User;
import com.app.eventsapp.modules.auth.presenters.UserProfilePresenterImpl;
import com.app.eventsapp.modules.auth.session.UserSessionManager;
import com.app.eventsapp.modules.auth.util.GoogleApiClientUtil;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.squareup.picasso.Picasso;

import javax.inject.Inject;

/**
 * Created by Grigory Kalyashov on 19.02.2017.
 */
public class UserProfileFragment extends DetailFragmentBase implements UserProfileFragmentView,
        GoogleApiClient.OnConnectionFailedListener
{
    public static String FRAGMENT_TAG = "UserProfileFragment";

    @Inject
    public UserProfilePresenterImpl presenter = new UserProfilePresenterImpl();

    private UserSessionManager userSessionManager;

    private GoogleApiClient googleApiClient;

    @Override
    public void onResume()
    {
        super.onResume();

        presenter.init(this);
    }

    @NonNull
    @Override
    protected View initRootView(LayoutInflater inflater, ViewGroup container)
    {
        rootView = inflater.inflate(R.layout.user_profile_fragment, container, false);

        initToolbar();

        googleApiClient = GoogleApiClientUtil
                .getGoogleApiClient(context, getString(R.string.server_client_id), this);

        //TODO передавать объект User при создании фрагмента
        userSessionManager = new UserSessionManager(context);

        showUserData(userSessionManager.getUserData());

        Button logoutBtn = (Button) rootView.findViewById(R.id.logout_btn);

        logoutBtn.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                presenter.onLogOutBtnClick(userSessionManager, googleApiClient);
                getFragmentManager().popBackStack();
            }
        });

        return rootView;
    }

    private void initToolbar()
    {
        Toolbar toolbar = (Toolbar) rootView.findViewById(R.id.profile_toolbar);
        context.setSupportActionBar(toolbar);
        context.getSupportActionBar().setTitle(getString(R.string.profile));
        context.getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        context.getSupportActionBar().setDisplayShowHomeEnabled(true);

        toolbar.setNavigationOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                getFragmentManager().popBackStack();
            }
        });
    }

    private void showUserData(User currentUser)
    {
        TextView username = (TextView) rootView.findViewById(R.id.user_profile_username);
        username.setText(currentUser.getFullName());

        ImageView profileImage = (ImageView) rootView.findViewById(R.id.user_profile_profile_image);

        String userPhotoUrl = currentUser.getPhotoUrl();

        if(userPhotoUrl != null)
        {
            PicassoImageManager.getInstance()
                    .loadResource(userPhotoUrl, profileImage, Picasso.Priority.HIGH);
        }
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult)
    {
        Toast.makeText(context,getString(R.string.auth_failure), Toast.LENGTH_LONG).show();
    }

    @Override
    public void successLogOut()
    {
        Toast.makeText(context,getString(R.string.success_log_out), Toast.LENGTH_LONG).show();

        ImageView profileImage = (ImageView) context.findViewById(R.id.profile_image);
        profileImage.setImageResource(R.drawable.user);

        TextView username = (TextView) context.findViewById(R.id.username);
        username.setText("");
    }

    @Override
    public void failureLogOut()
    {
        Toast.makeText(context,getString(R.string.failure_log_out), Toast.LENGTH_LONG).show();
    }

    @Override
    public void onDestroy()
    {
        super.onDestroy();
        googleApiClient.stopAutoManage(context);
        googleApiClient.disconnect();
    }
}
