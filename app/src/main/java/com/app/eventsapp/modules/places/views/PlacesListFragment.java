package com.app.eventsapp.modules.places.views;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.app.eventsapp.R;
import com.app.eventsapp.core.base.BaseFragment;
import com.app.eventsapp.modules.places.PlacesAdapter;
import com.app.eventsapp.modules.places.models.Place;
import com.app.eventsapp.modules.places.presenters.PlacesListPresenterImpl;
import com.app.eventsapp.modules.places.rest.PlacesService;

import java.util.List;

import javax.inject.Inject;

/**
 * Created by Grigory Kalyashov on 05.03.2017.
 *
 * Список заведений
 */
public class PlacesListFragment extends BaseFragment implements PlacesListView
{
    public static String FRAGMENT_TAG = "PlacesListFragment";

    @Inject
    public PlacesListPresenterImpl presenter = new PlacesListPresenterImpl(new PlacesService());

    private PlacesAdapter adapter;
    private RecyclerView placesRecyclerView;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }

    @Override
    public void onResume()
    {
        super.onResume();

        presenter.init(this);
        presenter.onResume(adapter.getItemCount());
    }

    @NonNull
    @Override
    protected View initRootView(LayoutInflater inflater, ViewGroup container)
    {
        rootView = inflater.inflate(R.layout.places_list_fragment, container, false);

        if(adapter == null)
        {
            adapter = new PlacesAdapter();
        }

        placesRecyclerView = (RecyclerView) rootView.findViewById(R.id.places_list);
        placesRecyclerView.setSaveEnabled(true);
        GridLayoutManager recyclerViewLayoutManager = new GridLayoutManager(getActivity(), 1);
        recyclerViewLayoutManager.supportsPredictiveItemAnimations();
        placesRecyclerView.setLayoutManager(recyclerViewLayoutManager);
        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(
                placesRecyclerView.getContext(),  recyclerViewLayoutManager.getOrientation());
        placesRecyclerView.addItemDecoration(dividerItemDecoration);
        placesRecyclerView.setAdapter(adapter);


        return rootView;
    }

    @Override
    public void clearAdapter()
    {

    }

    @Override
    public void openPlaceDetails(int position) {

    }

    @Override
    public void addPlacesToAdapter(List<Place> places)
    {
        adapter.addPlaces(places);
    }

    @Override
    public void showProgressBar()
    {

    }

    @Override
    public void hideProgressBar()
    {

    }

    @Override
    public void onErrorLoading()
    {

    }

    @Override
    public void onFailureLoading()
    {

    }

    @Override
    public void setRecyclerViewAdapter(List<Place> places)
    {
        if(adapter == null)
        {
            adapter = new PlacesAdapter(places);
            placesRecyclerView.setAdapter(adapter);
        }
        else
        {
            adapter.addPlaces(places);
        }
    }
}
