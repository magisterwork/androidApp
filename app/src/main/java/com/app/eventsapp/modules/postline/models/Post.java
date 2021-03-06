package com.app.eventsapp.modules.postline.models;

import java.util.Calendar;

import com.app.eventsapp.entities.EventPlace;

/**
 * Created by Grigory Kalyashov on 31.10.2016.
 */
public class Post
{
    private Long id;
    private String name;
    private String description;
    private EventPlace place;
    private String rate;
    private Calendar beginTime;
    private Calendar endTime;
    private String previewUrl;
    private String imageUrl;

    public Post(Long id, String name, String description, EventPlace place, Calendar beginTime,
                Calendar endTime, String previewUrl, String imageUrl, String rate)
    {
        this.id = id;
        this.name = name;
        this.description = description;
        this.place = place;
        this.beginTime = beginTime;
        this.endTime = endTime;
        this.previewUrl = previewUrl;
        this.imageUrl = imageUrl;
        this.rate = rate;
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

    public EventPlace getPlace()
    {
        return place;
    }

    public void setPlace(EventPlace place)
    {
        this.place = place;
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

    public String getPreviewUrl()
    {
        return previewUrl;
    }

    public void setPreviewUrl(String previewUrl)
    {
        this.previewUrl = previewUrl;
    }

    public String getRate()
    {
        return rate;
    }

    public void setRate(String rate)
    {
        this.rate = rate;
    }
}
