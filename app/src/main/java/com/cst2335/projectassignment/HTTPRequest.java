package com.cst2335.projectassignment;

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

    @Override
    protected String doInBackground(String... args) {

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
            String result = stringBuilder.toString();

            // convert string to JSON: Look at slide 27:
            JSONObject uvReport = new JSONObject(result);

            //get the double associated with "value"
            int numEntries = uvReport.getInt("count");

            publishProgress(25);
            Thread.sleep(1000);
            publishProgress(50);
            Log.i(TAG, "Num of entries: " + numEntries) ;

        } catch (Exception exception) { exception.printStackTrace(); }

        return "Done";
    }

    public void onProgressUpdate(Integer ... args) {
        Log.i(TAG, "onProgressUpdate");
    }

    public void onPostExecute(String fromDoInBackground) {
        Log.i(TAG, fromDoInBackground);
    }
}
