package com.cst2335.projectassignment.fragments;

import android.content.Context;
import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.cst2335.projectassignment.R;
import com.cst2335.projectassignment.activities.ActivitySearch;
import com.cst2335.projectassignment.activities.JActivity;
import com.cst2335.projectassignment.objects.Event;
import com.cst2335.projectassignment.utils.HTTPRequest;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Fragment class for the event search fragment
 *
 * @see JFragment
 * @author Jacob Paulin
 */
public class FragmentEventSearch extends JFragment {

    private static final String ARG_CITY = ActivitySearch.ARG_CITY;
    private static final String ARG_RADIUS = ActivitySearch.ARG_RADIUS;

    private Context context;
    private JActivity jActivity;
    private String city;
    private Integer radius;

    private ArrayList<Event> events;

    /**
     * Required empty public constructor
     */
    public FragmentEventSearch() {}

    /**
     * Used to set the context
     * @param context Context
     * @return this
     */
    public FragmentEventSearch context(Context context) {
        this.context = context;
        this.jActivity = (JActivity) context;
        return this;
    }

    /**
     * Method which is triggered on the creation of the fragment
     * @param savedInstanceState
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            city = getArguments().getString(ARG_CITY);
            radius = getArguments().getInt(ARG_RADIUS);
        }

        if (radius == null || radius <= 0) radius = 100;

        if (city != null) {
            try {
                JSONObject queryResults = jActivity.doHttpRequest(city, radius);
                JSONArray queryResultsArray = queryResults.getJSONObject("_embedded").getJSONArray("events");

                events = HTTPRequest.processHTTPJSONArray(queryResultsArray);
            } catch (Exception exception) { exception.printStackTrace(); }
        }
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

        return inflater.inflate(R.layout.fragment_event_search, container, false);
    }

    /**
     * Accessor method for variable events
     *
     * @return value of variable events
     */
    public final ArrayList<Event> getEventList() { return events; }

}