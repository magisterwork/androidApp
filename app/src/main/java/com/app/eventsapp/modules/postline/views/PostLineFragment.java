package com.app.eventsapp.modules.postline.views;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.app.eventsapp.R;
import com.app.eventsapp.core.base.BaseFragment;
import com.app.eventsapp.core.di.components.MainActivityComponent;
import com.app.eventsapp.modules.postline.PagingPostLineAdapter;
import com.app.eventsapp.modules.postline.models.Post;
import com.app.eventsapp.modules.postline.presenters.PostLinePresenterImpl;

import java.util.List;

import javax.inject.Inject;

/**
 * Фрагмент для отображения ленты постов
 */
public class PostLineFragment extends BaseFragment implements PostLineFragmentView
{
    @Inject
    public PostLinePresenterImpl presenter;

    private Activity activity;
    private PagingPostLineAdapter adapter;
    private RecyclerView recyclerView;
    private View rootView;

    public PostLineFragment()
    {}

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        this.getComponent(MainActivityComponent.class).inject(this);

    }

    @Override
    public void onResume()
    {
        super.onResume();
        presenter.init(this);
        presenter.onResume();
    }

    @Override
    public void onAttach(Context context)
    {
        super.onAttach(context);
        this.activity = (Activity) context;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        if(rootView == null)
        {
            rootView = inflater.inflate(R.layout.postline_fragment, container, false);

            recyclerView = (RecyclerView) rootView.findViewById(R.id.postRecyclerView);
            recyclerView.setSaveEnabled(true);
            GridLayoutManager recyclerViewLayoutManager = new GridLayoutManager(getActivity(), 1);
            recyclerViewLayoutManager.supportsPredictiveItemAnimations();
            recyclerView.setLayoutManager(recyclerViewLayoutManager);
            recyclerView.setAdapter(adapter);
        }

        return rootView;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState)
    {
        // TODO реализовать свой OnScrollListener
        //recyclerView.addOnScrollListener();
    }

    @Override
    public void setRecyclerViewAdapter(List<Post> posts)
    {
        if(adapter == null)
        {
            PagingPostLineAdapter adapter = new PagingPostLineAdapter(posts);
            recyclerView.setAdapter(adapter);
        }
        else
        {
            presenter.addPostsToAdapter(posts);
        }
    }

    @Override
    public void addPostsToAdapter(List<Post> posts)
    {
        adapter.addPosts(posts);
    }

    @Override
    public void openPostDetails()
    {

    }
}
