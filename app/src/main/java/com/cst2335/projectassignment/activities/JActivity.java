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

// TODO: Add JavaDoc Comment
public abstract class JActivity extends AppCompatActivity {

    public static final int PERMISSION_CODE_FINE_LOCATION = 100;
    public static final int PERMISSION_CODE_COARSE_LOCATION = 101;

    // TODO: Add JavaDoc Comment
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
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, flt, JActivity.this.getResources().getDisplayMetrics());
    }

    // TODO: Add JavaDoc Comment
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

    // TODO: Add JavaDoc Comment
    public final SharedPreferences getSharedPreferences() { return getSharedPreferences(TicketQuery.PREFERENCES_FILE, Context.MODE_PRIVATE); }

    // TODO: Add JavaDoc Comment
    public final SharedPreferences.Editor getSharedPreferencesEditor() { return getSharedPreferences(TicketQuery.PREFERENCES_FILE, Context.MODE_PRIVATE).edit(); }

    // TODO: Add JavaDoc Comment
    public void onFragmentLoaded(Fragment fragment) {};

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
