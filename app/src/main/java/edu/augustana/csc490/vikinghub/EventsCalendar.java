package edu.augustana.csc490.vikinghub;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import android.view.Menu;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class EventsCalendar extends Activity {

        @Override
        protected void onCreate(Bundle savedInstanceState) {

            super.onCreate(savedInstanceState);
            setContentView(R.layout.events_layout);

            TextView display = (TextView)findViewById(R.id.info);
            HTMLParser parser = new HTMLParser();
            parser.sendDisplay(display);
            String urlString = "http://www.augustana.edu/prebuilt/acal/calpage.php?mode=js&viewid=13";
            parser.execute(urlString);

        }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }
}

//Code to run in background and parse HTML
class HTMLParser extends AsyncTask<String, Void, String> {
    TextView display;
    ArrayList<Event> listOfEvents = new ArrayList<Event>();
    @Override
    protected String doInBackground(String... strings) {
        String uglyDoc = "";
        try {
            Log.d("JSwa", "Connecting to [" + strings[0] + "]");
            Document doc  = Jsoup.connect(strings[0]).get();
            Log.d("JSwa", "Connected to ["+strings[0]+"]");

            //uglyDoc = doc.toString();
            Elements eventTitles = doc.getElementsByAttributeValueContaining("class", "cal_title");
            Elements eventDates = doc.getElementsByAttributeValueContaining("class", "cal_date");
            //removes additional dates that are being pulled in and cause an incorrect display
            //more info above the method
            eventDates = removeExtraDates(eventDates);
            Elements eventDescriptions = doc.getElementsByAttributeValueContaining("class", "cal_desc");

            for(int i = 0; i < eventTitles.size(); i++){
                Event currentEvent = new Event(eventTitles.get(i).text(), eventDates.get(i).text(), eventDescriptions.get(i).text());
                listOfEvents.add(currentEvent);
            }

//            String[] uglyDocArray = uglyDoc.split("\n");
//            for(String line : uglyDocArray){
//                Log.w("line",line);
//            }

        }
        catch(Throwable t) {
            t.printStackTrace();
        }

        return uglyDoc;
    }

    /**
     *
     * @param eventDates - full list of event dates
     * This method deals with a problem caused by the layout of the HTML file.
     * The file has classes "cal_date" and "cal_date_large" and, due to some browser rendering issues,
     * I can only get content using getElementsByAttributeValueContaining(). This means that it returns
     * both "cal_date" content and the occasional "cal_date_large" that throws off the content scheme.
     * This method removes the cal_date_large elements from the dates.
     */
    public Elements removeExtraDates(Elements eventDates){
        Elements temp = new Elements();
        for(Element date : eventDates){
            if(date.className().contains("large")){
                temp.add(date);
            }
        }
        eventDates.removeAll(temp);
        return eventDates;
    }

    protected void sendDisplay(TextView display){
        this.display = display;
    }

    @Override
    protected void onPostExecute(String s) {
        //Question the ordering of these two lines. May need to call display.setText before super executes.
        super.onPostExecute(s);
        display.setText(s);
    }

    private void formatText(){

    }
}





