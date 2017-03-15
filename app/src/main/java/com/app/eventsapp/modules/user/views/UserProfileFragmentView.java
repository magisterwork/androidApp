package com.app.eventsapp.modules.user.views;

/**
 * Created by Grigory Kalyashov on 19.02.2017.
 */

public interface UserProfileFragmentView
{
    /**
     * Успешный выход из профиля
     */
    void successLogOut();

    /**
     * Не получилось выйти из профиля
     */
    void failureLogOut();
}
