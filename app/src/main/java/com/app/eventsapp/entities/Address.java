package com.app.eventsapp.entities;

/**
 * Created by Grigory Kalyashov on 31.10.2016.
 */
public class Address
{
    private String countryIso;
    private String city;
    private String street;
    private String house;

    public Address(String countryIso, String city, String street, String house)
    {
        this.countryIso = countryIso;
        this.city = city;
        this.street = street;
        this.house = house;
    }

    public String getCountryIso()
    {
        return countryIso;
    }

    public void setCountryIso(String countryIso)
    {
        this.countryIso = countryIso;
    }

    public String getCity()
    {
        return city;
    }

    public void setCity(String city)
    {
        this.city = city;
    }

    public String getStreet()
    {
        return street;
    }

    public void setStreet(String street)
    {
        this.street = street;
    }

    public String getHouse()
    {
        return house;
    }

    public void setHouse(String house)
    {
        this.house = house;
    }
}
