package com.app.eventsapp.modules.postline;

import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.app.eventsapp.R;
import com.app.eventsapp.core.base.BaseFragment;
import com.app.eventsapp.modules.postline.presenters.PostLinePresenterImpl;
import com.app.eventsapp.modules.postline.views.PostLineFragmentView;
import com.app.eventsapp.utils.PostsHelper;

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
                             Bundle savedInstanceState)
    {
        View rootView = inflater.inflate(R.layout.postline_fragment, container, false);

        initRecyclerView(rootView);
        return rootView;
    }

    @Override
    public void openPostDetails() {

    }

    private void initRecyclerView(View v)
    {
        RecyclerView recyclerView = (RecyclerView) v.findViewById(R.id.postRecyclerView);

        PagingPostLineAdapter adapter = new PagingPostLineAdapter(PostsHelper.getPost());

        GridLayoutManager recyclerViewLayoutManager = new GridLayoutManager(getActivity(), 1);
        recyclerViewLayoutManager.supportsPredictiveItemAnimations();

        recyclerView.setSaveEnabled(true);
        recyclerView.setLayoutManager(recyclerViewLayoutManager);
        recyclerView.setAdapter(adapter);

    }
}
