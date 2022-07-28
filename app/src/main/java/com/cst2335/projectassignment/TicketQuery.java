package com.cst2335.projectassignment;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;

import com.cst2335.projectassignment.utils.HTTPRequest;

import org.json.JSONObject;

public class TicketQuery extends AppCompatActivity {
    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ticketquery);

        HTTPRequest req = new HTTPRequest();
        req.execute("https://api.publicapis.org/entries");

        String result = null;
        JSONObject resultJSON = null;
        Integer result_count = null;
        try {
            result = req.get();
            resultJSON = new JSONObject(result);
            result_count = resultJSON.getInt("count");
        } catch (Exception exception) { exception.printStackTrace(); }

        log(Integer.toString(result_count));

    }

    @Override
    public void onStart() {
        super.onStart();

    }

    private static void log(String message) { Log.i(TAG, message); }
}