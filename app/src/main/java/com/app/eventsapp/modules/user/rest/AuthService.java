package com.app.eventsapp.modules.user.rest;

import com.app.eventsapp.modules.user.rest.request.GoogleToken;
import com.app.eventsapp.modules.user.rest.response.SimpleResponse;
import com.app.eventsapp.rest.base.RestService;
import com.app.eventsapp.rest.request.RequestCallback;
import com.app.eventsapp.rest.request.RequestListener;

import javax.inject.Inject;

import retrofit2.Call;


/**
 * Created by Grigory Kalyashov on 19.02.2017.
 *
 * Сервис аутентификации
 */
public class AuthService extends RestService
{
    @Inject
    public AuthService()
    {}

    /**
     * Отправить полученный google токен на валидацию
     *
     * @param googleToken google токен
     * @param requestListener слушатель
     */
    public void validateGoogleToken(String googleToken, RequestListener<SimpleResponse> requestListener)
    {
        AuthAPI authAPI = buildRetrofit().create(AuthAPI.class);
        Call<SimpleResponse> call = authAPI.validateGoogleToken(new GoogleToken(googleToken));
        call.enqueue(new RequestCallback<>(requestListener));
    }
}
