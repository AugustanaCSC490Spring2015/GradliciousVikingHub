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

import java.util.Calendar;
import java.util.Date;

public class EventsCalendar extends Activity {

        //URL of Augustana's HTML calendar
        private final String URL_STRING = "http://www.augustana.edu/prebuilt/acal/calpage.php?mode=js&viewid=13";

        @Override
        protected void onCreate(Bundle savedInstanceState) {

            super.onCreate(savedInstanceState);
            setContentView(R.layout.events_layout);

            TextView display = (TextView)findViewById(R.id.info);

//            Calendar cal = Calendar.getInstance();
//            cal.setTime(new Date());
//            String month = "&month=" + cal.get(Calendar.MONTH);
//            String year = "&year=" + cal.get(Calendar.YEAR);

            String url = URL_STRING;//+year+month;
            Log.w("URL",url);
            HTMLParser parser = new HTMLParser();
            parser.sendDisplay(display);
            parser.execute(URL_STRING);

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
    @Override
    protected String doInBackground(String... strings) {
        StringBuilder stringBuilder = new StringBuilder();
        String uglyDoc = "";
        try {
            Log.d("JSwa", "Connecting to [" + strings[0] + "]");
            Document doc  = Jsoup.connect(strings[0]).get();
            Log.d("JSwa", "Connected to ["+strings[0]+"]");

            //uglyDoc = doc.toString();
            Elements eventTitles = doc.getElementsByAttributeValueContaining("class", "cal_title");
            Elements eventDates = doc.getElementsByAttributeValueContaining("class", "cal_date");
            Elements temp = new Elements();
            for(Element date : eventDates){
                if(date.className().contains("large")){
                    temp.add(date);
                }
            }
            eventDates.removeAll(temp);
            Elements eventDescriptions = doc.getElementsByAttributeValueContaining("class", "cal_desc");
            for(int i = 0; i < eventTitles.size(); i++){
                Log.d("JSwa", "date"+eventDates.get(i).text());
                uglyDoc = uglyDoc + eventTitles.get(i).text() + "\n" + eventDates.get(i).text() + "\n" + eventDescriptions.get(i).text() + "\n";
            }

//            Elements eventTitles = doc.select("span.\\&quot;cal_title\\&quot;");
//            Elements eventDates = doc.select("div.\\&quot;cal_date\\&quot;");
//            Elements eventDescriptions = doc.select("div.\\&quot;cal_desc\\&quot;");
//            for(int i = 0; i < eventTitles.size(); i++){
//                Log.d("JSwa", "date"+eventDates.get(i).text());
//                uglyDoc = uglyDoc + eventTitles.get(i).text() + "\n" + eventDates.get(i).text() + "\n" + eventDescriptions.get(i).text() + "\n";
//            }
            String[] uglyDocArray = uglyDoc.split("\n");
            for(String line : uglyDocArray){
                Log.w("line",line);
            }

        }
        catch(Throwable t) {
            t.printStackTrace();
        }

        return uglyDoc;
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





