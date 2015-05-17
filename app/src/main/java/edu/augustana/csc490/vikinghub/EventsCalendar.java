package edu.augustana.csc490.vikinghub;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

public class EventsCalendar extends Activity {

    private ArrayList<Event> listOfEvents;

        @Override
        protected void onCreate(Bundle savedInstanceState) {

            super.onCreate(savedInstanceState);
            setContentView(R.layout.events_layout);

            ListView listView = (ListView) findViewById(R.id.eventListView);
            listOfEvents = new ArrayList<>();

            HTMLParser parser = new HTMLParser(this, listView, listOfEvents);
            String urlString = "http://www.augustana.edu/prebuilt/acal/calpage.php?mode=js&viewid=13";
            parser.execute(urlString);

            listView.setOnItemClickListener(onItemClick);
        }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    private ListView.OnItemClickListener onItemClick = new ListView.OnItemClickListener(){
        @Override
        public void onItemClick(AdapterView<?> parent, View view,int position, long id) {

            String url = listOfEvents.get(position).getEventUrl();
            Intent goToUrlIntent = new Intent(Intent.ACTION_VIEW);
            goToUrlIntent.setData(Uri.parse(url));
            startActivity(goToUrlIntent);
        }
    };
}

//Code to run in background and parse HTML
class HTMLParser extends AsyncTask<String, Void, ArrayList<Event>> {
    private Activity activity;
    private ListView listView;
    private ArrayList<Event> listOfEvents;
    public HTMLParser(Activity activity, ListView listView, ArrayList<Event> listOfEvents){
        this.activity = activity;
        this.listView = listView;
        this.listOfEvents = listOfEvents;
    }

    @Override
    protected ArrayList<Event> doInBackground(String... strings) {
        try {
            Log.d("JSwa", "Connecting to [" + strings[0] + "]");
            Document doc  = Jsoup.connect(strings[0]).get();
            Log.d("JSwa", "Connected to ["+strings[0]+"]");

            Elements eventTitles = doc.getElementsByAttributeValueContaining("class", "cal_title");
            Elements eventDates = doc.getElementsByAttributeValueContaining("class", "cal_date");
            //removes additional dates that are being pulled in and cause an incorrect display
            //more info above the method
            eventDates = removeExtraDates(eventDates);
            Elements eventDescriptions = doc.getElementsByAttributeValueContaining("class", "cal_desc");

            for(int i = 0; i < eventTitles.size(); i++){
                String[] splitString =  eventTitles.get(i).html().split("\\&quot;");
                String url = "";
                for(String line : splitString){
                    if(line.substring(0,3).equals("htt")){
                        url = line.substring(0,line.length());
                    }
                }
                listOfEvents.add(new Event(eventTitles.get(i).text(), eventDates.get(i).text(), eventDescriptions.get(i).text(), url));
            }


        }
        catch(Throwable t) {
            t.printStackTrace();
        }

        return listOfEvents;
    }

    public ArrayList<Event> getListOfEvents(){
        return listOfEvents;
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

    @Override
    protected void onPostExecute(ArrayList<Event> s) {
        //Question the ordering of these two lines. May need to call display.setText before super executes.
        super.onPostExecute(s);
        EventListAdapter listAdapter = new EventListAdapter(activity,s);
        ListView listView = (ListView) activity.findViewById(R.id.eventListView);
        listView.setAdapter(listAdapter);
    }
}





