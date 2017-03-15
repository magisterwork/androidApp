package com.app.eventsapp.modules.user.presenters;

import com.app.eventsapp.core.base.BaseFragmentPresenter;
import com.app.eventsapp.modules.user.util.UserDataManager;
import com.app.eventsapp.modules.user.views.UserProfileFragmentView;
import com.google.android.gms.common.api.GoogleApiClient;

/**
 * Created by Grigory Kalyashov on 19.02.2017.
 */
public interface UserProfilePresenter extends BaseFragmentPresenter<UserProfileFragmentView>
{
    /**
     * Обработка события нажатия на кнопку выхода
     * из профиля
     *
     * @param userDataManager
     * @param googleApiClient
     */
    void onLogOutBtnClick(UserDataManager userDataManager, GoogleApiClient googleApiClient);
}
