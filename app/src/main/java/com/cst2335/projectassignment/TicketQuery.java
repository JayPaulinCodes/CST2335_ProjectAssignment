package com.cst2335.projectassignment;

import androidx.annotation.StringRes;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.material.internal.NavigationMenuItemView;
import com.google.android.material.navigation.NavigationView;

// TODO: Add JavaDoc Comment
public class TicketQuery extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private static final String TAG = "TicketQuery";

    // TODO: Add JavaDoc Comment
    private static final void log(String message) { Log.i(TAG, message); }

    // TODO: Add JavaDoc Comment
    private final String word(@StringRes int string, Boolean capitalize) { return (capitalize) ? capitalize(getString(string)) : getString(string); }

    // TODO: Add JavaDoc Comment
    private static final String capitalize(String str) { return (str == null || str.isEmpty()) ? str : str.substring(0, 1).toUpperCase() + str.substring(1); }

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

    }

    // TODO: Add JavaDoc Comment
    @Override
    @SuppressLint("RestrictedApi")
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_toolbar, menu);

        // Set the name on the drawer menu
        NavigationMenuItemView activityName = findViewById(R.id.menuDrawer_activity);
        activityName.setTitle(String.format("%s: %s", word(R.string.activity, true), this.getLocalClassName()));

        // Set the author on the drawer menu
        NavigationMenuItemView author = findViewById(R.id.menuDrawer_author);
        author.setTitle(String.format("%s: %s", word(R.string.author, true), word(R.string.appAuthor, false)));

        // Set the version on the drawer menu
        NavigationMenuItemView version = findViewById(R.id.menuDrawer_version);
        version.setTitle(String.format("%s: %s", word(R.string.version, true), word(R.string.appVersion, false)));

        return super.onCreateOptionsMenu(menu);
    }

    // Handle Toolbar Select
    // TODO: Add JavaDoc Comment
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        log("In onOptionsItemSelected (1)");
        String message = null;

        switch (item.getItemId()) {
            case R.id.menuToolbar_home:
                message = "Home";
                break;
        }

        if (message != null) Toast.makeText(this, message, Toast.LENGTH_LONG).show();

        return true;
    }

    // Handle Drawer Select
    // TODO: Add JavaDoc Comment
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        Integer option = null;

        switch (item.getItemId()) {
            case R.id.menuDrawer_home:
                option = 1;
                break;
            case R.id.menuDrawer_favorites:
                option = 2;
                break;
        }

        DrawerLayout drawerLayout = findViewById(R.id.drawer);
        drawerLayout.closeDrawer(GravityCompat.START);

        if (option != null) {
            log(option.toString());
        }

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