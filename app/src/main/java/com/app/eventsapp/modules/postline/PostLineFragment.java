package com.app.eventsapp.modules.postline;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.app.eventsapp.R;
import com.app.eventsapp.core.base.BaseFragment;
import com.app.eventsapp.modules.postline.presenters.PostLinePresenterImpl;
import com.app.eventsapp.modules.postline.views.PostLineFragmentView;

import javax.inject.Inject;

/**
 * Фрагмент для отображения ленты постов
 */
public class PostLineFragment extends BaseFragment implements PostLineFragmentView
{
    @Inject
    PostLinePresenterImpl presenter;

    public PostLineFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_main, container, false);
    }

    @Override
    public void openPostDetails() {

    }
}
