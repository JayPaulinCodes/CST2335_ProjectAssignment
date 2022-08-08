package com.cst2335.projectassignment.fragments;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.cst2335.projectassignment.R;
import com.cst2335.projectassignment.activities.ActivityFavorites;
import com.cst2335.projectassignment.activities.ActivityHome;
import com.cst2335.projectassignment.activities.ActivitySearch;
import com.cst2335.projectassignment.utils.TicketQuery;

// TODO: Add JavaDoc Comment
public class FragmentHomeButton extends JFragment {

    private static final String ARG_DESTINATION_PAGE = ActivityHome.ARG_DESTINATION_PAGE;
    private static final String ARG_BUTTON_LABEL = ActivityHome.ARG_BUTTON_LABEL;
    private static final String ARG_DESCRIPTION = ActivityHome.ARG_DESCRIPTION;

    private Context context;
    private String destinationPage;
    private String buttonLabel;
    private String description;

    // TODO: Add JavaDoc Comment
    public FragmentHomeButton() {
        // Required empty public constructor
    }

    // TODO: Add JavaDoc Comment
    public FragmentHomeButton context(Context context) {
        this.context = context;
        return this;
    }

    // TODO: Add JavaDoc Comment
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if(savedInstanceState == null){
            // Get back arguments
            if(getArguments() != null) {
                destinationPage = getArguments().getString(ARG_DESTINATION_PAGE);
                buttonLabel = getArguments().getString(ARG_BUTTON_LABEL);
                description = getArguments().getString(ARG_DESCRIPTION);
            }
        }

    }

    // TODO: Add JavaDoc Comment
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home_button, container, false);

        TextView homeButton_text = view.findViewById(R.id.fragment_homeButton_text);
        Button homeButton_button = view.findViewById(R.id.fragment_homeButton_button);

        homeButton_text.setText(description);
        homeButton_button.setText(buttonLabel);

        homeButton_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i(this.getClass().getName(), "11");
                Intent goToPage = null;

                switch (destinationPage) {

                    case TicketQuery.ACTIVITY_SEARCH:
                        if (context != null) goToPage = new Intent(context, ActivitySearch.class);
                        break;

                    case TicketQuery.ACTIVITY_FAVORITES:
                        if (context != null) goToPage = new Intent(context, ActivityFavorites.class);
                        break;

                }

                if (goToPage != null) startActivity(goToPage);
            }
        });

        // Inflate the layout for this fragment
        return view;
    }
}