package com.app.eventsapp.modules.postline;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.app.eventsapp.R;
import com.app.eventsapp.modules.postline.models.Post;

import java.util.ArrayList;
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

    public PagingPostLineAdapter(List<Post> posts)
    {
        this.posts = posts;
    }

    public void setPosts(List<Post> posts)
    {
        this.posts  = posts;
    }

    static class PostViewHolder extends RecyclerView.ViewHolder
    {
        private TextView name;
        private ImageView poster;
        private TextView address;
        private TextView description;
        private TextView beginTime;
        private TextView endTime;

        public PostViewHolder(View itemView)
        {
            super(itemView);

            name = (TextView) itemView.findViewById(R.id.post_title);
            poster = (ImageView) itemView.findViewById(R.id.post_poster);
            address = (TextView) itemView.findViewById(R.id.post_address);
            description = (TextView) itemView.findViewById(R.id.post_description);
            beginTime = (TextView) itemView.findViewById(R.id.post_begin_time);
            endTime = (TextView)itemView.findViewById(R.id.post_end_time);
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
        PostViewHolder postViewHolder = (PostViewHolder) holder;
        Post post = posts.get(position);

        postViewHolder.name.setText(post.getName());
        postViewHolder.description.setText(post.getDescription());
        postViewHolder.address.setText(post.getAddress().toString());
        postViewHolder.beginTime.setText(post.getBeginTime().getTime().toString());
        postViewHolder.endTime.setText(post.getBeginTime().getTime().toString());
    }

    @Override
    public int getItemCount()
    {
        return posts.size();
    }
}
