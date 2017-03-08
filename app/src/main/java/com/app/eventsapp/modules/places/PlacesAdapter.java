package com.app.eventsapp.modules.places;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.app.eventsapp.R;
import com.app.eventsapp.core.managers.PicassoImageManager;
import com.app.eventsapp.modules.places.models.Place;
import com.squareup.picasso.Picasso;

import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Grigory Kalyashov on 08.03.2017.
 *
 * Адаптер для списка заведений
 */
public class PlacesAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>
{
    private List<Place> places;

    public PlacesAdapter()
    {
        places = new ArrayList<>();
    }

    public PlacesAdapter(List<Place> places)
    {
        this.places = places;
    }

    public void setPlaces(List<Place> places)
    {
        this.places = places;
    }

    public void addPlaces(List<Place> places)
    {
        this.places.addAll(places);
        notifyDataSetChanged();
    }

    public Place getPlace(int id)
    {
        return places.get(id);
    }

    public void clear()
    {
        this.places.clear();
        notifyDataSetChanged();
    }

    private static class PlaceViewHolder extends RecyclerView.ViewHolder
    {
        private TextView placeName;
        private ImageView image;

        public PlaceViewHolder(View itemView)
        {
            super(itemView);

            placeName = (TextView) itemView.findViewById(R.id.place_post_title);
            image = (ImageView) itemView.findViewById(R.id.place_post_poster);
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        View view = LayoutInflater.from(parent.getContext()).inflate(
                R.layout.place_post_layout, parent, false);

        return new PlaceViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position)
    {
        PlaceViewHolder postViewHolder = (PlaceViewHolder) holder;
        Place place = places.get(position);

        postViewHolder.placeName.setText(place.getName());

        String imageUrl = place.getImageUrl();

        if (!StringUtils.isEmpty(imageUrl))
        {
            PicassoImageManager.getInstance().loadResource(imageUrl,
                    postViewHolder.image, Picasso.Priority.HIGH);
        }
    }

    @Override
    public int getItemCount()
    {
        return places.size();
    }
}
