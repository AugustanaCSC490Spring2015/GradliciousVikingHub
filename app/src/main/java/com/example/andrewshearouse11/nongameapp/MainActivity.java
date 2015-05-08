package com.example.andrewshearouse11.nongameapp;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.List;


public class MainActivity extends Activity {

    Button campusTourButton;
    Button campusMapButton;
    Button eventsCalendarButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //establish ImageView objects
        campusTourButton = (Button) findViewById(R.id.tourButton);
        campusMapButton = (Button) findViewById(R.id.mapButton);
        eventsCalendarButton = (Button) findViewById(R.id.eventsButton);

        final Typeface font = Typeface.createFromAsset(this.getAssets(), "fonts/moon_light.otf");
        campusTourButton.setTypeface(font);
        campusTourButton.setTextSize(24);
        campusTourButton.setTextColor(Color.WHITE);
        campusMapButton.setTypeface(font);
        campusMapButton.setTextSize(24);
        campusMapButton.setTextColor(Color.WHITE);
        eventsCalendarButton.setTypeface(font);
        eventsCalendarButton.setTextSize(24);
        eventsCalendarButton.setTextColor(Color.WHITE);

        BuildingSharedPreferences preferences = new BuildingSharedPreferences(this, new String[0]);

        /* TEST CODE FOR SAVING/CREATING NEW PARSE OBJECT
        ParseObject testObject = new ParseObject("TestObject");
        testObject.put("foo", "bar");
        testObject.saveInBackground();
        */


                //set the click listeners
                setListeners();
    }

    private void setListeners(){
        //launch campus tour
        campusTourButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent campusTour = new Intent(getBaseContext(), AugustanaCampusTour.class);
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
                //Intent eventsCalendar = new Intent(getBaseContext(), EventsCalendar.class);
                //startActivity(eventsCalendar);
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
