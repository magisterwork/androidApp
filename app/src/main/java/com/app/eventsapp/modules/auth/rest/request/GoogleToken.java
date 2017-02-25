package com.app.eventsapp.modules.auth.rest.request;

/**
 * Created by Grigory Kalyashov on 19.02.2017.
 *
 * Google токен
 */
public class GoogleToken
{
    private String gtoken;

    public GoogleToken(String gtoken)
    {
        this.gtoken = gtoken;
    }

    public String getGtoken()
    {
        return gtoken;
    }

    public void setGtoken(String gtoken)
    {
        this.gtoken = gtoken;
    }
}
