package com.app.eventsapp.modules.places.models;

import com.app.eventsapp.entities.PlaceCategory;

import java.util.List;

/**
 * Created by Grigory Kalyashov on 05.03.2017.
 *
 * Заведение
 */
public class Place
{
    private Long id;
    private String name;
    private String imageUrl;
    private String address;
    private String icon;
    private List<PlaceCategory> categories;
    private double googleRating;
    private double latitude;
    private double longitude;

    public Place()
    {}

    public Long getId()
    {
        return id;
    }

    public void setId(Long id)
    {
        this.id = id;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public String getImageUrl()
    {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl)
    {
        this.imageUrl = imageUrl;
    }

    public String getAddress()
    {
        return address;
    }

    public void setAddress(String address)
    {
        this.address = address;
    }

    public List<PlaceCategory> getCategories()
    {
        return categories;
    }

    public void setCategories(List<PlaceCategory> categories)
    {
        this.categories = categories;
    }

    public double getGoogleRating()
    {
        return googleRating;
    }

    public void setGoogleRating(double googleRating)
    {
        this.googleRating = googleRating;
    }

    public double getLatitude()
    {
        return latitude;
    }

    public void setLatitude(double latitude)
    {
        this.latitude = latitude;
    }

    public double getLongitude()
    {
        return longitude;
    }

    public void setLongitude(double longitude)
    {
        this.longitude = longitude;
    }

    public String getIcon()
    {
        return icon;
    }

    public void setIcon(String icon)
    {
        this.icon = icon;
    }
}
