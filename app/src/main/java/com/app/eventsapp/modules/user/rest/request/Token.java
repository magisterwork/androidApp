package com.app.eventsapp.modules.user.rest.request;

/**
 * Created by Grigory Kalyashov on 19.02.2017.
 *
 * Google токен
 */
public class Token
{
    private String token;

    public Token(String token)
    {
        this.token = token;
    }

    public String getToken()
    {
        return token;
    }

    public void setToken(String token)
    {
        this.token = token;
    }
}
