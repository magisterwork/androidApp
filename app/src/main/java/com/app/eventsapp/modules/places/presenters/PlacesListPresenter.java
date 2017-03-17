package com.app.eventsapp.modules.places.presenters;

import com.app.eventsapp.core.base.BaseFragmentPresenter;
import com.app.eventsapp.modules.places.models.Place;
import com.app.eventsapp.modules.places.views.PlacesListView;

import java.util.List;


/**
 * Created by Grigory Kalyashov on 17.03.2017.
 *
 * Презентер для списка заведений
 */
public interface PlacesListPresenter extends BaseFragmentPresenter<PlacesListView>
{
    void onResume(int placesCount);
    void refresh();
    void onLoadMore();
    void onItemClick(int position);
    void addPlacesToAdapter(List<Place> places);
}
