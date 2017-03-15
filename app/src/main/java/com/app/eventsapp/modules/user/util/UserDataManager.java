package com.app.eventsapp.modules.user.util;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;

import com.app.eventsapp.modules.user.models.User;

/**
 * Created by Grigory Kalyashov on 19.02.2017.
 *
 * Сохраняет и управляет данными пользователя
 */
public class UserDataManager
{
    private SharedPreferences pref;
    private SharedPreferences.Editor editor;
    private int PRIVATE_MODE = 0;
    private static final String PREFER_FILE_NAME = "userDataPref";
    private static final String IS_USER_LOGIN = "IsUserLogin";
    private Context context;

    public static final String KEY_FIRST_NAME = "first_name";
    public static final String KEY_SECOND_NAME = "second_name";
    public static final String KEY_PROFILE_IMAGE_URL = "profile_image_url";
    public static final String KEY_EMAIL = "email";
    public static final String KEY_USER_TOKEN = "user_token";

    @SuppressLint("CommitPrefEdits")
    public UserDataManager(Context c)
    {
        this.context = c;
        pref = context.getSharedPreferences(PREFER_FILE_NAME, PRIVATE_MODE);
        editor = pref.edit();
    }

    /**
     * Сохранить пользователя
     * @param user - пользователь
     */
    public void saveUserData(User user)
    {
        editor.putBoolean(IS_USER_LOGIN, true);
        editor.putString(KEY_FIRST_NAME, user.getFirstName());
        editor.putString(KEY_SECOND_NAME, user.getSecondName());
        editor.putString(KEY_PROFILE_IMAGE_URL, user.getPhotoUrl());
        editor.putString(KEY_EMAIL, user.getEmail());
        editor.commit();
    }

    /**
     * Получить данные пользователя
     * @return user - сохраненный пользователь
     */
    public User getUserData()
    {
        User user = new User();
        user.setFirstName(pref.getString(KEY_FIRST_NAME, null));
        user.setSecondName(pref.getString(KEY_SECOND_NAME, null));
        user.setPhotoUrl(pref.getString(KEY_PROFILE_IMAGE_URL, null));
        user.setEmail(pref.getString(KEY_EMAIL, null));

        return user;
    }

    /**
     * Очистить данные пользователя из Shared Preferences
     */
    public void clearUserData()
    {
        editor.clear();
        editor.commit();
    }

    /**
     * @return залогинен ли пользователь
     */
    public boolean isUserLoggedIn()
    {
        return pref.getBoolean(IS_USER_LOGIN, false);
    }

    /**
     * @return токен пользователя
     */
    public String getUserToken()
    {
        return pref.getString(KEY_USER_TOKEN, null);
    }

    /**
     * Сохранить токен пользователя
     * @param userToken - токен с сервера
     */
    public void saveUserToken(String userToken)
    {
        editor.putString(KEY_USER_TOKEN, userToken);
        editor.commit();
    }
}
