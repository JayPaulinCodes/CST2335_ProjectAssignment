package com.cst2335.projectassignment.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.view.ContextThemeWrapper;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.location.Address;
import android.location.Criteria;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.Handler;
import android.util.TypedValue;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import com.cst2335.projectassignment.R;
import com.cst2335.projectassignment.fragments.FragmentEventSearch;
import com.cst2335.projectassignment.fragments.FragmentHomeButton;
import com.cst2335.projectassignment.utils.CityName;
import com.cst2335.projectassignment.utils.OpenHelper;
import com.cst2335.projectassignment.utils.TicketQuery;
import com.google.android.material.internal.NavigationMenuItemView;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.progressindicator.CircularProgressIndicator;

import java.util.List;
import java.util.Locale;
import java.util.concurrent.ExecutionException;

import javax.xml.transform.Result;

// TODO: Add JavaDoc Comment
public class ActivityHome extends JActivity implements NavigationView.OnNavigationItemSelectedListener {

    public static final String ARG_DESTINATION_PAGE = "activityArg_destinationPage";
    public static final String ARG_BUTTON_LABEL = "activityArg_buttonLabel";
    public static final String ARG_DESCRIPTION = "activityArg_description";

    private OpenHelper openHelper;
    private SQLiteDatabase sqLiteDatabase;

    private Runnable postLoad = () -> {
        // Load Fragments
        FragmentManager fragmentManager = getSupportFragmentManager();

        FragmentHomeButton fragment_homeButton_search = new FragmentHomeButton().context(ActivityHome.this);
        Bundle arguments_homeButton_search = new Bundle();

        arguments_homeButton_search.putString(ARG_DESTINATION_PAGE, TicketQuery.ACTIVITY_SEARCH);
        arguments_homeButton_search.putString(ARG_BUTTON_LABEL, word(R.string.activityHome_searchButton_buttonLabel, false));
        arguments_homeButton_search.putString(ARG_DESCRIPTION, word(R.string.activityHome_searchButton_description, false));

        fragment_homeButton_search.setArguments(arguments_homeButton_search);

        fragmentManager
                .beginTransaction()
                .replace(R.id.activity_home_frame1, fragment_homeButton_search)
                .commit();


        FragmentHomeButton fragment_homeButton_favorites = new FragmentHomeButton().context(ActivityHome.this);
        Bundle arguments_homeButton_favorites = new Bundle();

        arguments_homeButton_favorites.putString(ARG_DESTINATION_PAGE, TicketQuery.ACTIVITY_FAVORITES);
        arguments_homeButton_favorites.putString(ARG_BUTTON_LABEL, word(R.string.activityHome_favoriteButton_buttonLabel, false));
        arguments_homeButton_favorites.putString(ARG_DESCRIPTION, word(R.string.activityHome_favoriteButton_description, false));

        fragment_homeButton_favorites.setArguments(arguments_homeButton_favorites);

        fragmentManager
                .beginTransaction()
                .replace(R.id.activity_home_frame2, fragment_homeButton_favorites)
                .commit();

        // Handle City Shit
        String currentCity = getCurrentCity();
        String lastCity = getSharedPreferences().getString(TicketQuery.PREFERENCE_LAST_USER_CITY, "N/A");
        if (lastCity.equals("N/A")) getSharedPreferencesEditor().putString(TicketQuery.PREFERENCE_LAST_USER_CITY, currentCity);
        else if (!lastCity.equals(currentCity)) getSharedPreferencesEditor().putString(TicketQuery.PREFERENCE_LAST_USER_CITY, currentCity);

        // Hide the progress indicator
        CircularProgressIndicator progressIndicator = findViewById(R.id.activity_home_progressIndicator);
        progressIndicator.setIndeterminate(false);
        progressIndicator.setVisibility(View.INVISIBLE);
    };

    // TODO: Add JavaDoc Comment
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

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
            LinearLayout linearLayout = findViewById(R.id.activity_home_linearLayout);

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
        new Handler().postDelayed(postLoad, 5000);
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
                startActivity(new Intent(ActivityHome.this, ActivityHome.class));
                break;
            case R.id.menuToolbar_search:
                startActivity(new Intent(ActivityHome.this, ActivitySearch.class));
                break;
            case R.id.menuToolbar_favorites:
                startActivity(new Intent(ActivityHome.this, ActivityFavorites.class));
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
                startActivity(new Intent(ActivityHome.this, ActivityHome.class));
                break;
            case R.id.menuDrawer_search:
                startActivity(new Intent(ActivityHome.this, ActivitySearch.class));
                break;
            case R.id.menuDrawer_favorites:
                startActivity(new Intent(ActivityHome.this, ActivityFavorites.class));
                break;
            case R.id.menuDrawer_help:
                new AlertDialog.Builder(new ContextThemeWrapper(this, R.style.Theme_Components_AlertDialog))
                        .setTitle(getString(R.string.alertDialog_activityHome_helpTitle))
                        .setMessage(String.format(word(R.string.alertDialog_activityHome_helpMessage, false)))
                        .setNeutralButton(word(R.string.close, true), (click, arg) -> {})
                        .setNeutralButtonIcon(getDrawable(R.drawable.close_icon_60_dark))
                        .create()
                        .show();
                break;
        }

        DrawerLayout drawerLayout = findViewById(R.id.drawer);
        drawerLayout.closeDrawer(GravityCompat.START);

        return false;
    }

    // TODO: Add JavaDoc Comment
    @Override
    public void onFragmentLoaded(Fragment fragment) {}
}