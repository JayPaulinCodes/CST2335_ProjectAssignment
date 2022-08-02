package com.cst2335.projectassignment.activities;

import android.content.Context;
import android.location.Address;
import android.location.Criteria;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.util.TypedValue;

import androidx.annotation.StringRes;
import androidx.appcompat.app.AppCompatActivity;

import com.cst2335.projectassignment.utils.HTTPRequest;

import org.json.JSONObject;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

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

    // TODO: Add JavaDoc Comment
    public final String getCurrentCity() {
        String result = null;

        LocationManager locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        Criteria criteria = new Criteria();
        String provider = locationManager.getBestProvider(criteria, false);

        // TODO: Ensure proper permissions

        Location location = locationManager.getLastKnownLocation(provider);

        if (location != null) {
            double lat = location.getLatitude();
            double lng = location.getLongitude();

            Geocoder geoCoder = new Geocoder(this, Locale.getDefault());

            try {
                List<Address> address = geoCoder.getFromLocation(lat, lng, 1);
                result = address.get(0).getLocality();
            } catch (Exception exception) {
                result = null;
            }
        }

        return result;
    }

    // TODO: Add JavaDoc Comment
    public final JSONObject performHttpRequest(String url) {
        String result = null;
        JSONObject resultJSON = null;
        HTTPRequest httpRequest = new HTTPRequest();

        httpRequest.execute(url);

        try {
            result = httpRequest.get();
            resultJSON = new JSONObject(result);
        } catch (Exception exception) {}

        return resultJSON;
    }

    // TODO: Add JavaDoc Comment
    public final JSONObject doHttpRequest(String city, Integer radius) {
        return performHttpRequest(HTTPRequest.url(city, radius));
    }

    // TODO: Add JavaDoc Comment
    public final JSONObject doHttpRequest(String id) {
        return performHttpRequest(HTTPRequest.url(id));
    }

}
