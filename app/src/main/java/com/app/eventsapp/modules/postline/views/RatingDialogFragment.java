package com.app.eventsapp.modules.postline.views;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.TextView;

import com.app.eventsapp.R;
import com.varunest.sparkbutton.SparkButton;

/**
 * Created by Grigory Kalyashov on 04.03.2017.
 *
 * Диалог для оценки события
 */
public class RatingDialogFragment extends DialogFragment
{
    private SparkButton ratingUp;
    private SparkButton ratingDown;
    private TextView ratingTextView;
    private static final String RATING_KEY = "rating";

    public RatingDialogFragment()
    {}

    public static RatingDialogFragment newInstanse(String rating)
    {
        RatingDialogFragment fragment = new RatingDialogFragment();
        Bundle args = new Bundle();
        args.putString(RATING_KEY, rating);
        fragment.setArguments(args);

        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        return inflater.inflate(R.layout.rating_dialog_layout, container);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState)
    {
        super.onViewCreated(view, savedInstanceState);

        ratingTextView = (TextView) view.findViewById(R.id.dialog_event_rating);
        String rating = getArguments().getString(RATING_KEY);
        ratingTextView.setText(rating);
    }
}
