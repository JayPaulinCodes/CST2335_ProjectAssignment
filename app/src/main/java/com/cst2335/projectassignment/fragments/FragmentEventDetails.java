package com.cst2335.projectassignment.fragments;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.cst2335.projectassignment.R;
import com.cst2335.projectassignment.activities.ActivitySearch;
import com.cst2335.projectassignment.activities.JActivity;
import com.cst2335.projectassignment.objects.Event;
import com.cst2335.projectassignment.utils.DownloadImageTask;

// TODO: Add JavaDoc Comment
public class FragmentEventDetails extends JFragment {

//    private static final String ARG_CITY = ActivitySearch.ARG_CITY;

    private Context context;
    private Event event;

    // TODO: Add JavaDoc Comment
    public FragmentEventDetails() {
        // Required empty public constructor
    }

    // TODO: Add JavaDoc Comment
    public FragmentEventDetails context(Context context) {
        this.context = context;
        return this;
    }

    // TODO: Add JavaDoc Comment
    public FragmentEventDetails event(Event event) {
        this.event = event;
        return this;
    }

    // TODO: Add JavaDoc Comment
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        if (getArguments() != null) {
//            mParam1 = getArguments().getString(ARG_PARAM1);
//            mParam2 = getArguments().getString(ARG_PARAM2);
//        }
    }

    // TODO: Add JavaDoc Comment
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_event_details, container, false);

        ImageButton view_closeButton = view.findViewById(R.id.fragment_eventDetails_closeButton);
        ImageView view_image = view.findViewById(R.id.fragment_eventDetails_image);
        TextView view_name = view.findViewById(R.id.fragment_eventDetails_name);
        TextView view_priceRange = view.findViewById(R.id.fragment_eventDetails_priceRange);
        TextView view_dateTime = view.findViewById(R.id.fragment_eventDetails_dateTime);
        TextView view_info = view.findViewById(R.id.fragment_eventDetails_info);
        Button view_urlButton = view.findViewById(R.id.fragment_eventDetails_urlButton);
        Button view_favoriteButton = view.findViewById(R.id.fragment_eventDetails_favoriteButton);

        new DownloadImageTask(view_image).execute(event.getImage());

        return view;
    }
}