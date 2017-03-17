package com.app.eventsapp.modules.places.rest;

import com.app.eventsapp.modules.places.models.Place;
import com.app.eventsapp.rest.base.RestService;
import com.app.eventsapp.rest.request.RequestCallback;
import com.app.eventsapp.rest.request.RequestListener;

import java.util.List;

import retrofit2.Call;

/**
 * Created by Grigory Kalyashov on 16.03.2017.
 *
 * Сервис для работы с заведениями
 */
public class PlacesService extends RestService
{
    /**
     * Получить список заведений
     *
     * @param requestListener - слушатель
     * @param offset - сдвиг
     * @param count - количество заведений
     */
    public void getPlacesList(RequestListener<List<Place>> requestListener,
                                 int offset, int count)
    {
        PlacesAPI placesAPI = buildRetrofit().create(PlacesAPI.class);
        Call<List<Place>> call = placesAPI.getPlaces(offset, count);
        call.enqueue(new RequestCallback<>(requestListener));
    }

    /**
     * Получить заведение по id
     *
     * @param requestListener - слушатель
     * @param id - идентификатор заведения
     */
    public void getPlaceById(RequestListener<Place> requestListener, long id)
    {
        PlacesAPI placesAPI = buildRetrofit().create(PlacesAPI.class);
        Call<Place> call = placesAPI.getPlace(id);
        call.enqueue(new RequestCallback<>(requestListener));
    }
}
