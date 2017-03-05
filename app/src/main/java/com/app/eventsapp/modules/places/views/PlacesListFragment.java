package com.app.eventsapp.modules.places.views;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.app.eventsapp.R;
import com.app.eventsapp.core.base.BaseFragment;
import com.app.eventsapp.modules.auth.ui.FavoritesAdapter;
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
    private ListView favoritesList;

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
            adapter = new PlacesAdapter(context);
            adapter.addPlaces(PlacesHelper.getPlaces());
        }

        favoritesList = (ListView) rootView.findViewById(R.id.places_list);
        favoritesList.setAdapter(adapter);

        return rootView;
    }
}
