package com.cst2335.projectassignment;

import androidx.annotation.StringRes;
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
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.cst2335.projectassignment.fragments.EventSearch;
import com.google.android.material.internal.NavigationMenuItemView;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.progressindicator.CircularProgressIndicator;

// TODO: Add JavaDoc Comment
public class TicketQuery extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private static final String TAG = "TicketQuery";

    // TODO: Add JavaDoc Comment
    private static final void log(String message) { Log.i(TAG, message); }

    // TODO: Add JavaDoc Comment
    private final String word(@StringRes int string, Boolean capitalize) { return (capitalize) ? capitalize(getString(string)) : getString(string); }

    // TODO: Add JavaDoc Comment
    private static final String capitalize(String str) { return (str == null || str.isEmpty()) ? str : str.substring(0, 1).toUpperCase() + str.substring(1); }



    private Runnable postLoad = new Runnable() {
        public void run() {
            // Load Fragment
            FragmentManager fragmentManager = getSupportFragmentManager();
            EventSearch fragment_eventSearch = new EventSearch();

            fragmentManager
                    .beginTransaction()
                    .replace(R.id.activity_ticketQuery_frame, fragment_eventSearch)
                    .commit();

            CircularProgressIndicator progressIndicator = findViewById(R.id.activity_ticketQuery_progressIndicator);
            progressIndicator.setIndeterminate(false);
            progressIndicator.setVisibility(View.INVISIBLE);
        }
    };

    // TODO: Add JavaDoc Comment
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ticketquery);

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

//        findViewById(R.id.activity_ticketQuery_toolbar).bringToFront();
//        findViewById(R.id.activity_ticketQuery_drawer).bringToFront();

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


//    HTTPRequest req = new HTTPRequest();
//        req.execute("https://api.publicapis.org/entries");
//
//                String result = null;
//                JSONObject resultJSON = null;
//                Integer result_count = null;
//                try {
//                result = req.get();
//                resultJSON = new JSONObject(result);
//                result_count = resultJSON.getInt("count");
//                } catch (Exception exception) { exception.printStackTrace(); }
//
//                log(Integer.toString(result_count));