package com.cst2335.projectassignment.fragments;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.cst2335.projectassignment.R;
import com.cst2335.projectassignment.activities.ActivitySearch;
import com.cst2335.projectassignment.activities.JActivity;
import com.cst2335.projectassignment.objects.Event;
import com.cst2335.projectassignment.utils.EventListAdapter;
import com.cst2335.projectassignment.utils.HTTPRequest;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

// TODO: Fix JavaDoc Comment
/**
 * A simple {@link JFragment} subclass.
 */
public class FragmentEventSearch extends JFragment {

    private static final String ARG_CITY = ActivitySearch.ARG_CITY;

    private Context context;
    private JActivity jActivity;
//    private ActivitySearch activitySearch;
    private String city;
//    private EventListAdapter listAdapter;
//    private Button searchButton;
//    private EditText editText_city;
//    private EditText editText_radius;

    private ArrayList<Event> events;

    // TODO: Add JavaDoc Comment
    public FragmentEventSearch() {
        // Required empty public constructor
    }

    // TODO: Add JavaDoc Comment
    public FragmentEventSearch context(Context context) {
        this.context = context;
        this.jActivity = (JActivity) context;
//        this.activitySearch = (ActivitySearch) context;
        return this;
    }

//    public static EventSearch newInstance(String param1, String param2) {
//        EventSearch fragment = new EventSearch();
//        Bundle args = new Bundle();
//        args.putString(ARG_PARAM1, param1);
//        args.putString(ARG_PARAM2, param2);
//        fragment.setArguments(args);
//        return fragment;
//    }

    // TODO: Add JavaDoc Comment
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            city = getArguments().getString(ARG_CITY);
        }

        if (city != null) {
            try {
                JSONObject queryResults = jActivity.doHttpRequest(city, 100);
                JSONArray queryResultsArray = queryResults.getJSONObject("_embedded").getJSONArray("events");

                events = HTTPRequest.processHTTPJSONArray(queryResultsArray);
//
//
//                for (int i = 0; i < events.size(); i++) {
//                    Log.i("SSSSSSSSSS", events.get(i).getId());
//                }
            } catch (Exception exception) { exception.printStackTrace(); }
        }
    }

    // TODO: Add JavaDoc Comment
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_event_search, container, false);

        return view;
    }

    // TODO: Add JavaDoc Comment
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
//        ListView listView = getView().findViewById(R.id.fragment_eventSearch_listView);
//        listView.setAdapter(listAdapter = new EventListAdapter(events, jActivity));
//
//        searchButton = getView().findViewById(R.id.fragment_eventSearch_searchBar).findViewById(R.id.searchBar_searchButton);

//        Log.i("FRAGEVENTSEARCH", searchButton.getText().toString());
    }

    // TODO: Add JavaDoc Comment
    public final ArrayList<Event> getEventList() { return events; }
//
//    // TODO: Add JavaDoc Comment
//    public final EventListAdapter getListAdapter() { return listAdapter; }
//
//    /**
//     * Accessor method for variable searchButton
//     *
//     * @returns value of variable searchButton
//     */
//    public Button getSearchButton() { return searchButton; }


}