package com.cst2335.projectassignment.utils;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.cst2335.projectassignment.objects.Event;

import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Utility class with constants and usefull helper methods used throughout the app
 *
 * @author Jacob Paulin
 */
public class TicketQuery {
    public final static String PREFERENCES_FILE = "TicketQueryData";
    public final static String PREFERENCE_LAST_USER_CITY = "preference_lastUserCity";
    public final static String PREFERENCE_LAST_SEARCH_CITY = "preference_lastSearch_city";
    public final static String PREFERENCE_LAST_SEARCH_RADIUS = "preference_lastSearch_radius";

//    public static final String ACTIVITY_HOME = "activityPage_home";
    public static final String ACTIVITY_SEARCH = "activityPage_search";
    public static final String ACTIVITY_FAVORITES = "activityPage_favorites";


//    public static JSONObject fetchFromAPI(String city, Integer radius) {
//        HTTPRequest httpRequest = new HTTPRequest();
//        httpRequest.execute(HTTPRequest.url(city, radius));
//
//        String result = null;
//        JSONObject resultJSON = null;
//        try {
//            result = httpRequest.get();
//            resultJSON = new JSONObject(result);
//        } catch (Exception exception) {
//            exception.printStackTrace();
//            resultJSON = null;
//        }
//
//        return resultJSON;
//    }

    /**
     * Does a HTTPRequest for a specific event using the event id
     * @param eventId the id of the event to fetch
     * @return the JSONObject of the event
     */
    public static JSONObject fetchFromAPI(String eventId) {
        HTTPRequest httpRequest = new HTTPRequest();
        httpRequest.execute(HTTPRequest.url(eventId));

        String result;
        JSONObject resultJSON;
        try {
            result = httpRequest.get();
            resultJSON = new JSONObject(result);
        } catch (Exception exception) {
            exception.printStackTrace();
            resultJSON = null;
        }

        return resultJSON;
    }


    /**
     * Method used to retrieve all the events which are saved as favorites
     * @param db An instance of the SQLite database
     * @return List of favorited events
     */
    public static ArrayList<Event> getFavoriteEvents(SQLiteDatabase db) {
        ArrayList<Event> events = new ArrayList<>();

        String query = String.format(
                "SELECT * FROM %s WHERE %s = 1;",
                OpenHelper.TABLE_NAME,
                OpenHelper.COL_IS_EVENT_FAVORITE
        );

        Cursor cursor = db.rawQuery(query, null);
//        int colIndex_id = cursor.getColumnIndex(OpenHelper.COL_ID);
        int colIndex_eventId = cursor.getColumnIndex(OpenHelper.COL_EVENT_ID);
//        int colIndex_isEventFavorite = cursor.getColumnIndex(OpenHelper.COL_IS_EVENT_FAVORITE);

        while (cursor.moveToNext()) {
//            int eventDb_id = cursor.getInt(colIndex_id);
            String eventDb_eventId = cursor.getString(colIndex_eventId);
//            int eventDb_isEventFavorite = cursor.getInt(colIndex_isEventFavorite);

            try {
                JSONObject queryResults = TicketQuery.fetchFromAPI(eventDb_eventId);
                JSONObject queryResultsObj = queryResults.getJSONObject("_embedded").getJSONArray("events").getJSONObject(0);

                events.add(HTTPRequest.processEventJSON(queryResultsObj));
            } catch (Exception exception) { exception.printStackTrace(); }
        }

        cursor.close();
        return events;
    }

    /**
     * Checks if a event is in the database
     * @param db An instance of the SQLite database
     * @param eventId The id of the event to find
     * @return True if the event is in the database and false if it's not
     */
    public static boolean isEventInDB(SQLiteDatabase db, String eventId) {
        String query = String.format(
                "SELECT * FROM %s WHERE %s = '%s';",
                OpenHelper.TABLE_NAME,
                OpenHelper.COL_EVENT_ID,
                eventId
        );

        Cursor cursor = db.rawQuery(query, null);

        int cursorCount = cursor.getCount();

        cursor.close();

        return cursorCount > 0;
    }

    /**
     * Sets the favorite status of a specific event in the database
     * @param db An instance of the SQLite database
     * @param eventId The id of the event to find
     * @param isFavorite True if the event is a favorite and false otherwise
     */
    public static void setEventFavorite(SQLiteDatabase db, String eventId, boolean isFavorite) {
        int favValue;

        if (isFavorite) favValue = 1;
        else favValue = 0;

        ContentValues contentValues = new ContentValues();
        contentValues.put(OpenHelper.COL_ID, getEventDBId(db, eventId));
        contentValues.put(OpenHelper.COL_EVENT_ID, eventId);
        contentValues.put(OpenHelper.COL_IS_EVENT_FAVORITE, favValue);

        db.replace(OpenHelper.TABLE_NAME, null, contentValues);
    }

    /**
     * Find the id used by a specific event in the SQLite database
     * @param db An instance of the SQLite database
     * @param eventId The id of the event to find
     * @return The id used by the event in the SQLite database
     */
    public static Integer getEventDBId(SQLiteDatabase db, String eventId) {
        String query = String.format(
                "SELECT * FROM %s WHERE %s = '%s';",
                OpenHelper.TABLE_NAME,
                OpenHelper.COL_EVENT_ID,
                eventId
        );

        Cursor cursor = db.rawQuery(query, null);
        int colIndex_id = cursor.getColumnIndex(OpenHelper.COL_ID);

        if (cursor.moveToFirst()) {
            int out = cursor.getInt(colIndex_id);
            cursor.close();
            return out;
        }
        else {
            cursor.close();
            return null;
        }
    }

    /**
     * Checks if a specific item is saved in the SQLite database as a favorite
     * @param db An instance of the SQLite database
     * @param eventId The id of the event to find
     * @return True if the event is a favorite and false otherwise
     */
    @SuppressLint("Range")
    public static Boolean isEventFavorite(SQLiteDatabase db, String eventId) {
        Boolean output = null;
        String query = String.format(
                "SELECT * FROM %s WHERE %s = '%s';",
                OpenHelper.TABLE_NAME,
                OpenHelper.COL_EVENT_ID,
                eventId
        );

        if (isEventInDB(db, eventId)) {
            Cursor cursor = db.rawQuery(query, null);
//            int colIndex_id = cursor.getColumnIndex(OpenHelper.COL_ID);
//            int colIndex_eventId = cursor.getColumnIndex(OpenHelper.COL_EVENT_ID);
            int colIndex_isEventFavorite = cursor.getColumnIndex(OpenHelper.COL_IS_EVENT_FAVORITE);

            if (cursor.moveToFirst()) {
//                int eventDb_id = cursor.getInt(colIndex_id);
//                String eventDb_eventId = cursor.getString(colIndex_eventId);
                int eventDb_isEventFavorite = cursor.getInt(colIndex_isEventFavorite);

                if (eventDb_isEventFavorite == 0) output = false;
                else if (eventDb_isEventFavorite == 1) output = true;
            }

            cursor.close();
        } else {
            output = false;

            ContentValues contentValues = new ContentValues();
            contentValues.put(OpenHelper.COL_EVENT_ID, eventId);
            contentValues.put(OpenHelper.COL_IS_EVENT_FAVORITE, 0);
            db.insert(OpenHelper.TABLE_NAME, "NullColumnName", contentValues);
        }


        return output;
    }

}
