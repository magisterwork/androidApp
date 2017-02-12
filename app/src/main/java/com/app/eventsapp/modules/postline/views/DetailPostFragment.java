package com.app.eventsapp.modules.postline.views;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.app.eventsapp.MockPostService;
import com.app.eventsapp.R;
import com.app.eventsapp.core.base.BaseFragment;
import com.app.eventsapp.core.cache.PostCacheUtils;
import com.app.eventsapp.core.managers.PicassoImageManager;
import com.app.eventsapp.modules.postline.models.Post;
import com.app.eventsapp.utils.DateTimeHelper;
import com.app.eventsapp.utils.PostUtils;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.squareup.picasso.Picasso;

import org.apache.commons.lang3.StringUtils;

/**
 * Created by Grigory Kalyashov on 13.11.2016.
 *
 * Фрагмент для детальной информации о посте
 */
public class DetailPostFragment extends BaseFragment implements DetailPostFragmentView, OnMapReadyCallback
{
    public static String FRAGMENT_TAG = "DetailPostFragment";

    private GoogleMap mMap;
    private Post currentPost;

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

        Long postId = this.getArguments().getLong(PostUtils.postIdBundleKey);
        currentPost = PostCacheUtils.getPostFromCache(postId);

        setPostDetails(rootView, currentPost);

        initMap();

        return rootView;
    }

    private void setPostDetails(View view, Post post)
    {
        TextView postTitle = (TextView) view.findViewById(R.id.detail_post_title);
        ImageView poster = (ImageView) view.findViewById(R.id.detail_post_poster);
        TextView address = (TextView) view.findViewById(R.id.detail_post_address);
        TextView description = (TextView) view.findViewById(R.id.detail_post_description);
        TextView date = (TextView) view.findViewById(R.id.detail_post_date);

        postTitle.setText(post.getName());
        address.setText(post.getPlace().toString());
        description.setText(post.getDescription());

        String beginTimeStr = DateTimeHelper.formatEventDate(post.getBeginTime());
        String endTimeStr = DateTimeHelper.formatEventDate(post.getEndTime());

        String dateTime = DateTimeHelper.formatDateWithPeriod(post.getBeginTime(), post.getEndTime());

        date.setText(dateTime);

        final String previewUrl = post.getPreviewUrl();
        final String fullImageUrl = post.getImageUrl();

        if (!StringUtils.isEmpty(previewUrl))
        {
            PicassoImageManager.getInstance().loadResource(previewUrl, poster, Picasso.Priority.HIGH);
        }
    }

    private void initToolbar()
    {
        Toolbar toolbar = (Toolbar) rootView.findViewById(R.id.scrolling_toolbar);
        context.setSupportActionBar(toolbar);
        context.getSupportActionBar().setTitle("");
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

    private void initMap()
    {
        SupportMapFragment mapFragment = (SupportMapFragment) getChildFragmentManager()
                .findFragmentById(R.id.map);

        if(currentPost.getPlace().hasCoordinates())
        {
            mapFragment.onLowMemory();
            mapFragment.getMapAsync(this);
        }
        else
        {
            mapFragment.getView().setVisibility(View.GONE);
        }
    }

    @Override
    public void onMapReady(GoogleMap googleMap)
    {
        mMap = googleMap;

        Double latitude = currentPost.getPlace().getLatitude();
        Double longitude = currentPost.getPlace().getLongitude();

        LatLng place = new LatLng(latitude, longitude);
        mMap.getUiSettings().setAllGesturesEnabled(false);
        mMap.addMarker(new MarkerOptions().position(place).title(currentPost.getName()));
        mMap.moveCamera( CameraUpdateFactory.newLatLngZoom(place , 18.0f) );
    }
}
