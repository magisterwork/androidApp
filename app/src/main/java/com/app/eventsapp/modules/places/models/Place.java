package com.app.eventsapp.modules.places.models;

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
    private String type;

    public Place()
    {}

    public Place(Long id, String name, String imageUrl, String address, String type)
    {
        this.id = id;
        this.name = name;
        this.imageUrl = imageUrl;
        this.address = address;
        this.type = type;
    }

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

    public String getType()
    {
        return type;
    }

    public void setType(String type)
    {
        this.type = type;
    }
}
