package com.app.eventsapp.modules.auth.presenters;

import android.content.Context;
import android.util.Log;

import com.app.eventsapp.modules.auth.models.User;
import com.app.eventsapp.modules.auth.rest.AuthService;
import com.app.eventsapp.modules.auth.rest.response.ResponseStatus;
import com.app.eventsapp.modules.auth.rest.response.SimpleResponse;
import com.app.eventsapp.modules.auth.session.UserSessionManager;
import com.app.eventsapp.modules.auth.views.AuthFragmentView;
import com.app.eventsapp.rest.request.RequestListener;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;

import org.apache.commons.lang3.StringUtils;

import javax.inject.Inject;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Response;

/**
 * Created by Grigory Kalyashov on 19.02.2017.
 */
public class AuthPresenterImpl implements AuthPresenter
{
    private AuthFragmentView view;
    private UserSessionManager userSessionManager;

    @Inject
    public AuthPresenterImpl()
    {}

    @Override
    public void init(AuthFragmentView view)
    {
        this.view = view;
    }

    @Override
    public void saveUserData(GoogleSignInAccount account, Context context)
    {
        AuthService authService = new AuthService();

        userSessionManager = new UserSessionManager(context);

        userSessionManager.saveUserData(new User(account));

        if(!StringUtils.isEmpty(account.getIdToken()))
        {
            authService.validateGoogleToken(account.getIdToken(), new RequestListener<SimpleResponse>()
            {
                @Override
                public void onSuccess(Call<SimpleResponse> call, Response<SimpleResponse> response)
                {
                    SimpleResponse rs = response.body();

                    if(rs.getStatus().equals(ResponseStatus.SUCCESS))
                    {
                        userSessionManager.saveUserToken(rs.getToken());
                    }
                    else
                    {
                        Log.d("Auth","Unsuccess response");
                    }
                }

                @Override
                public void onErrorResponse(Call<SimpleResponse> call, Response<SimpleResponse> response)
                {
                    Log.d("Auth","error response");
                }

                @Override
                public void onFailure(Call<SimpleResponse> call, Throwable t)
                {
                    Log.d("Auth","fail");
                }
            });
        }
    }

}
