package com.app.eventsapp.utils;

import com.app.eventsapp.entities.Address;
import com.app.eventsapp.modules.postline.models.Post;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * Created by Grigory Kalyashov on 01.11.2016.
 */
//TODO заглушка. Удалить после создания сервиса Retrofit
public class PostsHelper
{
    public static List<Post> getPost()
    {
        List<Post> posts = new ArrayList<>();

        Address address = new Address("RU","Вологда","Городской вал","24");
        Calendar c = Calendar.getInstance();
        c.set(2017,2,1,13,43);

        for(int i = 1; i < 100; i++)
        {
            posts.add(new Post((long) i,"Пост","Описание",address,c,c));
        }


        return posts;
    }
}
