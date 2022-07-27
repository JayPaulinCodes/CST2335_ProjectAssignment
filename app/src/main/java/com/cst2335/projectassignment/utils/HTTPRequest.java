package com.cst2335.projectassignment.utils;

import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class HTTPRequest extends AsyncTask<String, Integer, String> {
    private static final String TAG = "HTTPRequest";

    public static final String BASE_URL = "https://app.ticketmaster.com/discovery/v2/events.json?apikey=GaC7WC9H0odhl78qlM7sgE6ktHwZcDDq";
    private static final String KEY = "GaC7WC9H0odhl78qlM7sgE6ktHwZcDDq";

    @Override
    protected String doInBackground(String... args) {
        String resultOutput = null;

        try {

            // Create URL based off of args
            URL url = new URL(args[0]);

            // Open connection
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();

            InputStream response = urlConnection.getInputStream();

            BufferedReader reader = new BufferedReader(new InputStreamReader(response, "UTF-8"), 8);
            StringBuilder stringBuilder = new StringBuilder();

            String line = null;
            while ((line = reader.readLine()) != null) {
                stringBuilder.append(line + "\n");
            }

            resultOutput = stringBuilder.toString();

        } catch (Exception exception) { exception.printStackTrace(); }

        return resultOutput;
    }

    public void onProgressUpdate(Integer ... args) {
        Log.i(TAG, "onProgressUpdate");
    }

    public void onPostExecute(String result) {
        super.onPostExecute(result);
        Log.i(TAG, "onPostExecute");
    }
}
