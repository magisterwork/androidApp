package com.app.eventsapp.modules.auth.rest.request;

/**
 * Created by Grigory Kalyashov on 23.02.2017.
 */

public class FavoriteRq
{
    private String token;
    private Long eventId;

    public FavoriteRq(String token, Long eventId)
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

    public Long getEventId()
    {
        return eventId;
    }

    public void setEventId(Long eventId)
    {
        this.eventId = eventId;
    }
}
