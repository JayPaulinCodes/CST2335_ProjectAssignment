package com.cst2335.projectassignment.utils;

import org.json.JSONObject;

// TODO: Add JavaDoc Comment
public class TicketQuery {
    public final static String PREFERENCES_FILE = "TicketQueryData";
    public final static String PREFERENCE_LAST_USER_CITY = "preference_lastUserCity";

    public static final String ACTIVITY_HOME = "activityPage_home";
    public static final String ACTIVITY_SEARCH = "activityPage_search";
    public static final String ACTIVITY_FAVORITES = "activityPage_favorites";


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

}
