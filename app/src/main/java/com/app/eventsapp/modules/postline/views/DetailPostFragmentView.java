package com.app.eventsapp.modules.postline.views;

import com.app.eventsapp.modules.postline.models.Post;

/**
 * Created by Grigory Kalyashov on 13.11.2016.
 *
 * Детальная информация о событии
 */
public interface DetailPostFragmentView
{
    /**
     * Отметить событие как "избранное"
     */
    void markEventAsFavorite();

    /**
     * Отметить событие как не "избранное"
     */
    void markEventAsNotFavorite();

    /**
     * Успешное добавление в избранное
     */
    void onSuccessfulAddToFavorites();

    /**
     * Ошибка при добавлении в избранное
     */
    void onUnsuccessfulAddToFavorites();

    /**
     * Установить текущий пост
     * @param post - текущий пост (событие)
     */
    void setCurrentPost(Post post);

    /**
     * Отобразить детали поста
     * @param post - пост
     */
    void setPostDetails(final Post post);

    /**
     * Ошибка при получении поста
     */
    void onFailureGetPost();
}
