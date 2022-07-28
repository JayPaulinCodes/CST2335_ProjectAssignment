package com.cst2335.projectassignment;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;

public class TicketQuery extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private static void log(String message) { Log.i(TAG, message); }
    private static final String TAG = "TicketQuery";

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
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_toolbar, menu);
        return super.onCreateOptionsMenu(menu);
//        return true;
    }

    // Handle Toolbar Select
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
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        Integer option = null;

        switch (item.getItemId()) {
            case R.id.drawerMenu_home:
                option = 1;
                break;
            case R.id.drawerMenu_search:
                option = 2;
                break;
            case R.id.drawerMenu_favorites:
                option = 3;
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