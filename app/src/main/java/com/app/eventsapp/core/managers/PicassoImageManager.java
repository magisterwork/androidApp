package com.app.eventsapp.core.managers;

import android.content.Context;
import android.widget.ImageView;

import com.app.eventsapp.core.app.EventsApp;
import com.squareup.picasso.Callback;
import com.squareup.picasso.LruCache;
import com.squareup.picasso.Picasso;

/**
 * Created by Grigory Kalyashov on 15.11.2016.
 *
 * Менеджер для работы с картинками, на основе библиотеки Picasso
 */
public class PicassoImageManager
{
    private static final long PICASSO_DISK_CACHE_SIZE = 1024 * 1024 * 50;
    private static final int PICASSO_MEMORY_CACHE_SIZE = 1024 * 1024 * 30;

    private static volatile PicassoImageManager instance;

    private Picasso picasso;

    private PicassoImageManager()
    {
        Context contextApp = EventsApp.getAppContext();

        Picasso.Builder builder = new Picasso.Builder(contextApp);
        builder
                //.downloader(new OkHttpDownloader(contextApp, PICASSO_DISK_CACHE_SIZE))
                .memoryCache(new LruCache(PICASSO_MEMORY_CACHE_SIZE));

        picasso = builder.build();
    }

    //TODO
    public static PicassoImageManager getInstance()
    {
        if (instance == null)
        {
            synchronized (PicassoImageManager.class)
            {
                if (instance == null)
                    instance = new PicassoImageManager();
            }

            //instance.setDebugMode(true);
        }
        return PicassoImageManager.instance;
    }

    /**
     * Используется для загрузки ресурса (картинки)
     * @param url - target url
     * @param des - ImageView - ресурс назначения
     * @param priority - приоритет загрузки
     */
    public void loadResource(final String url, final ImageView des, Picasso.Priority priority)
    {
        picasso
                .load(url)
                .priority(priority)
                .into(des, new Callback()
                {
                    @Override
                    public void onSuccess()
                    {

                    }

                    @Override
                    public void onError()
                    {

                    }
                });
    }

    /**
     * Установить debug mode
     * в котором указывается индикатор откуда загружена картника
     * зеленый - ram
     * синий - hdd
     * красный - network
     * @param flag - флаг установки в debug mode
     */
    public void setDebugMode(boolean flag)
    {
        picasso.setIndicatorsEnabled(flag);
        picasso.setLoggingEnabled(flag);
    }

}