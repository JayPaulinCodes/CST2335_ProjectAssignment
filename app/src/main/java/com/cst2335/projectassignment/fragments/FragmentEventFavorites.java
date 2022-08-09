package com.cst2335.projectassignment.fragments;

import android.content.Context;
import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.cst2335.projectassignment.R;
import com.cst2335.projectassignment.activities.ActivityFavorites;
import com.cst2335.projectassignment.activities.JActivity;
import com.cst2335.projectassignment.objects.Event;
import com.cst2335.projectassignment.utils.TicketQuery;

import java.util.ArrayList;


/**
 * Fragment class for the event favorites fragment
 *
 * @see JFragment
 * @author Jacob Paulin
 */
public class FragmentEventFavorites extends JFragment {

    private Context context;
    private JActivity jActivity;

    private ArrayList<Event> events;

    /**
     * Required empty public constructor
     */
    public FragmentEventFavorites() {}

    /**
     * Used to set the context
     * @param context Context
     * @return this
     */
    public FragmentEventFavorites context(Context context) {
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

        events = TicketQuery.getFavoriteEvents(((ActivityFavorites) requireActivity()).getDB());
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

        return inflater.inflate(R.layout.fragment_event_favorites, container, false);
    }

    /**
     * Accessor method for variable events
     *
     * @return value of variable events
     */
    public final ArrayList<Event> getEventList() { return events; }
}