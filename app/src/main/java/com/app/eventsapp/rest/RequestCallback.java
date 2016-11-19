package com.app.eventsapp.rest;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Grigory Kalyashov on 19.11.2016.
 *
 * Класс для колбеков
 * для обработки результатов используется listener
 *
 * @see retrofit2.Callback
 */
public class RequestCallback<T> implements Callback<T>
{
    protected RequestListener<T> listener;

    public RequestCallback(RequestListener<T> listener)
    {
        this.listener = listener;
    }

    @Override
    public void onResponse(Call<T> call, Response<T> response)
    {
        if(response.isSuccessful())
        {
            this.listener.onSuccess(call, response);
        }
        else
        {
            this.listener.onErrorResponse(call, response);
        }
    }

    @Override
    public void onFailure(Call<T> call, Throwable t)
    {
        this.listener.onFailure(call, t);
    }
}
