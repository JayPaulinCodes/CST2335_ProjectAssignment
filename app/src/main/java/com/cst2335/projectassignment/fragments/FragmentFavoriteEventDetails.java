package com.cst2335.projectassignment.fragments;

import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.FragmentManager;

import com.cst2335.projectassignment.R;
import com.cst2335.projectassignment.activities.ActivityFavorites;
import com.cst2335.projectassignment.objects.Event;
import com.cst2335.projectassignment.utils.DownloadImageTask;
import com.cst2335.projectassignment.utils.TicketQuery;

/**
 * Fragment class for the favorite event details fragment
 *
 * @see JFragment
 * @author Jacob Paulin
 */
public class FragmentFavoriteEventDetails extends JFragment {

//    private static final String ARG_CITY = ActivitySearch.ARG_CITY;

    private Context context;
    private Event event;

    /**
     * Required empty public constructor
     */
    public FragmentFavoriteEventDetails() {}

    /**
     * Used to set the context
     * @param context Context
     * @return this
     */
    public FragmentFavoriteEventDetails context(Context context) {
        this.context = context;
        return this;
    }

    /**
     * Used to set the event
     * @param event Event
     * @return this
     */
    public FragmentFavoriteEventDetails event(Event event) {
        this.event = event;
        return this;
    }

    /**
     * Method which is triggered on the creation of the fragment
     * @param savedInstanceState
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        if (getArguments() != null) {
//            mParam1 = getArguments().getString(ARG_PARAM1);
//            mParam2 = getArguments().getString(ARG_PARAM2);
//        }
    }

    /**
     * Method which is called to create the view for the fragment
     * @param inflater LayoutInflater
     * @param container ViewGroup
     * @param savedInstanceState Bundle
     * @return View for the fragment
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_favorite_event_details, container, false);

        Log.i("EVENT DETAILS", "onCreateView: " + event.getId());

        ImageButton view_closeButton = view.findViewById(R.id.fragment_favoriteEventDetails_closeButton);
        ImageView view_image = view.findViewById(R.id.fragment_favoriteEventDetails_image);
        TextView view_name = view.findViewById(R.id.fragment_favoriteEventDetails_name);
        TextView view_priceRange = view.findViewById(R.id.fragment_favoriteEventDetails_priceRange);
        TextView view_dateTime = view.findViewById(R.id.fragment_favoriteEventDetails_dateTime);
        TextView view_info = view.findViewById(R.id.fragment_favoriteEventDetails_info);
        Button view_urlButton = view.findViewById(R.id.fragment_favoriteEventDetails_urlButton);
        Button view_favoriteButton = view.findViewById(R.id.fragment_favoriteEventDetails_favoriteButton);

        new DownloadImageTask(view_image).execute(event.getImage());
        view_name.setText(event.getName());
        if (event.getPriceRange() != null) view_priceRange.setText(event.getPriceRange().toString());
        if (event.getStartDate() != null) view_dateTime.setText(event.getStartDate().getLocalDateTimeFormatted());
        if (event.getDescription() != null) view_info.setText(event.getDescription());
        else if (event.getInfo() != null) view_info.setText(event.getInfo());
        else if (event.getAdditionalInfo() != null) view_info.setText(event.getAdditionalInfo());

        view_urlButton.setOnClickListener(v -> {
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(event.getUrl()));
            startActivity(intent);
        });

        SQLiteDatabase db = ((ActivityFavorites) requireActivity()).getDB();

        view_favoriteButton.setOnClickListener(v -> {
            TicketQuery.setEventFavorite(db, event.getId(), false);
            Toast.makeText(getActivity(), R.string.message_removedFromFavorites, Toast.LENGTH_LONG).show();

            startFavoriteActivity(v);
        });

        view_closeButton.setOnClickListener(this::startFavoriteActivity);

        return view;
    }

    private void startFavoriteActivity(View view) {
        ActivityFavorites activity = (ActivityFavorites) getActivity();
        // Load Fragments
        FragmentManager fragmentManager = requireActivity().getSupportFragmentManager();

        FragmentEventFavorites fragment_eventFavorite = new FragmentEventFavorites().context(getActivity());

        fragmentManager
                .beginTransaction()
                .replace(R.id.activity_favorites_frame, fragment_eventFavorite)
                .commit();
    }
}