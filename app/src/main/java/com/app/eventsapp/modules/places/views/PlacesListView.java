package com.app.eventsapp.modules.places.views;

import com.app.eventsapp.modules.places.models.Place;

import java.util.List;

/**
 * Created by Grigory Kalyashov on 05.03.2017.
 */
public interface PlacesListView
{
    void clearAdapter();
    void openPlaceDetails(int position);
    void addPlacesToAdapter(List<Place> places);
    void showProgressBar();
    void hideProgressBar();
    void onErrorLoading();
    void onFailureLoading();
    void setRecyclerViewAdapter(List<Place> places);
}
