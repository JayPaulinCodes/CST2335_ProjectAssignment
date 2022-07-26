package com.cst2335.projectassignment.utils;

import android.os.AsyncTask;
import android.util.Log;

import com.cst2335.projectassignment.objects.Distance;
import com.cst2335.projectassignment.objects.Event;
import com.cst2335.projectassignment.objects.EventPriceRange;
import com.cst2335.projectassignment.objects.EventStartDate;
import com.cst2335.projectassignment.objects.EventStatus;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

/**
 * HTTPRequest class used to handle making HTTPRequests and retrieving data from the API
 *
 * @author Jacob Paulin
 */
public class HTTPRequest extends AsyncTask<String, Integer, String> {
    private static final String TAG = "HTTPRequest";

    public static final String BASE_URL = "https://app.ticketmaster.com/discovery/v2/events.json?apikey=%APIKEY%&city=%CITY%&radius=%RADIUS%";
    public static final String BASE_URL_DIRECT = "https://app.ticketmaster.com/discovery/v2/events.json?apikey=%APIKEY%&id=%ID%";
    private static final String KEY = "GaC7WC9H0odhl78qlM7sgE6ktHwZcDDq";

    /**
     * Mechanics of the class, this is where we code our logic for the class
     * @param args arguments to use when making a http request
     * @return The result from the API
     */
    @Override
    protected String doInBackground(String... args) {
        String resultOutput = null;

        try {

            // Create URL based off of args
            URL url = new URL(args[0]);

            // Open connection
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();

            InputStream response = urlConnection.getInputStream();

            BufferedReader reader = new BufferedReader(new InputStreamReader(response, StandardCharsets.UTF_8), 8);
            StringBuilder stringBuilder = new StringBuilder();

            String line;
            while ((line = reader.readLine()) != null) {
                stringBuilder.append(line).append("\n");
            }

            resultOutput = stringBuilder.toString();

        } catch (Exception exception) { exception.printStackTrace(); }

        return resultOutput;
    }

    /**
     * Overriding method from parent class
     * @param args
     */
    public void onProgressUpdate(Integer ... args) {
        Log.i(TAG, "onProgressUpdate");
    }

    /**
     * Method which is executed once the task is complete
     * @param result
     */
    public void onPostExecute(String result) {
        super.onPostExecute(result);
        Log.i(TAG, "onPostExecute");
    }

    /**
     * Method to build a HTTPRequest URL
     * @param city City to search
     * @param radius Radius to search
     * @return A String url with the given parameters
     */
    public static String url(String city, Integer radius) {
        return BASE_URL.replace("%APIKEY%", KEY).replace("%CITY%", city).replace("%RADIUS%", String.valueOf(radius));
    }

    /**
     * Method to build a HTTPRequest URL
     * @param id ID of the event to search
     * @return A String url with the given parameters
     */
    public static String url(String id) {
        return BASE_URL_DIRECT.replace("%APIKEY%", KEY).replace("%ID%", id);
    }

    /**
     * Proccesses a JSONObject from the API request and converts it to a Event object
     * @param eventJSON the json object for the event we want
     * @return Event object based off of the JSONObject
     */
    public static Event processEventJSON(JSONObject eventJSON) {
        String additionalInfo, description, id, image, info, locale, name, pleaseNote, type, url;
        Distance distance;
        EventPriceRange priceRange;
        EventStartDate startDate;
        EventStatus status;

        try {
            additionalInfo = eventJSON.getString("additionalInfo");
        } catch (Exception exception) { additionalInfo = null; }

        try {
            description = eventJSON.getString("description");
        } catch (Exception exception) { description = null; }

        try {
            id = eventJSON.getString("id");
        } catch (Exception exception) { id = null; }

        try {
            JSONArray imagesJSON = eventJSON.getJSONArray("images");
            JSONObject images_firstJSON = imagesJSON.getJSONObject(0);
            image = images_firstJSON.getString("url");
        } catch (Exception exception) { image = null; }

        try {
            info = eventJSON.getString("info");
        } catch (Exception exception) { info = null; }

        try {
            locale = eventJSON.getString("locale");
        } catch (Exception exception) { locale = null; }

        try {
            name = eventJSON.getString("name");
        } catch (Exception exception) { name = null; }

        try {
            pleaseNote = eventJSON.getString("pleaseNote");
        } catch (Exception exception) { pleaseNote = null; }

        try {
            type = eventJSON.getString("type");
        } catch (Exception exception) { type = null; }

        try {
            url = eventJSON.getString("url");
        } catch (Exception exception) { url = null; }

        try {
            String distance_units = eventJSON.getString("units");
            Double distance_distance = eventJSON.getDouble("distance");
            distance = new Distance(distance_distance, distance_units);
        } catch (Exception exception) { distance = null; }

        try {
            JSONArray priceRangesJSON = eventJSON.getJSONArray("priceRanges");
            JSONObject firstPriceRangeJSON = priceRangesJSON.getJSONObject(0);
            String priceRange_currency = firstPriceRangeJSON.getString("currency");
            Double priceRange_minimum = firstPriceRangeJSON.getDouble("min");
            Double priceRange_maximum = firstPriceRangeJSON.getDouble("max");
            priceRange = new EventPriceRange(priceRange_currency, priceRange_minimum, priceRange_maximum);
        } catch (Exception exception) { priceRange = null; }

        try {
            JSONObject datesJSON = eventJSON.getJSONObject("dates");
            JSONObject dates_startJSON = datesJSON.getJSONObject("start");
            Boolean startDate_noSpecificTime = dates_startJSON.getBoolean("noSpecificTime");
            Boolean startDate_toBeAssigned = dates_startJSON.getBoolean("dateTBA");
            Boolean startDate_toBeDetermined = dates_startJSON.getBoolean("dateTBD");
            String startDate_dateTime = dates_startJSON.getString("dateTime");
            String startDate_localDate = dates_startJSON.getString("localDate");
            startDate = new EventStartDate(startDate_noSpecificTime, startDate_toBeAssigned, startDate_toBeDetermined, startDate_dateTime ,startDate_localDate);
        } catch (Exception exception) { startDate = null; }

        try {
            JSONObject datesJSON = eventJSON.getJSONObject("dates");
            JSONObject dates_statusJSON = datesJSON.getJSONObject("status");
            String status_code = dates_statusJSON.getString("code");
            status = EventStatus.valueOf(status_code);
        } catch (Exception exception) { status = null; }

        Log.i("EVENT", id);

        return new Event(
                distance,
                priceRange,
                startDate,
                status,
                additionalInfo,
                description,
                id,
                image,
                info,
                locale,
                name,
                pleaseNote,
                type,
                url
        );
    }

    /**
     * Processes a JSONArray holding JSONObjects for events
     * @param array the JSONArray with JSONObject for events we want to process
     * @return List of events in the JSONArray
     */
    public static ArrayList<Event> processHTTPJSONArray(JSONArray array) {
        ArrayList<Event> events = new ArrayList<>();

        for (int i = 0; i < array.length(); i++) {
            JSONObject entry = null;
            try {
                entry = array.getJSONObject(i);
            } catch (Exception ignored) {}

            if (entry != null) events.add(processEventJSON(entry));

        }

        return events;
    }
}
