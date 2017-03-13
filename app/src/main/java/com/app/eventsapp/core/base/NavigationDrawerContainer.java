package com.app.eventsapp.core.base;

import android.support.v7.widget.Toolbar;

/**
 * Created by Grigory Kalyashov on 21.01.2017.
 *
 * Интерфейс для фрагмента/активити с navigation drawer
 */
public interface NavigationDrawerContainer
{
    /**
     * Инициализация бокового меню
     *
     * @param toolbar - Toolbar
     * @param checkedItem - выбранный элемент меню
     */
    void initDrawer(Toolbar toolbar, int checkedItem);

    /**
     *  Установить LOCK MODE для DrawerLayout
     */
    void setDrawerLockMode();
}
