package com.app.eventsapp.modules.auth.rest;

import com.app.eventsapp.modules.auth.rest.request.GoogleToken;
import com.app.eventsapp.modules.auth.rest.response.SimpleResponse;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * Created by Grigory Kalyashov on 19.02.2017.
 *
 * API аутентификации
 */
public interface AuthAPI
{
    /**
     * Отправить google токен на сервер, для валидации
     *
     * @param gtoken google токен
     * @return ответ, содержащий токен, сгенерированный сервером
     */
    @POST("auth/google/validate")
    Call<SimpleResponse> validateGoogleToken(@Body GoogleToken gtoken);
}
