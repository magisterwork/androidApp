package com.app.eventsapp.entities;

import org.apache.commons.lang3.StringUtils;

/**
 * Created by Grigory Kalyashov on 31.10.2016.
 *
 * Местоположение события
 */
public class EventPlace
{
    private String country;
    private String city;
    // адрес содержит улицу, дом и т.п.
    private String address;
    private Double latitude;
    private Double longitude;

    public EventPlace(String country, String city, String address, Double latitude, Double longitude)
    {
        this.country = country;
        this.city = city;
        this.address = address;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public String getCountry()
    {
        return country;
    }

    public void setCountry(String country)
    {
        this.country = country;
    }

    public String getCity()
    {
        return city;
    }

    public void setCity(String city)
    {
        this.city = city;
    }

    public String getAddress()
    {
        return address;
    }

    public void setAddress(String address)
    {
        this.address = address;
    }

    public Double getLatitude()
    {
        return latitude;
    }

    public void setLatitude(Double latitude)
    {
        this.latitude = latitude;
    }

    public Double getLongitude()
    {
        return longitude;
    }

    public void setLongitude(Double longitude)
    {
        this.longitude = longitude;
    }

    @Override
    public String toString()
    {
        return StringUtils.defaultString(address, "");
    }

    public boolean hasCoordinates()
    {
        return this.longitude != null && this.latitude != null;
    }
}
