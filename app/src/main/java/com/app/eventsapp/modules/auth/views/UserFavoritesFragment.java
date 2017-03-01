package com.app.eventsapp.modules.auth.views;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.app.eventsapp.R;
import com.app.eventsapp.core.base.NavigationDrawerFragment;
import com.app.eventsapp.modules.auth.presenters.UserFavoritesPresenterImpl;
import com.app.eventsapp.modules.auth.session.UserSessionManager;
import com.app.eventsapp.modules.auth.ui.FavoritesAdapter;
import com.app.eventsapp.modules.postline.models.Post;
import com.app.eventsapp.modules.postline.views.DetailPostFragment;
import com.app.eventsapp.utils.PostUtils;

import java.util.List;

import javax.inject.Inject;

import static com.app.eventsapp.MainActivity.FRAGMENT_CONTAINER;

/**
 * Created by Grigory Kalyashov on 21.02.2017.
 *
 * Фрагмент избранных событий пользователя
 */
public class UserFavoritesFragment extends NavigationDrawerFragment implements UserFavoritesView
{
    public static String FRAGMENT_TAG = "UserFavoritesFragment";

    private FavoritesAdapter adapter;
    private ListView favoritesList;
    private UserSessionManager sessionManager;

    @Inject
    public UserFavoritesPresenterImpl presenter = new UserFavoritesPresenterImpl();

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }

    @NonNull
    @Override
    protected View initRootView(LayoutInflater inflater, ViewGroup container)
    {
        checkedItem = R.id.nav_favorites;

        rootView = inflater.inflate(R.layout.favorites_fragment, container, false);

        if(adapter == null)
        {
            adapter = new FavoritesAdapter(context);
        }

        initToolbar();

        sessionManager = new UserSessionManager(context);

        return rootView;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        super.onCreateView(inflater, container, savedInstanceState);

        favoritesList = (ListView) rootView.findViewById(R.id.favorites_list);
        favoritesList.setSaveEnabled(true);
        favoritesList.setAdapter(adapter);

        favoritesList.setOnItemClickListener(new AdapterView.OnItemClickListener()
        {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l)
            {
                openPostDetails(i);
            }
        });

        return rootView;
    }

    @Override
    public void onResume()
    {
        super.onResume();
        presenter.init(this);
        presenter.onResume(sessionManager);
    }

    private void initToolbar()
    {
        toolbar = (Toolbar) rootView.findViewById(R.id.favorites_toolbar);
        context.setSupportActionBar(toolbar);
        context.getSupportActionBar().setTitle(getString(R.string.liked));
    }

    @Override
    public void setRecyclerViewAdapter(List<Post> favorites)
    {
        if(adapter == null)
        {
            adapter = new FavoritesAdapter(favorites, context);
            favoritesList.setAdapter(adapter);
        }
        else
        {
            adapter.clear();
            adapter.addPosts(favorites);
        }
    }

    @Override
    public void onErrorLoading()
    {
        Toast.makeText(context,R.string.error_favorites_loading, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showProgressBar()
    {
        ProgressBar progressBar = (ProgressBar) context.findViewById(R.id.favorites_progress_bar);
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgressBar()
    {
        ProgressBar progressBar = (ProgressBar) context.findViewById(R.id.favorites_progress_bar);

        if(progressBar != null)
        {
            progressBar.setVisibility(View.INVISIBLE);
        }
    }

    private void openPostDetails(int position)
    {
        Post post = ((FavoritesAdapter) favoritesList.getAdapter()).getPost(position);

        DetailPostFragment detailPostFragment = (DetailPostFragment)
                fragmentManager.findFragmentByTag(DetailPostFragment.FRAGMENT_TAG);

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
