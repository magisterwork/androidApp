package com.app.eventsapp.rest.postapi;

import com.app.eventsapp.rest.adapters.DateTimeAdapter;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.Calendar;

/**
 * Created by Grigory Kalyashov on 07.11.2016.
 */
public class PostJsonBuilder
{
    public static Gson buildPostGson()
    {
        GsonBuilder gsonBuilder =
                new GsonBuilder()
                .registerTypeAdapter(Calendar.class, new DateTimeAdapter());

        return gsonBuilder.create();

    }
}
