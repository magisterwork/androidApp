package com.app.eventsapp.core.base;

/**
 * Created by Grigory Kalyashov on 30.10.2016.
 *
 * Интерфейс для презентеров
 */
public interface BaseFragmentPresenter<T>
{
    void init(T view);
}