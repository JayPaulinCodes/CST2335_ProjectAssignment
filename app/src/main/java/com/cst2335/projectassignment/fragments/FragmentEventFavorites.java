package com.cst2335.projectassignment.fragments;

import android.content.Context;
import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.cst2335.projectassignment.R;
import com.cst2335.projectassignment.activities.ActivityFavorites;
import com.cst2335.projectassignment.activities.JActivity;
import com.cst2335.projectassignment.objects.Event;
import com.cst2335.projectassignment.utils.OpenHelper;
import com.cst2335.projectassignment.utils.TicketQuery;

import java.util.ArrayList;


// TODO: Add JavaDoc Comment
public class FragmentEventFavorites extends JFragment {

    private Context context;
    private JActivity jActivity;

    private ArrayList<Event> events;

    // TODO: Add JavaDoc Comment
    public FragmentEventFavorites() {
        // Required empty public constructor
    }

    // TODO: Add JavaDoc Comment
    public FragmentEventFavorites context(Context context) {
        this.context = context;
        this.jActivity = (JActivity) context;
        return this;
    }

    // TODO: Add JavaDoc Comment
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        events = TicketQuery.getFavoriteEvents(((ActivityFavorites) getActivity()).getDB());
    }

    // TODO: Add JavaDoc Comment
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_event_favorites, container, false);

        return view;
    }

    // TODO: Add JavaDoc Comment
    public final ArrayList<Event> getEventList() { return events; }
}