package com.cst2335.projectassignment.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.cst2335.projectassignment.R;
import com.cst2335.projectassignment.activities.ActivityHome;


public class FragmentHomeButton extends Fragment {

    private static final String ARG_DESTINATION_PAGE = ActivityHome.ARG_DESTINATION_PAGE;
    private static final String ARG_BUTTON_LABEL = ActivityHome.ARG_BUTTON_LABEL;
    private static final String ARG_DESCRIPTION = ActivityHome.ARG_DESCRIPTION;

    private String destinationPage;
    private String buttonLabel;
    private String description;

    public FragmentHomeButton() {
        // Required empty public constructor
    }

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

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home_button, container, false);

        TextView messageHereText = view.findViewById(R.id.detailsFragment_message_text);
        TextView messageIdText = view.findViewById(R.id.detailsFragment_message_id);
        CheckBox sentMessageCheckbox = view.findViewById(R.id.detailsFragment_sent_message_checkbox);
        Button hideButton = view.findViewById(R.id.detailsFragment_hide_button);

        messageHereText.setText(messageText);
        messageIdText.setText(String.format("ID=%d", messageId));
        sentMessageCheckbox.setChecked((messageType == MessageType.SEND.toString()));



        hideButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getParentFragmentManager()
                        .beginTransaction()
                        .remove(getParentFragmentManager().findFragmentById(R.id.chatRoom_frame_layout))
                        .commit();
            }
        });

        // Inflate the layout for this fragment
        return view;
    }
}