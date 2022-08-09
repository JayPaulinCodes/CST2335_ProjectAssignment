package com.cst2335.projectassignment.fragments;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.cst2335.projectassignment.activities.JActivity;

import java.util.Objects;

/**
 * Custom created Fragment class which we use to
 * call the JActivity.onFragmentLoaded method during
 * the Fragment's onStart method
 *
 * @see Fragment
 * @author Jacob Paulin
 */
public class JFragment extends Fragment {

    /**
     * This method is called once the fragment is loaded
     * and visible to the user
     */
    @Override
    public void onStart() {
        super.onStart();

        ((JActivity) requireActivity()).onFragmentLoaded(this);
    }

}
