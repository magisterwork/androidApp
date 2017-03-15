package com.app.eventsapp.modules.user.views;

import com.app.eventsapp.modules.postline.models.Post;

import java.util.List;

/**
 * Created by Grigory Kalyashov on 23.02.2017.
 *
 * Избранное пользователя
 */
public interface UserFavoritesView
{
    /**
     * Создаить адаптер для списка избранного и наполнить данными
     *
     * @param favorites - список с избранными событиями
     */
    void setRecyclerViewAdapter(List<Post> favorites);

    /**
     * Ошибка загрузки избранных события
     */
    void onErrorLoading();

    /**
     * Отобразить индикатор загрузки
     */
    void showProgressBar();

    /**
     * Скрыть индикатор загрузки
     */
    void hideProgressBar();

    /**
     * Отобразить сообщение о необходимости залогиниться
     */
    void showNeedAuthMessage();
}
