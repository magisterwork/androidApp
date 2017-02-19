package com.app.eventsapp.modules.auth.rest;

import com.app.eventsapp.rest.base.RestService;
import com.app.eventsapp.rest.request.RequestCallback;
import com.app.eventsapp.rest.request.RequestListener;

import javax.inject.Inject;

import okhttp3.ResponseBody;
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
    public void validateGoogleToken(String googleToken, RequestListener<ResponseBody> requestListener)
    {
        AuthAPI authAPI = buildRetrofit().create(AuthAPI.class);
        Call<ResponseBody> call = authAPI.validateGoogleToken(new GoogleToken(googleToken));
        call.enqueue(new RequestCallback<>(requestListener));
    }
}
