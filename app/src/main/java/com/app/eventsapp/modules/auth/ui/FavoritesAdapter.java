package com.app.eventsapp.modules.auth.ui;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.app.eventsapp.R;
import com.app.eventsapp.core.managers.PicassoImageManager;
import com.app.eventsapp.modules.postline.models.Post;
import com.squareup.picasso.Picasso;

import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Grigory Kalyashov on 23.02.2017.
 *
 * Адаптер для списка избранного
 */
public class FavoritesAdapter extends BaseAdapter
{
    private List<Post> posts;
    private LayoutInflater inflater;

    public FavoritesAdapter(Context context)
    {
        posts = new ArrayList<>();
        inflater = LayoutInflater.from(context);
    }

    public FavoritesAdapter(List<Post> posts, Context context)
    {
        this.posts = posts;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return posts.size();
    }

    @Override
    public Object getItem(int i)
    {
        return posts.get(i);
    }

    @Override
    public long getItemId(int i)
    {
        return posts.get(i).getId();
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup)
    {
        view = inflater.inflate(R.layout.favorite_post_layout, null);

        Post post = posts.get(i);

        TextView title = (TextView) view.findViewById(R.id.favorite_post_title);
        ImageView preview = (ImageView) view.findViewById(R.id.favorite_post_poster);

        title.setText(post.getName());

        String posterURL = post.getPreviewUrl();

        if (!StringUtils.isEmpty(posterURL)) {
            PicassoImageManager.getInstance()
                    .loadResource(posterURL, preview, Picasso.Priority.HIGH);
        }

        return view;
    }

    public void setPosts(List<Post> posts)
    {
        this.posts = posts;
    }

    public void addPosts(List<Post> posts)
    {
        this.posts.addAll(posts);
        notifyDataSetChanged();
    }

    public Post getPost(int i)
    {
        return posts.get(i);
    }

    public void clear()
    {
        this.posts.clear();
        notifyDataSetChanged();
    }
}