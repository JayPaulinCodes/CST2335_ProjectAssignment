package com.cst2335.projectassignment.activities;

import android.Manifest;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Criteria;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.util.TypedValue;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.StringRes;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;

import com.cst2335.projectassignment.R;
import com.cst2335.projectassignment.utils.HTTPRequest;
import com.cst2335.projectassignment.utils.TicketQuery;

import org.json.JSONObject;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

/**
 * Custom created AppCompatActivity class which we use to
 * add and implement multiple helper methods.
 *
 * @see AppCompatActivity
 * @author Jacob Paulin
 */
public abstract class JActivity extends AppCompatActivity {

    public static final int PERMISSION_CODE_FINE_LOCATION = 100;
    public static final int PERMISSION_CODE_COARSE_LOCATION = 101;

    /**
     * onCreate method for the JActivity class.
     * This is where the code in this class starts from.
     *
     * @param savedInstanceState Bundle argument passed though from parent class
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(new String[]{ Manifest.permission.ACCESS_FINE_LOCATION }, PERMISSION_CODE_FINE_LOCATION);
        }

        if (checkSelfPermission(Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(new String[]{ Manifest.permission.ACCESS_COARSE_LOCATION }, PERMISSION_CODE_COARSE_LOCATION);
        }
    }

    /**
     * This is a method in JActivity which is called by
     * a JFragment object once the fragment is loaded and
     * on the screen visible to the user
     *
     * @param fragment The fragment which has just loaded
     * @see JActivity
     * @see com.cst2335.projectassignment.fragments.JFragment
     */
    public void onFragmentLoaded(Fragment fragment) {};

    /**
     * Utility method used to quickly log stuff to console.
     *
     * @param message The message to log to console
     */
    public final void log(String message) {
        Log.i(this.getLocalClassName(), message);
    }

    /**
     * Quickly retrieves a word from the strings.xml file and
     * gives the option to capitalize the first letter as well.
     *
     * @param string The string to retrieve
     * @param capitalize If the first letter should be capitalized
     * @return String with or without a capital first letter
     */
    public final String word(@StringRes int string, Boolean capitalize) {
        return (capitalize) ? capitalize(getString(string)) : getString(string);
    }

    /**
     * Capitalized the first letter of a string.
     *
     * @param string The string you wish to capitalize
     * @return Your string with a capital for the first letter
     */
    public static String capitalize(String string) {
        return (string == null || string.isEmpty()) ? string : string.substring(0, 1).toUpperCase() + string.substring(1);
    }

    /**
     * Converts a float number to a dp number for XML use.
     *
     * @param flt The float number to convert.
     * @return Your float in dp
     */
    public final Float floatToDp(float flt) {
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, flt, JActivity.this.getResources().getDisplayMetrics());
    }

    /**
     * Uses locations services to find the current city of the user
     *
     * @return The current city the user is in
     */
    public final String getCurrentCity() {
        String result = null;

        if (checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED &&
            checkSelfPermission(Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED
        ) {
            LocationManager locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
            Criteria criteria = new Criteria();
            String provider = locationManager.getBestProvider(criteria, false);

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
        } else return "N/A";

    }

    /**
     *
     * @param url
     * @return
     */
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

    /**
     * Performs a http request to the API we use and returns the JSONObject
     * @param city The city to look up
     * @param radius The radius to search
     * @return JSONObject retrieved from the API request
     */
    public final JSONObject doHttpRequest(String city, Integer radius) {
        return performHttpRequest(HTTPRequest.url(city, radius));
    }

    /**
     * Performs a http request to the API we use and returns the JSONObject
     * @param id The event ID to search up
     * @return JSONObject retrieved from the API request
     */
    public final JSONObject doHttpRequest(String id) {
        return performHttpRequest(HTTPRequest.url(id));
    }

    /**
     * Simple way of getting the shared preferences object
     * @return SharedPreferences
     */
    public final SharedPreferences getSharedPreferences() { return getSharedPreferences(TicketQuery.PREFERENCES_FILE, Context.MODE_PRIVATE); }

    /**
     * Simple way of getting the shared preferences editor object
     * @return SharedPreferences.Editor
     */
    public final SharedPreferences.Editor getSharedPreferencesEditor() { return getSharedPreferences(TicketQuery.PREFERENCES_FILE, Context.MODE_PRIVATE).edit(); }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (requestCode == PERMISSION_CODE_FINE_LOCATION) {
            String msg;
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                msg = String.format(
                        "%s %s %s %s",
                        word(R.string.fine, true),
                        word(R.string.location, true),
                        word(R.string.permission, true),
                        word(R.string.granted, true)
                );
            } else {
                msg = String.format(
                        "%s %s %s %s",
                        word(R.string.fine, true),
                        word(R.string.location, true),
                        word(R.string.permission, true),
                        word(R.string.denied, true)
                );
            }
            Toast.makeText(JActivity.this, msg, Toast.LENGTH_SHORT) .show();
        } else if (requestCode == PERMISSION_CODE_COARSE_LOCATION) {
            String msg;
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                msg = String.format(
                        "%s %s %s %s",
                        word(R.string.coarse, true),
                        word(R.string.location, true),
                        word(R.string.permission, true),
                        word(R.string.granted, true)
                );
            } else {
                msg = String.format(
                        "%s %s %s %s",
                        word(R.string.coarse, true),
                        word(R.string.location, true),
                        word(R.string.permission, true),
                        word(R.string.denied, true)
                );
            }
            Toast.makeText(JActivity.this, msg, Toast.LENGTH_SHORT).show();
        }
    }

}
