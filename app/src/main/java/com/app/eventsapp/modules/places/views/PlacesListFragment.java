package com.app.eventsapp.modules.places.views;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.app.eventsapp.R;
import com.app.eventsapp.core.base.BaseFragment;
import com.app.eventsapp.modules.places.PlacesAdapter;
import com.app.eventsapp.modules.places.PlacesHelper;

/**
 * Created by Grigory Kalyashov on 05.03.2017.
 *
 * Список заведений
 */
public class PlacesListFragment extends BaseFragment implements  PlacesListView
{
    public static String FRAGMENT_TAG = "PlacesListFragment";
    private PlacesAdapter adapter;
    private RecyclerView placesRecyclerView;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }

    @NonNull
    @Override
    protected View initRootView(LayoutInflater inflater, ViewGroup container)
    {
        rootView = inflater.inflate(R.layout.places_list_fragment, container, false);

        if(adapter == null)
        {
            adapter = new PlacesAdapter();
            adapter.addPlaces(PlacesHelper.getPlaces());
        }

        placesRecyclerView = (RecyclerView) rootView.findViewById(R.id.places_list);
        placesRecyclerView.setSaveEnabled(true);
        GridLayoutManager recyclerViewLayoutManager = new GridLayoutManager(getActivity(), 1);
        recyclerViewLayoutManager.supportsPredictiveItemAnimations();
        placesRecyclerView.setLayoutManager(recyclerViewLayoutManager);
        placesRecyclerView.setAdapter(adapter);

        return rootView;
    }
}
