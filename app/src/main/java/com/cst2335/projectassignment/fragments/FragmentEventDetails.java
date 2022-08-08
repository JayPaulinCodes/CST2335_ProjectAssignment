package com.cst2335.projectassignment.fragments;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.cst2335.projectassignment.R;
import com.cst2335.projectassignment.activities.ActivitySearch;
import com.cst2335.projectassignment.activities.JActivity;
import com.cst2335.projectassignment.objects.Event;
import com.cst2335.projectassignment.utils.DownloadImageTask;
import com.cst2335.projectassignment.utils.OpenHelper;
import com.cst2335.projectassignment.utils.TicketQuery;

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

        Log.i("EVENT DETAILS", "onCreateView: " + event.getId());

        ImageButton view_closeButton = view.findViewById(R.id.fragment_eventDetails_closeButton);
        ImageView view_image = view.findViewById(R.id.fragment_eventDetails_image);
        TextView view_name = view.findViewById(R.id.fragment_eventDetails_name);
        TextView view_priceRange = view.findViewById(R.id.fragment_eventDetails_priceRange);
        TextView view_dateTime = view.findViewById(R.id.fragment_eventDetails_dateTime);
        TextView view_info = view.findViewById(R.id.fragment_eventDetails_info);
        Button view_urlButton = view.findViewById(R.id.fragment_eventDetails_urlButton);
        Button view_favoriteButton = view.findViewById(R.id.fragment_eventDetails_favoriteButton);

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

        SQLiteDatabase db = ((ActivitySearch) getActivity()).getDB();

        boolean isFavorite = TicketQuery.isEventFavorite(db, event.getId());

        if (isFavorite) view_favoriteButton.setText(R.string.fragment_eventDetails_favoriteRemove);
        else view_favoriteButton.setText(R.string.fragment_eventDetails_favoriteAdd);

        view_favoriteButton.setOnClickListener(v -> {
            boolean isEventFavorite = TicketQuery.isEventFavorite(db, event.getId());

            Button button = v.findViewById(R.id.fragment_eventDetails_favoriteButton);

            TicketQuery.setEventFavorite(db, event.getId(), !isEventFavorite);

            if (!isEventFavorite) {
                button.setText(R.string.fragment_eventDetails_favoriteRemove);
                Toast.makeText(getActivity(), R.string.message_addedToFavorites, Toast.LENGTH_LONG).show();
            } else {
                button.setText(R.string.fragment_eventDetails_favoriteAdd);
                Toast.makeText(getActivity(), R.string.message_removedFromFavorites, Toast.LENGTH_LONG).show();
            }
        });

        view_closeButton.setOnClickListener(v -> {
            ActivitySearch activity = (ActivitySearch) getActivity();
            // Load Fragments
            FragmentManager fragmentManager = getActivity().getSupportFragmentManager();

            FragmentEventSearch fragment_eventSearch = new FragmentEventSearch().context(getActivity());
            Bundle arguments_eventSearch = new Bundle();

            // Handle City Shit
            String lastSearch_city = activity.getSharedPreferences().getString(TicketQuery.PREFERENCE_LAST_SEARCH_CITY, activity.getCurrentCity());
            int lastSearch_radius = activity.getSharedPreferences().getInt(TicketQuery.PREFERENCE_LAST_SEARCH_RADIUS, 100);


            arguments_eventSearch.putString(activity.ARG_CITY, lastSearch_city);
            arguments_eventSearch.putInt(activity.ARG_RADIUS, lastSearch_radius);

            fragment_eventSearch.setArguments(arguments_eventSearch);

            fragmentManager
                    .beginTransaction()
                    .replace(R.id.activity_search_frame, fragment_eventSearch)
                    .commit();

        });

        return view;
    }
}