package com.cst2335.projectassignment.utils;

import android.content.Context;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.AsyncTask;

import com.cst2335.projectassignment.activities.JActivity;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

// TODO: Add JavaDoc Comment
public class CityName extends AsyncTask<Location, Integer, List<Address>> {

    private Context context;

    // TODO: Add JavaDoc Comment
    public CityName(Context context) {
        this.context = context;
    }

    @Override
    protected List<Address> doInBackground(Location... locations) {
        Location location = locations[0];

        Geocoder coder = new Geocoder(context, Locale.ENGLISH);
        List<Address> results = null;

        try {
            results = coder.getFromLocation(location.getLatitude(), location.getLongitude(), 1);
        } catch (IOException e) {
            // nothing
        }
        return results;
    }
}
