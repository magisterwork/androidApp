package com.app.eventsapp.core.cache;

import com.app.eventsapp.modules.postline.models.Post;

import java.util.List;

/**
 * Created by Grigory Kalyashov on 19.11.2016.
 *
 * Содержит ф-ии для добавления и получения постов из кэша
 */
public class PostCacheUtils
{
    private static final String postCacheKey = "post_";

    /**
     * Добавить список постов в кэш
     * @param posts список постов
     */
    public static void addPostsToCache(List<Post> posts)
    {
        for (Post post : posts)
        {
            addPostToCache(post);
        }
    }

    //TODO добавление в кеш в другом потоке + обновляемый кеш

    /**
     * Добавить пост в кэш
     * @param post пост
     */
    public static void addPostToCache(Post post)
    {
        Cache.getInstance().getLruCache().put(getPostKey(post.getId()),post);
    }

    /**
     * Получить пост из кэша
     * @param id идентификатор поста, ключ
     * @return пост
     */
    public static Post getPostFromCache(Long id)
    {
        return (Post) Cache.getInstance().getLruCache().get(getPostKey(id));
    }

    private static String getPostKey(Long id)
    {
        return postCacheKey + id;
    }
}
