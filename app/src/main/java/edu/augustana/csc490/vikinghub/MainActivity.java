package edu.augustana.csc490.vikinghub;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;


public class MainActivity extends Activity {

    Button campusTourButton;
    Button campusMapButton;
    Button eventsCalendarButton;
    Button helpButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //establish ImageView objects
        campusTourButton = (Button) findViewById(R.id.tourButton);
        campusMapButton = (Button) findViewById(R.id.mapButton);
        eventsCalendarButton = (Button) findViewById(R.id.eventsButton);
        helpButton = (Button) findViewById(R.id.helpButton);

        final Typeface font = Typeface.createFromAsset(this.getAssets(), "fonts/moon_light.otf");
        setButtonStyles(campusTourButton, font, 24);
        setButtonStyles(campusMapButton, font, 24);
        setButtonStyles(eventsCalendarButton, font, 24);
        setButtonStyles(helpButton, font, 12);

        BuildingSharedPreferences preferences = new BuildingSharedPreferences(this, new String[0]);
                //set the click listeners
                setListeners();
    }

    /**
     * @param button the button that is being edited
     * @param font the desired font
     * @param fontSize the desired font size
     */
    private void setButtonStyles(Button button, Typeface font, int fontSize){
        button.setTypeface(font);
        button.setTextSize(fontSize);
        button.setTextColor(Color.WHITE);
    }

    //sets listeners for the different buttons
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

        helpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent help = new Intent(getBaseContext(), Help.class);
                startActivity(help);
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
