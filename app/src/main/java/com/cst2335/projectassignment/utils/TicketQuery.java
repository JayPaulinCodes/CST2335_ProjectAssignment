package com.cst2335.projectassignment.utils;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.cst2335.projectassignment.objects.Event;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

// TODO: Add JavaDoc Comment
public class TicketQuery {
    public final static String PREFERENCES_FILE = "TicketQueryData";
    public final static String PREFERENCE_LAST_USER_CITY = "preference_lastUserCity";
    public final static String PREFERENCE_LAST_SEARCH_CITY = "preference_lastSearch_city";
    public final static String PREFERENCE_LAST_SEARCH_RADIUS = "preference_lastSearch_radius";

    public static final String ACTIVITY_HOME = "activityPage_home";
    public static final String ACTIVITY_SEARCH = "activityPage_search";
    public static final String ACTIVITY_FAVORITES = "activityPage_favorites";


    // TODO: Add JavaDoc Comment
    public static final JSONObject fetchFromAPI(String city, Integer radius) {
        HTTPRequest httpRequest = new HTTPRequest();
        httpRequest.execute(httpRequest.url(city, radius));

        String result = null;
        JSONObject resultJSON = null;
        try {
            result = httpRequest.get();
            resultJSON = new JSONObject(result);
        } catch (Exception exception) {
            exception.printStackTrace();
            resultJSON = null;
        }

        return resultJSON;
    }

    // TODO: Add JavaDoc Comment
    public static final JSONObject fetchFromAPI(String eventId) {
        HTTPRequest httpRequest = new HTTPRequest();
        httpRequest.execute(httpRequest.url(eventId));

        String result = null;
        JSONObject resultJSON = null;
        try {
            result = httpRequest.get();
            resultJSON = new JSONObject(result);
        } catch (Exception exception) {
            exception.printStackTrace();
            resultJSON = null;
        }

        return resultJSON;
    }


    // TODO: Add JavaDoc Comment
    public static ArrayList<Event> getFavoriteEvents(SQLiteDatabase db) {
        ArrayList<Event> events = new ArrayList<>();

        String query = String.format(
                "SELECT * FROM %s WHERE %s = 1;",
                OpenHelper.TABLE_NAME,
                OpenHelper.COL_IS_EVENT_FAVORITE
        );

        Cursor cursor = db.rawQuery(query, null);
        int colIndex_id = cursor.getColumnIndex(OpenHelper.COL_ID);
        int colIndex_eventId = cursor.getColumnIndex(OpenHelper.COL_EVENT_ID);
        int colIndex_isEventFavorite = cursor.getColumnIndex(OpenHelper.COL_IS_EVENT_FAVORITE);

        while (cursor.moveToNext()) {
            int eventDb_id = cursor.getInt(colIndex_id);
            String eventDb_eventId = cursor.getString(colIndex_eventId);
            int eventDb_isEventFavorite = cursor.getInt(colIndex_isEventFavorite);

            try {
                JSONObject queryResults = TicketQuery.fetchFromAPI(eventDb_eventId);
                JSONObject queryResultsObj = queryResults.getJSONObject("_embedded").getJSONArray("events").getJSONObject(0);

                events.add(HTTPRequest.processEventJSON(queryResultsObj));
            } catch (Exception exception) { exception.printStackTrace(); }
        }

        cursor.close();
        return events;
    }

    // TODO: Add JavaDoc Comment
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

        if (cursorCount > 0) return true;
        else return false;
    }

    // TODO: Add JavaDoc Comment
    public static void setEventFavorite(SQLiteDatabase db, String eventId, boolean isFavorite) {
        int favValue;

        if (isFavorite) favValue = 1;
        else favValue = 0;

        ContentValues contentValues = new ContentValues();
        contentValues.put(OpenHelper.COL_ID, getEventDBId(db, eventId));
        contentValues.put(OpenHelper.COL_EVENT_ID, eventId);
        contentValues.put(OpenHelper.COL_IS_EVENT_FAVORITE, favValue);

        long id = db.replace(OpenHelper.TABLE_NAME, null, contentValues);
    }

    // TODO: Add JavaDoc Comment
    public static Integer getEventDBId(SQLiteDatabase db, String eventId) {
        String query = String.format(
                "SELECT * FROM %s WHERE %s = '%s';",
                OpenHelper.TABLE_NAME,
                OpenHelper.COL_EVENT_ID,
                eventId
        );

        Cursor cursor = db.rawQuery(query, null);
        int colIndex_id = cursor.getColumnIndex(OpenHelper.COL_ID);

        if (cursor.moveToFirst()) return cursor.getInt(colIndex_id);
        else return null;
    }

    // TODO: Add JavaDoc Comment
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
            int colIndex_id = cursor.getColumnIndex(OpenHelper.COL_ID);
            int colIndex_eventId = cursor.getColumnIndex(OpenHelper.COL_EVENT_ID);
            int colIndex_isEventFavorite = cursor.getColumnIndex(OpenHelper.COL_IS_EVENT_FAVORITE);

            if (cursor.moveToFirst()) {
                int eventDb_id = cursor.getInt(colIndex_id);
                String eventDb_eventId = cursor.getString(colIndex_eventId);
                int eventDb_isEventFavorite = cursor.getInt(colIndex_isEventFavorite);

                if (eventDb_isEventFavorite == 0) output = false;
                else if (eventDb_isEventFavorite == 1) output = true;
            }
        } else {
            output = false;

            ContentValues contentValues = new ContentValues();
            contentValues.put(OpenHelper.COL_EVENT_ID, eventId);
            contentValues.put(OpenHelper.COL_IS_EVENT_FAVORITE, 0);
            long id = db.insert(OpenHelper.TABLE_NAME, "NullColumnName", contentValues);
        }

        return output;
    }

}
