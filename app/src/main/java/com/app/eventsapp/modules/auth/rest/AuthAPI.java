package com.app.eventsapp.modules.auth.rest;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
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
    @FormUrlEncoded
    @POST("auth/google/validate")
    Call<ResponseBody> validateGoogleToken(@Field("gtoken") String gtoken);
}
