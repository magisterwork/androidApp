package com.app.eventsapp.modules.places;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.app.eventsapp.R;
import com.app.eventsapp.core.managers.PicassoImageManager;
import com.app.eventsapp.modules.places.models.Place;
import com.app.eventsapp.modules.postline.models.Post;
import com.squareup.picasso.Picasso;

import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Grigory Kalyashov on 05.03.2017.
 */
public class PlacesAdapter extends BaseAdapter
{
    private List<Place> places;
    private LayoutInflater inflater;

    public PlacesAdapter(Context context)
    {
        places = new ArrayList<>();
        inflater = LayoutInflater.from(context);
    }

    public PlacesAdapter(List<Place> places, Context context)
    {
        this.places = places;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount()
    {
        return places.size();
    }

    @Override
    public Object getItem(int i)
    {
        return places.get(i);
    }

    @Override
    public long getItemId(int i)
    {
        return places.get(i).getId();
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup)
    {
        view = inflater.inflate(R.layout.place_post_layout, null);

        Place place = places.get(i);

        TextView title = (TextView) view.findViewById(R.id.place_post_title);
        ImageView poster = (ImageView) view.findViewById(R.id.place_post_poster);

        title.setText(place.getName());

        String posterURL = place.getImageUrl();

        if (!StringUtils.isEmpty(posterURL))
        {
            PicassoImageManager.getInstance()
                    .loadResource(posterURL, poster, Picasso.Priority.HIGH);
        }

        return view;
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

    public Place getPlace(int i)
    {
        return places.get(i);
    }

    public void clear()
    {
        this.places.clear();
        notifyDataSetChanged();
    }
}
