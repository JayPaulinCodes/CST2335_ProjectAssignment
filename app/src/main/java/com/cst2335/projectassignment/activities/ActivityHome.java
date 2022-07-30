package com.cst2335.projectassignment.activities;

import androidx.annotation.Dimension;
import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.view.ContextThemeWrapper;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentManager;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import android.util.TypedValue;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;

import com.cst2335.projectassignment.R;
import com.cst2335.projectassignment.fragments.FragmentEventSearch;
import com.cst2335.projectassignment.fragments.FragmentHomeButton;
import com.cst2335.projectassignment.utils.TicketQuery;
import com.google.android.material.internal.NavigationMenuItemView;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.progressindicator.CircularProgressIndicator;

// TODO: Add JavaDoc Comment
public class ActivityHome extends JActivity implements NavigationView.OnNavigationItemSelectedListener {

    public static final String ARG_DESTINATION_PAGE = "activityArg_destinationPage";
    public static final String ARG_BUTTON_LABEL = "activityArg_buttonLabel";
    public static final String ARG_DESCRIPTION = "activityArg_description";

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
                // TODO: Send to home page
                break;
            case R.id.menuToolbar_search:
                // TODO: Send to search page
                break;
            case R.id.menuToolbar_favorites:
                // TODO: Send to favorites page
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
                // TODO: Send to home page
                break;
            case R.id.menuDrawer_search:
                // TODO: Send to search page
                break;
            case R.id.menuDrawer_favorites:
                // TODO: Send to favorites page
                break;
            case R.id.menuDrawer_help:
                // TODO: Show help dialog
                break;
        }

        DrawerLayout drawerLayout = findViewById(R.id.drawer);
        drawerLayout.closeDrawer(GravityCompat.START);

        return false;
    }
}