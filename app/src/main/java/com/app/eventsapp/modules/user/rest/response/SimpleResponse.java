package com.app.eventsapp.modules.user.rest.response;

/**
 * Created by Grigory Kalyashov on 23.02.2017.
 *
 * Простой ответ, содержит токен и статус
 */
public class SimpleResponse
{
    private String status;
    private String token;

    public SimpleResponse(String status, String token)
    {
        this.status = status;
        this.token = token;
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
}
