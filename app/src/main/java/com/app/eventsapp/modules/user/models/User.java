package com.app.eventsapp.modules.user.models;

import com.google.android.gms.auth.api.signin.GoogleSignInAccount;

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
    private String photoUrl;

    public User()
    {}

    public User(String firstName, String secondName, String email)
    {
        this.firstName = firstName;
        this.secondName = secondName;
        this.email = email;
    }

    public User(GoogleSignInAccount account)
    {
        this.firstName = account.getGivenName();
        this.secondName = account.getFamilyName();
        this.email = account.getEmail();
        this.photoUrl = account.getPhotoUrl().toString();
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

    public String getFullName()
    {
        return firstName + " " + secondName;
    }

    public String getPhotoUrl()
    {
        return photoUrl;
    }

    public void setPhotoUrl(String photoUrl)
    {
        this.photoUrl = photoUrl;
    }
}
