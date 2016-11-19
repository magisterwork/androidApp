package com.app.eventsapp.core.cache;

import android.util.LruCache;

/**
 * Created by Grigory Kalyashov on 19.11.2016.
 *
 * Простой LruCache
 */
public class Cache
{
    private static Cache instance;
    private LruCache<Object, Object> lruCache;

    private int cacheSize = 1024;

    private Cache()
    {
        lruCache = new LruCache<>(cacheSize);
    }

    public static Cache getInstance()
    {
        if(instance == null)
        {
            instance = new Cache();
        }

        return instance;
    }

    /**
     * @return LruCache<key, value>, где
     * key - ключ для кэширования значения value
     */
    public LruCache<Object, Object> getLruCache()
    {
        return lruCache;
    }
}
