package com.example.andrewshearouse11.nongameapp;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.ImageView;


public class MainActivity extends Activity {

    ImageButton campusTourButton;
    ImageButton campusMapButton;
    ImageButton eventsCalendarButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //establish ImageView objects
        campusTourButton = (ImageButton) findViewById(R.id.tourButton);
        campusMapButton = (ImageButton) findViewById(R.id.mapButton);
        eventsCalendarButton = (ImageButton) findViewById(R.id.eventsButton);

        //set the click listeners
        setListeners();
    }

    private void setListeners(){
        //launch campus tour
        campusTourButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent campusTour = new Intent(getBaseContext(), CampusTour.class);
                startActivity(campusTour);
            }
        });

        //launch campus map
        campusMapButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent campusMap = new Intent(getBaseContext(), CampusMap.class);
                startActivity(campusMap);
            }
        });

        //launch full events calendar
        eventsCalendarButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent eventsCalendar = new Intent(getBaseContext(), EventsCalendar.class);
                startActivity(eventsCalendar);
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
