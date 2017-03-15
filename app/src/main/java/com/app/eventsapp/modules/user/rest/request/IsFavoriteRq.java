package com.app.eventsapp.modules.user.rest.request;

/**
 * Created by Grigory Kalyashov on 27.02.2017.
 */

public class IsFavoriteRq
{
    private String token;
    private long eventId;

    public IsFavoriteRq(String token, long eventId)
    {
        this.token = token;
        this.eventId = eventId;
    }


    public String getToken()
    {
        return token;
    }

    public void setToken(String token)
    {
        this.token = token;
    }

    public long getEventId()
    {
        return eventId;
    }

    public void setEventId(long eventId)
    {
        this.eventId = eventId;
    }
}
