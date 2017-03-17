package com.app.eventsapp.modules.places.presenters;

import com.app.eventsapp.modules.places.models.Place;
import com.app.eventsapp.modules.places.rest.PlacesService;
import com.app.eventsapp.modules.places.views.PlacesListView;
import com.app.eventsapp.rest.request.RequestListener;

import java.util.List;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Response;

/**
 * Created by Grigory Kalyashov on 17.03.2017.
 */
public class PlacesListPresenterImpl implements PlacesListPresenter
{
    private PlacesListView view;

    private int offset = 0;
    private final int count = 20;
    private int totalItemsCount = 0;
    private PlacesService placesService;

    @Inject
    public PlacesListPresenterImpl(PlacesService placesService)
    {
        this.placesService = placesService;
    }

    @Override
    public void init(PlacesListView view)
    {
        this.view = view;
    }

    @Override
    public void onResume(int eventsCount)
    {
        totalItemsCount = eventsCount;
        offset = totalItemsCount;
        sendPlacesRequest(offset, count, true);
    }

    @Override
    public void refresh()
    {
        view.clearAdapter();
        offset = 0;
        totalItemsCount = 0;
        sendPlacesRequest(offset, count, false);
    }

    @Override
    public void onLoadMore()
    {
        sendPlacesRequest(offset, count, true);
    }

    @Override
    public void onItemClick(int position)
    {
        view.openPlaceDetails(position);
    }

    @Override
    public void addPlacesToAdapter(List<Place> places)
    {
        view.addPlacesToAdapter(places);
    }

    private void sendPlacesRequest(int offset, final int count, boolean isNeedShowProgressBar)
    {
        if(offset <= totalItemsCount)
        {
            if (isNeedShowProgressBar)
            {
                view.showProgressBar();
            }

            placesService.getPlacesList(new RequestListener<List<Place>>()
            {
                @Override
                public void onSuccess(Call<List<Place>> call, Response<List<Place>> response)
                {
                    view.hideProgressBar();
                    view.setRecyclerViewAdapter(response.body());
                    int loadedEventsCount = response.body().size();
                    totalItemsCount += loadedEventsCount;
                }

                @Override
                public void onErrorResponse(Call<List<Place>> call, Response<List<Place>> response)
                {
                    view.hideProgressBar();
                    view.onErrorLoading();
                }

                @Override
                public void onFailure(Call<List<Place>> call, Throwable t)
                {
                    view.hideProgressBar();
                    view.onFailureLoading();
                }
            }, offset, count);

            this.offset+=count;
        }
    }
}
