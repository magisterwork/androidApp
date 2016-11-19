package com.app.eventsapp.modules.postline.views;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.app.eventsapp.R;
import com.app.eventsapp.core.base.BaseFragment;
import com.app.eventsapp.core.cache.PostCacheUtils;
import com.app.eventsapp.core.managers.PicassoImageManager;
import com.app.eventsapp.modules.postline.models.Post;
import com.app.eventsapp.utils.PostUtils;
import com.squareup.picasso.Picasso;

import org.apache.commons.lang3.StringUtils;

/**
 * Created by Grigory Kalyashov on 13.11.2016.
 *
 * Фрагмент для детальной информации о посте
 */
public class DetailPostFragment extends BaseFragment implements DetailPostFragmentView
{
    public static String FRAGMENT_TAG = "DetailPostFragment";

    public DetailPostFragment()
    {
        super();
    }

    @NonNull
    @Override
    protected View initRootView(LayoutInflater inflater, ViewGroup container)
    {
        rootView = inflater.inflate(R.layout.detailpost_fragment, container, false);
        return rootView;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        super.onCreateView(inflater, container, savedInstanceState);

        initToolbar();
        initFAB();

        Long postId = this.getArguments().getLong(PostUtils.postIdBundleKey);
        Post post = PostCacheUtils.getPostFromCache(postId);

        setPostDetails(rootView, post);

        return rootView;
    }

    public void onBackPressed(FragmentActivity activity)
    {

    }

    private void setPostDetails(View view, Post post)
    {
        TextView postTitle = (TextView) view.findViewById(R.id.detail_post_title);
        ImageView poster = (ImageView) view.findViewById(R.id.detail_post_poster);
        TextView address = (TextView) view.findViewById(R.id.detail_post_address);
        TextView description = (TextView) view.findViewById(R.id.detail_post_description);
        TextView beginTime = (TextView) view.findViewById(R.id.detail_post_begin_time);
        TextView endTime = (TextView) view.findViewById(R.id.detail_post_end_time);

        postTitle.setText(post.getName());
        address.setText(post.getAddress().toString());
        description.setText(post.getDescription());
        beginTime.setText(post.getBeginTime().getTime().toString());
        endTime.setText(post.getBeginTime().getTime().toString());

        String posterURL = post.getImageUrl();

        if (!StringUtils.isEmpty(posterURL))
            PicassoImageManager.getInstance().loadResource(posterURL, poster, Picasso.Priority.HIGH);
    }

    private void initToolbar()
    {
        Toolbar toolbar = (Toolbar) rootView.findViewById(R.id.scrolling_toolbar);
        context.setSupportActionBar(toolbar);
        context.getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        context.getSupportActionBar().setDisplayShowHomeEnabled(true);

        final DetailPostFragment detailPostFragment = this;

        toolbar.setNavigationOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                FragmentManager fm = context.getSupportFragmentManager();

                if (fm.getBackStackEntryCount() > 0)
                {
                    fm.beginTransaction()
                            .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_CLOSE)
                            .detach(detailPostFragment)
                            .commit();
                    fm.popBackStack(DetailPostFragment.FRAGMENT_TAG, FragmentManager.POP_BACK_STACK_INCLUSIVE);
                }
                else
                {
                    context.onBackPressed();
                }
            }
        });

    }

    private void initFAB()
    {
        FloatingActionButton fab = (FloatingActionButton) rootView.findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Добавить в избранное", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }
}
