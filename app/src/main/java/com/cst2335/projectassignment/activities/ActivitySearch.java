package com.cst2335.projectassignment.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.view.ContextThemeWrapper;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Handler;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.cst2335.projectassignment.R;
import com.cst2335.projectassignment.fragments.FragmentEventDetails;
import com.cst2335.projectassignment.fragments.FragmentEventSearch;
import com.cst2335.projectassignment.fragments.JFragment;
import com.cst2335.projectassignment.objects.Event;
import com.cst2335.projectassignment.utils.EventListAdapter;
import com.cst2335.projectassignment.utils.HTTPRequest;
import com.cst2335.projectassignment.utils.OpenHelper;
import com.cst2335.projectassignment.utils.TicketQuery;
import com.google.android.material.internal.NavigationMenuItemView;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.progressindicator.CircularProgressIndicator;
import com.google.android.material.snackbar.Snackbar;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;

// TODO: Add JavaDoc Comment
public class ActivitySearch extends JActivity implements NavigationView.OnNavigationItemSelectedListener {

    public static final String ARG_CITY = "activityArg_city";
    public static final String ARG_RADIUS = "activityArg_radius";

    private Boolean loaded = false;
    private ArrayList<Event> events = new ArrayList<>(  );
    private EventListAdapter listAdapter;
    private OpenHelper openHelper;
    private SQLiteDatabase sqLiteDatabase;
    private Button searchButton;
    private EditText editText_city;
    private EditText editText_radius;
    private View fragmentEventSearch_view;

    public SQLiteDatabase getDB() { return sqLiteDatabase; }

    private Runnable postLoad = () -> {
        // Pause for a bit to allow time to load

        // Load Fragments
        FragmentManager fragmentManager = getSupportFragmentManager();

        FragmentEventSearch fragment_eventSearch = new FragmentEventSearch().context(ActivitySearch.this);
        Bundle arguments_eventSearch = new Bundle();

        // Handle City Shit
        String currentCity = getCurrentCity();
        String lastCity = getSharedPreferences().getString(TicketQuery.PREFERENCE_LAST_USER_CITY, "N/A");
        if (lastCity.equals("N/A")) getSharedPreferencesEditor().putString(TicketQuery.PREFERENCE_LAST_USER_CITY, currentCity);
        else if (!lastCity.equals(currentCity)) getSharedPreferencesEditor().putString(TicketQuery.PREFERENCE_LAST_USER_CITY, currentCity);
        arguments_eventSearch.putString(ARG_CITY, currentCity);
        arguments_eventSearch.putInt(ARG_RADIUS, 100);

        fragment_eventSearch.setArguments(arguments_eventSearch);

        fragmentManager
                .beginTransaction()
                .replace(R.id.activity_search_frame, fragment_eventSearch)
                .commit();

        // Hide the progress indicator
        CircularProgressIndicator progressIndicator = findViewById(R.id.activity_search_progressIndicator);
        progressIndicator.setIndeterminate(false);
        progressIndicator.setVisibility(View.INVISIBLE);

    };

    // TODO: Add JavaDoc Comment
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        // Set up database
        openHelper = new OpenHelper(this);
        sqLiteDatabase = openHelper.getWritableDatabase();
        Cursor results = sqLiteDatabase.rawQuery("Select * from " + openHelper.TABLE_NAME + ";",null);

        // Set Up Toolbar
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // Set Up Drawer
        DrawerLayout drawer = findViewById(R.id.drawer);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.open, R.string.close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        NavigationView navigationView = findViewById(R.id.drawer_navigation);
        navigationView.setNavigationItemSelectedListener(this);

        drawer.addDrawerListener(new DrawerLayout.DrawerListener() {
            boolean isDrawerOpen = false;
            boolean readyForChange = true;
            LinearLayout linearLayout = findViewById(R.id.activity_search_linearLayout);

            @Override
            public void onDrawerSlide(@NonNull View drawerView, float slideOffset) {
                if (readyForChange) {
                    if (isDrawerOpen) linearLayout.setElevation(floatToDp(5));
                    else linearLayout.setElevation(floatToDp(10));
                    isDrawerOpen = !isDrawerOpen;
                    readyForChange = false;
                }

            }

            @Override
            public void onDrawerOpened(@NonNull View drawerView) {
                if (!readyForChange) readyForChange = true;
            }

            @Override
            public void onDrawerClosed(@NonNull View drawerView) {
                if (!readyForChange) readyForChange = true;
            }

            @Override
            public void onDrawerStateChanged(int newState) {

            }
        });

        // Set up the postLoad runnable
        new Handler().postDelayed(postLoad, 2000);
    }

    // TODO: Add JavaDoc Comment
    @Override
    @SuppressLint("RestrictedApi")
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_toolbar, menu);

        // Set the name on the drawer menu
        NavigationMenuItemView activityName = findViewById(R.id.menuDrawer_activity);
        if (activityName != null) activityName.setTitle(String.format("%s: %s", word(R.string.activity, true), this.getLocalClassName()));

        // Set the author on the drawer menu
        NavigationMenuItemView author = findViewById(R.id.menuDrawer_author);
        if (author != null) author.setTitle(String.format("%s: %s", word(R.string.author, true), word(R.string.appAuthor, false)));

        // Set the version on the drawer menu
        NavigationMenuItemView version = findViewById(R.id.menuDrawer_version);
        if (version != null) version.setTitle(String.format("%s: %s", word(R.string.version, true), word(R.string.appVersion, false)));

        return super.onCreateOptionsMenu(menu);
    }

    // Handle Toolbar Select
    // TODO: Add JavaDoc Comment
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.menuToolbar_home:
                startActivity(new Intent(ActivitySearch.this, ActivityHome.class));
                break;
            case R.id.menuToolbar_search:
                startActivity(new Intent(ActivitySearch.this, ActivitySearch.class));
                break;
            case R.id.menuToolbar_favorites:
                startActivity(new Intent(ActivitySearch.this, ActivityFavorites.class));
                break;
            case R.id.menuToolbar_help:
                // TODO: Show help dialog
                break;
        }

        return true;
    }

    // Handle Drawer Select
    // TODO: Add JavaDoc Comment
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.menuDrawer_activity:
            case R.id.menuDrawer_author:
            case R.id.menuDrawer_version:

                new AlertDialog.Builder(new ContextThemeWrapper(this, R.style.Theme_Components_AlertDialog))
                        .setTitle(getString(R.string.alertDialog_appInfo_title))
                        .setMessage(String.format(
                                getString(R.string.alertDialog_appInfo_message),
                                this.getLocalClassName(),
                                word(R.string.appAuthor, false),
                                word(R.string.appVersion, false)
                        ))
                        .setNeutralButton(word(R.string.close, true), (click, arg) -> {})
                        .setNeutralButtonIcon(getDrawable(R.drawable.close_icon_60_dark))
                        .create()
                        .show();

                break;
            case R.id.menuDrawer_home:
                startActivity(new Intent(ActivitySearch.this, ActivityHome.class));
                break;
            case R.id.menuDrawer_search:
                startActivity(new Intent(ActivitySearch.this, ActivitySearch.class));
                break;
            case R.id.menuDrawer_favorites:
                startActivity(new Intent(ActivitySearch.this, ActivityFavorites.class));
                break;
            case R.id.menuDrawer_help:
                // TODO: Show help dialog
                break;
        }

        DrawerLayout drawerLayout = findViewById(R.id.drawer);
        drawerLayout.closeDrawer(GravityCompat.START);

        return false;
    }

    // TODO: Add JavaDoc Comment
    @Override
    public void onFragmentLoaded(Fragment fragment) {
        Class<? extends Fragment> fragmentClass = fragment.getClass();
//        log("555 " + fragment.getClass().getName());

        if (fragmentClass == FragmentEventSearch.class) {
            FragmentEventSearch fragmentEventSearch = (FragmentEventSearch) fragment;
            fragmentEventSearch_view = fragmentEventSearch.getView();
            View searchBar = fragmentEventSearch_view.findViewById(R.id.fragment_eventSearch_searchBar);


            events = fragmentEventSearch.getEventList();
            ListView listView = fragmentEventSearch_view.findViewById(R.id.fragment_eventSearch_listView);
            listView.setAdapter(listAdapter = new EventListAdapter(events, this));
            searchButton = searchBar.findViewById(R.id.searchBar_searchButton);
            editText_city = searchBar.findViewById(R.id.searchBar_editText_city);
            editText_radius = searchBar.findViewById(R.id.searchBar_editText_radius);

            searchButton.setOnClickListener(this::searchEvent);

            listView.setOnItemClickListener(this::listItemSelectEvent);
        } else if (fragmentClass == FragmentEventDetails.class) {

        }
    }

    private void listItemSelectEvent(AdapterView<?> parent, View view, int position, long id) {
        Event selectedEvent = (Event) listAdapter.getItem(position);

        // Load Fragments
        FragmentManager fragmentManager = getSupportFragmentManager();

        FragmentEventDetails fragment_eventDetails = new FragmentEventDetails().context(ActivitySearch.this).event(selectedEvent);
//        Bundle arguments_eventDetails = new Bundle();

//        fragment_eventDetails.setArguments(arguments_eventDetails);

        fragmentManager
                .beginTransaction()
                .replace(R.id.activity_search_frame, fragment_eventDetails)
                .commit();

    }

    private void searchEvent(View view) {
        Boolean canRun = true;
        ArrayList<String> invalidFields = new ArrayList<String>();
        StringBuilder errorStringBuilder = new StringBuilder();
        errorStringBuilder.append(word(R.string.message_invalidSearchInput, false));
        errorStringBuilder.append(" ");

        // Check for text in city field
        if (editText_city.getText().length() <= 0) invalidFields.add(word(R.string.city, true));

        // Check for a radius
        if (editText_radius.getText().length() <= 0) invalidFields.add(word(R.string.radius, true));

        if (invalidFields.size() > 0) canRun = false;

        if (canRun) {
            String city = editText_city.getText().toString();
            Integer radius = Integer.parseInt(editText_radius.getText().toString());

            // Do HTTP search
            try {
                JSONObject queryResults = doHttpRequest(city, radius);
                JSONArray queryResultsArray = queryResults.getJSONObject("_embedded").getJSONArray("events");
                events = HTTPRequest.processHTTPJSONArray(queryResultsArray);
            } catch (Exception exception) { exception.printStackTrace(); }

            // NotifyDataSetChanged
//            ListView listView = fragmentEventSearch_view.findViewById(R.id.fragment_eventSearch_listView);
//            listView.setAdapter(listAdapter = new EventListAdapter(events, this));

            // Not sure why this doesn't work
            listAdapter.setList(events);
            listAdapter.notifyDataSetChanged();

            // Update shared preferences with last search
            getSharedPreferencesEditor().putString(TicketQuery.PREFERENCE_LAST_SEARCH_CITY, city);
            getSharedPreferencesEditor().putInt(TicketQuery.PREFERENCE_LAST_SEARCH_RADIUS, radius);

//            String currentLastSearch_city = getSharedPreferences().getString(TicketQuery.PREFERENCE_LAST_SEARCH_CITY, "N/A");
//            Integer currentLastSearch_radius = getSharedPreferences().getInt(TicketQuery.PREFERENCE_LAST_SEARCH_RADIUS, -1);
//
//            if (currentLastSearch_city.equals("N/A") || currentLastSearch_radius == -1) {
//                // No data present
//            }

        } else {
            errorStringBuilder.append(String.join(", ", invalidFields));
            sendSnackbar(errorStringBuilder.toString());
        }
    }

    private void sendSnackbar(String message) {
        Snackbar snackbar = Snackbar.make(searchButton, message, Snackbar.LENGTH_INDEFINITE);
        snackbar.setAction(word(R.string.close, true), click -> snackbar.dismiss()).show();
    }
}