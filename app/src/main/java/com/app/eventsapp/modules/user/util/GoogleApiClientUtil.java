package com.app.eventsapp.modules.user.util;


import android.support.v4.app.FragmentActivity;

import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.GoogleApiClient;

/**
 * Created by Grigory Kalyashov on 19.02.2017.
 */
public class GoogleApiClientUtil
{

    /**
     * Получение GoogleApiClient
     *
     * @param context
     * @param cliendId
     * @param listener
     * @return
     */
    public static GoogleApiClient getGoogleApiClient(FragmentActivity context, String cliendId,
                                                     GoogleApiClient.OnConnectionFailedListener listener)
    {
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(cliendId)
                .build();

        GoogleApiClient mGoogleApiClient = new GoogleApiClient.Builder(context)
                .enableAutoManage(context, listener)
                .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
                .build();

        return mGoogleApiClient;
    }
}
