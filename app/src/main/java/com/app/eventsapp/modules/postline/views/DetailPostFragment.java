package com.app.eventsapp.modules.postline.views;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.provider.CalendarContract;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.app.eventsapp.R;
import com.app.eventsapp.core.base.BaseFragment;
import com.app.eventsapp.core.base.DetailFragmentBase;
import com.app.eventsapp.core.cache.PostCacheUtils;
import com.app.eventsapp.core.managers.PicassoImageManager;
import com.app.eventsapp.modules.auth.session.UserSessionManager;
import com.app.eventsapp.modules.postline.models.Post;
import com.app.eventsapp.modules.postline.presenters.DetailPostPresenterImpl;
import com.app.eventsapp.rest.postapi.EventsService;
import com.app.eventsapp.utils.DateTimeHelper;
import com.app.eventsapp.utils.PostUtils;
import com.app.eventsapp.utils.ViewUtils;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.squareup.picasso.Picasso;

import org.apache.commons.lang3.StringUtils;

import java.util.Calendar;

import javax.inject.Inject;


/**
 * Created by Grigory Kalyashov on 13.11.2016.
 *
 * Фрагмент для детальной информации о посте
 */
public class DetailPostFragment extends DetailFragmentBase implements DetailPostFragmentView, OnMapReadyCallback
{
    public static String FRAGMENT_TAG = "DetailPostFragment";

    //TODO почему-то не инжектится презентер
    @Inject
    public DetailPostPresenterImpl presenter = new DetailPostPresenterImpl();

    private GoogleMap map;
    private Post currentPost;
    private UserSessionManager sessionManager;

    public DetailPostFragment()
    {
        super();
    }

    @Override
    public void onResume()
    {
        super.onResume();
        presenter.init(this);
    }

    @NonNull
    @Override
    protected View initRootView(LayoutInflater inflater, ViewGroup container)
    {
        rootView = inflater.inflate(R.layout.detailpost_fragment, container, false);

        presenter.init(this);

        sessionManager = new UserSessionManager(context);

        return rootView;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        super.onCreateView(inflater, container, savedInstanceState);

        initToolbar();

        Long postId = this.getArguments().getLong(PostUtils.postIdBundleKey);

        presenter.getPost(postId, sessionManager);

        return rootView;
    }

    @Override
    public void setPostDetails(final Post post)
    {
        TextView postTitle = (TextView) rootView.findViewById(R.id.detail_post_title);
        ImageView poster = (ImageView)  rootView.findViewById(R.id.detail_post_poster);
        TextView address = (TextView)  rootView.findViewById(R.id.detail_post_address);
        TextView description = (TextView)  rootView.findViewById(R.id.detail_post_description);
        TextView date = (TextView)  rootView.findViewById(R.id.detail_post_date);
        TextView rating = (TextView) rootView.findViewById(R.id.event_rating);

        postTitle.setText(post.getName());
        ViewUtils.hideTextViewIfNoText(post.getPlace().toString(), address);
        ViewUtils.hideTextViewIfNoText(post.getDescription(), description);
        rating.setText(post.getRate());

        String dateTime = DateTimeHelper.formatDateWithPeriod(post.getBeginTime(), post.getEndTime());

        date.setText(dateTime);

        final String previewUrl = post.getPreviewUrl();
        final String fullImageUrl = post.getImageUrl();

        if (!StringUtils.isEmpty(previewUrl))
        {
            PicassoImageManager.getInstance().loadResource(previewUrl, poster, Picasso.Priority.HIGH);
        }

        poster.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                presenter.showFullEventImage(context, fullImageUrl);
            }
        });

        Button addReminder = (Button) rootView.findViewById(R.id.add_reminder);

        addReminder.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Intent intent = new Intent(Intent.ACTION_INSERT)
                        .setData(CalendarContract.Events.CONTENT_URI)
                        .putExtra(CalendarContract.Events.TITLE, post.getName())
                        .putExtra(CalendarContract.Events.DESCRIPTION, post.getDescription())
                        .putExtra(CalendarContract.Events.EVENT_LOCATION, post.getPlace().toString())
                        .putExtra(CalendarContract.Events.AVAILABILITY, CalendarContract.Events.AVAILABILITY_BUSY);

                startActivity(intent);
            }
        });

        initMap();
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
            View mapView = rootView.findViewById(R.id.detail_map);
            ViewUtils.hideView(mapView);
        }
    }

    @Override
    public void onMapReady(GoogleMap googleMap)
    {
        map = googleMap;

        Double latitude = currentPost.getPlace().getLatitude();
        Double longitude = currentPost.getPlace().getLongitude();

        LatLng place = new LatLng(latitude, longitude);
        map.getUiSettings().setAllGesturesEnabled(false);
        map.addMarker(new MarkerOptions().position(place).title(currentPost.getName()));
        map.moveCamera( CameraUpdateFactory.newLatLngZoom(place , 17.0f) );
    }

    @Override
    public void setCurrentPost(Post post)
    {
        currentPost = post;
    }

    @Override
    public void markEventAsFavorite()
    {
        Button saveToFavorites = (Button) rootView.findViewById(R.id.add_to_favorites);

        saveToFavorites.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                presenter.deleteFromFavorites(currentPost.getId(), sessionManager);
            }
        });

        onSuccessfulAddToFavorites();
    }

    @Override
    public void markEventAsNotFavorite()
    {
        Button saveToFavorites = (Button) rootView.findViewById(R.id.add_to_favorites);

        saveToFavorites.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                presenter.saveToFavorites(currentPost.getId(), sessionManager);
            }
        });
    }

    @Override
    public void onSuccessfulAddToFavorites()
    {
        Button saveToFavorites = (Button) rootView.findViewById(R.id.add_to_favorites);
        Drawable icon = getContext().getResources().getDrawable( R.drawable.ic_bookmark_fill);
        icon.setBounds(0,0,50,50);
        saveToFavorites.setCompoundDrawables( null, icon, null, null);
    }

    @Override
    public void onUnsuccessfulAddToFavorites()
    {
        Toast.makeText(context, R.string.unsuccessful_add_to_favorires, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onFailureGetPost()
    {
        Toast.makeText(context, R.string.error_loading, Toast.LENGTH_SHORT).show();
    }
}
