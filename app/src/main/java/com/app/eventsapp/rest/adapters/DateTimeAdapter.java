package com.app.eventsapp.rest.adapters;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.TimeZone;

import static com.app.eventsapp.utils.DateTimeHelper.POST_CALENDAR_PATTERN;

/**
 * Created by Grigory Kalyashov on 07.11.2016.
 */
public class DateTimeAdapter implements JsonDeserializer
{
    @Override
    public Object deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException
    {
        DateFormat format = new SimpleDateFormat(POST_CALENDAR_PATTERN);
        format.setTimeZone(TimeZone.getTimeZone("GMT"));

        try
        {
            Calendar calendar = new GregorianCalendar();
            calendar.setTime(format.parse(json.getAsString()));
            return calendar;
        }
        catch (ParseException e)
        {
            throw new JsonParseException(e);
        }
    }
}

