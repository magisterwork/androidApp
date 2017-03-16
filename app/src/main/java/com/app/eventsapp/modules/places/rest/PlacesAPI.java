package com.app.eventsapp.modules.places.rest;

import com.app.eventsapp.modules.places.models.Place;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by Grigory Kalyashov on 16.03.2017.
 *
 * API для получения заведений
 */
public interface PlacesAPI
{
    /**
     * Получить список заведений
     *
     * @param offset сдвиг
     * @param count количество заведений
     * @return список заведений
     */
    @GET("places/list")
    Call<List<Place>> getPlaces(@Query("offset") int offset, @Query("count") int count);

    /**
     * Получить заведение
     *
     * @param id - id заведения
     * @return заведение
     */
    @GET("places/get")
    Call<Place> getPlace(@Query("id") long id);
}
