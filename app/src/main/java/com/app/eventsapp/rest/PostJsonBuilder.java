package com.app.eventsapp.rest;

import com.app.eventsapp.modules.postline.models.Post;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;
import java.util.Calendar;

/**
 * Created by Grigory Kalyashov on 07.11.2016.
 */
public class PostJsonBuilder
{
    public static final String POST_ATTR_ID_API_KEY = "id";
    public static final String POST_ATTR_TITLE_API_KEY = "name";
    public static final String POST_ATTR_DESCRIPTION_API_KEY = "description";
    public static final String POST_ATTR_START_EVENT_TIME = "beginTime";
    public static final String POST_ATTR_END_EVENT_TIME = "endTime";

    public static Gson buildPostGson()
    {
        GsonBuilder gsonBuilder =
                new GsonBuilder()
                .registerTypeAdapter(Calendar.class, new DateTimeAdapter());

        return gsonBuilder.create();

    }

}
