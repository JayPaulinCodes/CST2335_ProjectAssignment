package com.cst2335.projectassignment.utils;

import android.annotation.SuppressLint;
import android.content.Context;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.AsyncTask;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

/**
 * Class used to determine the current city the user is in
 *
 * @deprecated No longer used
 */
public class CityName extends AsyncTask<Location, Integer, List<Address>> {

    @SuppressLint("StaticFieldLeak")
    private final Context context;

    /**
     * Constructor for the CityName class
     *
     * @deprecated No longer used
     */
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
