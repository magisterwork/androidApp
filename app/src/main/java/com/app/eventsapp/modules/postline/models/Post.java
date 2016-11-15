package com.app.eventsapp.modules.postline.models;

import java.util.Calendar;

import com.app.eventsapp.entities.Address;

/**
 * Created by Grigory Kalyashov on 31.10.2016.
 */
public class Post
{
    private Long id;
    private String name;
    private String description;
    private Address address;
    private Calendar beginTime;
    private Calendar endTime;
    private String imageUrl;

    public Post(Long id, String name, String description, Address address, Calendar beginTime, Calendar endTime, String imageUrl)
    {
        this.id = id;
        this.name = name;
        this.description = description;
        this.address = address;
        this.beginTime = beginTime;
        this.endTime = endTime;
        this.imageUrl = imageUrl;
    }

    public Post(Long id, String name, String description, Address address, Calendar beginTime, Calendar endTime)
    {
        this.id = id;
        this.name = name;
        this.description = description;
        this.address = address;
        this.beginTime = beginTime;
        this.endTime = endTime;
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

    public String getDescription()
    {
        return description;
    }

    public void setDescription(String description)
    {
        this.description = description;
    }

    public Address getAddress()
    {
        return address;
    }

    public void setAddress(Address address)
    {
        this.address = address;
    }

    public Calendar getBeginTime()
    {
        return beginTime;
    }

    public void setBeginTime(Calendar beginTime)
    {
        this.beginTime = beginTime;
    }

    public Calendar getEndTime()
    {
        return endTime;
    }

    public void setEndTime(Calendar endTime)
    {
        this.endTime = endTime;
    }

    public String getImageUrl()
    {
        return imageUrl;
    }

    public void setImageUrl(String poster)
    {
        this.imageUrl = imageUrl;
    }
}
