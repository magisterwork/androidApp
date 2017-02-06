package com.app.eventsapp.modules.postline.recyclerview;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.app.eventsapp.R;
import com.app.eventsapp.core.managers.PicassoImageManager;
import com.app.eventsapp.modules.postline.models.Post;
import com.app.eventsapp.utils.DateTimeHelper;
import com.squareup.picasso.Picasso;

import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by Grigory Kalyashov on 31.10.2016.
 *
 * Адаптер для RecyclerView
 */
public class PagingPostLineAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>
{
    private List<Post> posts;

    public PagingPostLineAdapter()
    {
        posts = new ArrayList<>();
    }

    //TODO что если посты не пришли с сервера
    public PagingPostLineAdapter(List<Post> posts)
    {
        this.posts = posts;
    }

    public void setPosts(List<Post> posts)
    {
        this.posts  = posts;
    }

    public void addPosts(List<Post> posts)
    {
        this.posts.addAll(posts);
        notifyDataSetChanged();
    }

    public Post getPost(int id)
    {
        return posts.get(id);
    }

    public void clear()
    {
        this.posts.clear();
        notifyDataSetChanged();
    }

    private static class PostViewHolder extends RecyclerView.ViewHolder
    {
        private TextView name;
        private ImageView poster;
        private TextView address;
        private TextView description;
        private TextView beginTime;

        public PostViewHolder(View itemView)
        {
            super(itemView);

            name = (TextView) itemView.findViewById(R.id.post_title);
            poster = (ImageView) itemView.findViewById(R.id.post_poster);
            address = (TextView) itemView.findViewById(R.id.post_address);
            description = (TextView) itemView.findViewById(R.id.post_description);
            beginTime = (TextView) itemView.findViewById(R.id.post_begin_time);
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        View view = LayoutInflater.from(parent.getContext()).inflate(
                R.layout.post_layout, parent, false);

        return new PostViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position)
    {
        //TODO binding
        PostViewHolder postViewHolder = (PostViewHolder) holder;
        Post post = posts.get(position);

        postViewHolder.name.setText(post.getName());
        postViewHolder.description.setText(post.getDescription());
        postViewHolder.address.setText(post.getAddress().toString());

        Calendar postBeginTime = post.getBeginTime();
        postViewHolder.beginTime.setText(DateTimeHelper.formatEventDate(postBeginTime));

        String posterURL = post.getImageUrl();

        // TODO если нет картинки
        if (!StringUtils.isEmpty(posterURL))
            PicassoImageManager.getInstance().loadResource(posterURL, postViewHolder.poster, Picasso.Priority.HIGH);
    }

    @Override
    public int getItemCount()
    {
        return posts.size();
    }
}
