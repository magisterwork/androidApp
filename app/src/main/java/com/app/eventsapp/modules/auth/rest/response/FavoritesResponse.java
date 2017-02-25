package com.app.eventsapp.modules.auth.rest.response;

import com.app.eventsapp.modules.postline.models.Post;

import java.util.List;

/**
 * Created by Grigory Kalyashov on 23.02.2017.
 */

public class FavoritesResponse
{
    private String status;
    private String token;
    private List<Post> favorites;

    public FavoritesResponse(String status, String token, List<Post> favorites)
    {
        this.status = status;
        this.token = token;
        this.favorites = favorites;
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

    public List<Post> getFavorites()
    {
        return favorites;
    }

    public void setFavorites(List<Post> favorites)
    {
        this.favorites = favorites;
    }
}
