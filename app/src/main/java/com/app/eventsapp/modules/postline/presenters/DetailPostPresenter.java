package com.app.eventsapp.modules.postline.presenters;

import android.content.Context;

import com.app.eventsapp.core.base.BaseFragmentPresenter;
import com.app.eventsapp.modules.postline.views.DetailPostFragmentView;

/**
 * Created by Grigory Kalyashov on 12.02.2017.
 */
public interface DetailPostPresenter extends BaseFragmentPresenter<DetailPostFragmentView>
{
    void showFullEventImage(Context context, String fullImageUrl);
}