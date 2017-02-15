package com.app.eventsapp.rest.base;

import com.app.eventsapp.rest.postapi.PostJsonBuilder;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Grigory Kalyashov on 05.02.2017.
 */
public abstract class RestService
{
    protected static Retrofit buildRetrofit(String serverApiUrl)
    {
        return new Retrofit.Builder()
                .baseUrl(serverApiUrl)
                .addConverterFactory(GsonConverterFactory.create(PostJsonBuilder.buildPostGson()))
                .build();
    }
}
