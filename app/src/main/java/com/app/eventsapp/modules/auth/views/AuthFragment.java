package com.app.eventsapp.modules.auth.views;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.app.eventsapp.R;
import com.app.eventsapp.core.base.DetailFragmentBase;
import com.app.eventsapp.modules.auth.presenters.AuthPresenterImpl;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.GoogleApiClient;

import javax.inject.Inject;

import static com.makeramen.roundedimageview.RoundedDrawable.TAG;

/**
 * Created by Grigory Kalyashov on 16.02.2017.
 *
 * Фрагмент аутентификации
 */
public class AuthFragment extends DetailFragmentBase implements AuthFragmentView,
        GoogleApiClient.OnConnectionFailedListener
{
    @Inject
    public AuthPresenterImpl presenter = new AuthPresenterImpl();

    public static String FRAGMENT_TAG = "AuthFragment";
    private final int SIGN_IN_REQUEST_CODE = 120;

    @NonNull
    @Override
    protected View initRootView(LayoutInflater inflater, ViewGroup container)
    {
        rootView = inflater.inflate(R.layout.auth_fragment, container, false);
        return rootView;
    }

    @Override
    public void onResume()
    {
        super.onResume();
        presenter.init(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        super.onCreateView(inflater, container, savedInstanceState);

        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.server_client_id))
                .build();

        final GoogleApiClient mGoogleApiClient = new GoogleApiClient.Builder(context)
                .enableAutoManage(context, this)
                .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
                .build();

        SignInButton signInButton = (SignInButton) rootView.findViewById(R.id.sign_in_button);
        Button skipSignInButton = (Button) rootView.findViewById(R.id.skip_sign_in_button);

        signInButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Intent signInIntent = Auth.GoogleSignInApi.getSignInIntent(mGoogleApiClient);
                startActivityForResult(signInIntent, SIGN_IN_REQUEST_CODE);
            }
        });

        skipSignInButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                getFragmentManager().popBackStack();
            }
        });

        return rootView;
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        if (requestCode == SIGN_IN_REQUEST_CODE)
        {
            GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
            handleSignInResult(result);
        }
    }

    private void handleSignInResult(GoogleSignInResult result)
    {
        Log.d(TAG, "handleSignInResult:" + result.isSuccess());

        if (result.isSuccess())
        {
            GoogleSignInAccount account = result.getSignInAccount();
            presenter.saveUserData(account, context);
        }
        else
        {
            Toast.makeText(context,getString(R.string.auth_failure), Toast.LENGTH_LONG).show();
        }

        getFragmentManager().popBackStack();
    }


    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult)
    {
        Toast.makeText(context,getString(R.string.auth_failure), Toast.LENGTH_LONG).show();
    }
}
