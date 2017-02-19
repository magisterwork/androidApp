package com.app.eventsapp.modules.auth.models;

/**
 * Created by Grigory Kalyashov on 19.02.2017.
 *
 * Пользователь
 */
public class User
{
    private String firstName;
    private String secondName;
    private String email;

    public User()
    {}

    public User(String firstName, String secondName, String email)
    {
        this.firstName = firstName;
        this.secondName = secondName;
        this.email = email;
    }

    public String getFirstName()
    {
        return firstName;
    }

    public void setFirstName(String firstName)
    {
        this.firstName = firstName;
    }

    public String getSecondName()
    {
        return secondName;
    }

    public void setSecondName(String secondName)
    {
        this.secondName = secondName;
    }

    public String getEmail()
    {
        return email;
    }

    public void setEmail(String email)
    {
        this.email = email;
    }
}
