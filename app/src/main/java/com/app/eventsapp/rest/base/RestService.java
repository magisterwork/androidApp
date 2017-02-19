package com.app.eventsapp.rest.base;

import com.google.gson.Gson;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Grigory Kalyashov on 05.02.2017.
 */
public abstract class RestService
{
    private static final String VERSION_API = "v1/";
    private static final String SERVER_ENDPOINT = "http://185.159.130.67:8080/api/" + VERSION_API;

    protected static Retrofit buildRetrofit()
    {
        return new Retrofit.Builder()
                .baseUrl(SERVER_ENDPOINT)
                .build();
    }

    protected static Retrofit buildRetrofit(Gson gson)
    {
        return new Retrofit.Builder()
                .baseUrl(SERVER_ENDPOINT)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
    }
}
