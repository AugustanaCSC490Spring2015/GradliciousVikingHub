package com.example.andrewshearouse11.nongameapp;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.List;


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

        /* TEST CODE FOR SAVING/CREATING NEW PARSE OBJECT
        ParseObject testObject = new ParseObject("TestObject");
        testObject.put("foo", "bar");
        testObject.saveInBackground();
        */
        final ParseQuery<ParseObject> query = ParseQuery.getQuery("AcademicBuildings");
        query.findInBackground(new FindCallback<ParseObject>() {
                                   @Override
                                   public void done(List<ParseObject> parseObjects, ParseException e) {
                                       if(e==null){
                                           int i = 0;
                                           while(i < parseObjects.size()){
                                               Log.d("info", "BUILDING NAME: " + parseObjects.get(i).getString("buildingName"));
                                               Log.d("info", "YEAR OPENED: " + parseObjects.get(i).getInt("yearOpened"));
                                               Log.d("info", "MAJORS: " + parseObjects.get(i).getString("majors"));
                                               Log.d("info", "BUILDING SERVICES: " + parseObjects.get(i).getString("buildingServices"));
                                               i++;
                                           }
                                       }else{
                                           Log.d("info", "Sad Panda");
                                       }
                                   }
                               });

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
