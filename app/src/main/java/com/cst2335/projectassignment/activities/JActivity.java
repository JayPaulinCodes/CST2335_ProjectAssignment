package com.cst2335.projectassignment.activities;

import android.os.Bundle;
import android.util.Log;
import android.util.TypedValue;

import androidx.annotation.StringRes;
import androidx.appcompat.app.AppCompatActivity;

// TODO: Add JavaDoc Comment
public class JActivity extends AppCompatActivity {

    // TODO: Add JavaDoc Comment
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    // TODO: Add JavaDoc Comment
    public final void log(String message) {
        Log.i(this.getLocalClassName(), message);
    }

    // TODO: Add JavaDoc Comment
    public final String word(@StringRes int string, Boolean capitalize) {
        return (capitalize) ? capitalize(getString(string)) : getString(string);
    }

    // TODO: Add JavaDoc Comment
    public static final String capitalize(String str) {
        return (str == null || str.isEmpty()) ? str : str.substring(0, 1).toUpperCase() + str.substring(1);
    }

    // TODO: Add JavaDoc Comment
    public final Float floatToDp(float flt) {
        return TypedValue.applyDimension( TypedValue.COMPLEX_UNIT_DIP, flt, JActivity.this.getResources().getDisplayMetrics() );
    }

}
