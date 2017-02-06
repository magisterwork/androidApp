package com.app.eventsapp.modules.postline.views;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.app.eventsapp.R;
import com.app.eventsapp.core.base.BaseFragment;
import com.app.eventsapp.core.di.components.MainActivityComponent;
import com.app.eventsapp.modules.postline.recyclerview.OnLoadMoreListener;
import com.app.eventsapp.modules.postline.recyclerview.PagingPostLineAdapter;
import com.app.eventsapp.modules.postline.models.Post;
import com.app.eventsapp.modules.postline.presenters.PostLinePresenterImpl;
import com.app.eventsapp.modules.postline.recyclerview.RecyclerOnItemClickListener;
import com.app.eventsapp.utils.PostUtils;

import static com.app.eventsapp.MainActivity.FRAGMENT_CONTAINER;

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

    public PostLineFragment()
    {}

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState)
    {
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
        super.onCreateView(inflater, container, savedInstanceState);

        recyclerView = (RecyclerView) rootView.findViewById(R.id.postRecyclerView);
        recyclerView.setSaveEnabled(true);
        GridLayoutManager recyclerViewLayoutManager = new GridLayoutManager(getActivity(), 1);
        recyclerViewLayoutManager.supportsPredictiveItemAnimations();
        recyclerView.setLayoutManager(recyclerViewLayoutManager);
        recyclerView.setAdapter(adapter);

        initSwipeRefreshLayout(rootView);
        return rootView;
    }

    public void initSwipeRefreshLayout(View root)
    {
        final SwipeRefreshLayout refreshLayout = (SwipeRefreshLayout) root.findViewById(R.id.refreshPostLine);
        final PostLineFragmentView postLineFragmentView = this;

        refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener()
        {
            @Override
            public void onRefresh()
            {
                new Thread(new Runnable()
                {
                    @Override
                    public void run()
                    {
                        presenter.init(postLineFragmentView);
                        presenter.refresh();
                    }
                }).run();

                refreshLayout.setRefreshing(false);
            }
        });
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState)
    {
        recyclerView.addOnItemTouchListener(new RecyclerOnItemClickListener(this.context, recyclerView, new RecyclerOnItemClickListener.OnItemClickListener()
        {
            @Override
            public void onItemClick(View view, int position)
            {
                presenter.onItemClick(position);
            }

            @Override
            public void onItemLongClick(View view, int position)
            {

            }
        }));

        setOnScrollListener();
    }

    @NonNull
    @Override
    protected View initRootView(LayoutInflater inflater, ViewGroup container)
    {
        rootView = inflater.inflate(R.layout.postline_fragment, container, false);
        return rootView;
    }

    @Override
    public void setRecyclerViewAdapter(List<Post> posts)
    {
        if(adapter == null)
        {
            adapter = new PagingPostLineAdapter(posts);
            recyclerView.setAdapter(adapter);
        }
        else
        {
            presenter.addPostsToAdapter(posts);
        }
    }

    @Override
    public void setOnScrollListener()
    {
        recyclerView.addOnScrollListener(
                new OnLoadMoreListener()
                {
                    @Override
                    public void onLoadMore()
                    {
                        presenter.onLoadMore();
                    }
                }
        );
    }

    @Override
    public void showProgressBar()
    {
        ProgressBar progressBar = (ProgressBar) activity.findViewById(R.id.postline_progress_bar);
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgressBar()
    {
        ProgressBar progressBar = (ProgressBar) activity.findViewById(R.id.postline_progress_bar);
        progressBar.setVisibility(View.INVISIBLE);
    }

    @Override
    public void onErrorLoading()
    {
        Toast.makeText(context,context.getResources().getString(R.string.error_loading),
                Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onFailureLoading()
    {
        Toast.makeText(context,context.getResources().getString(R.string.error_loading),
                Toast.LENGTH_SHORT).show();
    }

    @Override
    public void addPostsToAdapter(List<Post> posts)
    {
        adapter.addPosts(posts);
    }

    public void clearAdapter()
    {
        adapter.clear();
    }

    @Override
    public void openPostDetails(int position)
    {
        Post post = ((PagingPostLineAdapter) recyclerView.getAdapter()).getPost(position);

        //TODO как лучше всего создавать фрагменты
        DetailPostFragment detailPostFragment = (DetailPostFragment)
                fragmentManager.findFragmentByTag("DetailPostFragment");

        if (detailPostFragment == null)
        {
            detailPostFragment = new DetailPostFragment();
        }

        Bundle postBundle = new Bundle();
        postBundle.putLong(PostUtils.postIdBundleKey, post.getId());
        detailPostFragment.setArguments(postBundle);

        fragmentManager.beginTransaction()
                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                .replace(FRAGMENT_CONTAINER, detailPostFragment)
                .addToBackStack(DetailPostFragment.FRAGMENT_TAG)
                .commit();

    }
}
