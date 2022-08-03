package com.cst2335.projectassignment.fragments;

import androidx.fragment.app.Fragment;

import com.cst2335.projectassignment.activities.JActivity;

// TODO: Add JavaDoc Comments
public class JFragment extends Fragment {

    // TODO: Add JavaDoc Comments
    @Override
    public void onStart() {
        super.onStart();

        ((JActivity) getActivity()).onFragmentLoaded(this);
    }

}
