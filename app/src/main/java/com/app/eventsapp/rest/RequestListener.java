package com.app.eventsapp.rest;

import retrofit2.Call;
import retrofit2.Response;

/**
 * Created by Grigory Kalyashov on 19.11.2016.
 *
 * Слушатель запросов
 */
public abstract interface RequestListener<T>
{
    /**
     * Вызывается при удачном выполнении запроса
     * @param call
     * @param response
     */
    void onSuccess(Call<T> call, Response<T> response);

    /**
     * Вызывается если вовремя запроса произошла ошибка
     * @param call
     * @param response
     */
    void onErrorResponse(Call<T> call, Response<T> response);

    /**
     * Вызывается если запрос не удалось совершить
     * @param call
     * @param t
     */
    void onFailure(Call<T> call, Throwable t);
}
