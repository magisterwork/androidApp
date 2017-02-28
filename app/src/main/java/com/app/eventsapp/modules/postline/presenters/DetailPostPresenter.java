package com.app.eventsapp.modules.postline.presenters;

import android.content.Context;

import com.app.eventsapp.core.base.BaseFragmentPresenter;
import com.app.eventsapp.modules.auth.session.UserSessionManager;
import com.app.eventsapp.modules.postline.views.DetailPostFragmentView;

/**
 * Created by Grigory Kalyashov on 12.02.2017.
 */
public interface DetailPostPresenter extends BaseFragmentPresenter<DetailPostFragmentView>
{
    /**
     * Отобразить полное изображение
     * @param context - конекст
     * @param fullImageUrl - url изображения
     */
    void showFullEventImage(Context context, String fullImageUrl);

    /**
     * Получить пост
     * @param id - идентификатор
     */
    void getPost(long id, UserSessionManager sessionManager);

    /**
     * Сохранить событие в избранное
     *
     * @param eventId - идентификатор события
     * @param sessionManager - менеджер сессии
     */
    void saveToFavorites(Long eventId, final UserSessionManager sessionManager);

    /**
     * Удалить событие из избранного
     *
     * @param eventId - идентификатор события
     * @param sessionManager - менеджер сессии
     */
    void removeFavorite(Long eventId, final UserSessionManager sessionManager);
}
