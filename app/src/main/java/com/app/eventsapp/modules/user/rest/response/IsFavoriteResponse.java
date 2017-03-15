package com.app.eventsapp.modules.user.rest.response;

/**
 * Created by Grigory Kalyashov on 27.02.2017.
 */

public class IsFavoriteResponse
{
    private String status;
    private String token;
    private boolean favorite;

    public IsFavoriteResponse(String status, String token, boolean favorite)
    {
        this.status = status;
        this.token = token;
        this.favorite = favorite;
    }

    public String getStatus()
    {
        return status;
    }

    public void setStatus(String status)
    {
        this.status = status;
    }

    public String getToken()
    {
        return token;
    }

    public void setToken(String token)
    {
        this.token = token;
    }

    public boolean isFavorite()
    {
        return favorite;
    }

    public void setFavorite(boolean favorite)
    {
        this.favorite = favorite;
    }
}
