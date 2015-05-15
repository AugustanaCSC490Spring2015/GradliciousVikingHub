package com.example.andrewshearouse11.nongameapp;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.util.Xml;

import com.google.android.gms.analytics.ecommerce.Product;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;
import android.os.AsyncTask.*;
import android.view.Menu;
import android.widget.TextView;

class ProductTest
{

    public String name;
    public String quantity;
    public String color;

}

public class EventsCalendar extends Activity {

        String urlString = "http://www.augustana.edu/x11818.xml";
        @Override
        protected void onCreate(Bundle savedInstanceState) {

            super.onCreate(savedInstanceState);
            setContentView(R.layout.events_layout);

            TextView display = (TextView)findViewById(R.id.info);

            String url = "http://www.survivingwithandroid.com/2014/02/android-weather-app-tutorial-step-by.html";
            HTMLParser parser = new HTMLParser();
            parser.sendDisplay(display);
            parser.execute(url);

        }

        /*private void parseXML(XmlPullParser parser) throws XmlPullParserException,IOException {
            ArrayList<ProductTest> products = null;
            int eventType = parser.getEventType();
            ProductTest currentProduct = null;

            while (eventType != XmlPullParser.END_DOCUMENT){
                String name = null;
                switch (eventType){
                    case XmlPullParser.START_DOCUMENT:
                        products = new ArrayList();
                        break;
                    case XmlPullParser.START_TAG:
                        name = parser.getName();
                        if (name.equals("product")){
                            currentProduct = new ProductTest();
                        } else if (currentProduct != null){
                            if (name.equals("productname")){
                                currentProduct.name = parser.nextText();
                            } else if (name.equals("productcolor")){
                                currentProduct.color = parser.nextText();
                            } else if (name.equals("productquantity")){
                                currentProduct.quantity= parser.nextText();
                            }
                        }
                        break;
                    case XmlPullParser.END_TAG:
                        name = parser.getName();
                        if (name.equalsIgnoreCase("product") && currentProduct != null){
                            products.add(currentProduct);
                        }
                }
                eventType = parser.next();
            }

            printProducts(products);
        }

    private void printProducts(ArrayList<ProductTest> products)
    {
        String content = "";
        Iterator<ProductTest> it = products.iterator();
        while(it.hasNext())
        {
            ProductTest currProduct  = it.next();
            content = content + "Product :" +  currProduct.name + "\n";
            content = content + "Quantity :" +  currProduct.quantity + "\n";
            content = content + "Color :" +  currProduct.color + "\n \n";

        }

        TextView display = (TextView)findViewById(R.id.info);
        display.setText(content);
    }*/

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }
}

class HTMLParser extends AsyncTask<String, Void, String> {
    TextView display;
    @Override
    protected String doInBackground(String... strings) {
        StringBuilder stringBuilder = new StringBuilder();
        try {
            Log.d("JSwa", "Connecting to [" + strings[0] + "]");
            Document doc  = Jsoup.connect(strings[0]).get();
            Log.d("JSwa", "Connected to ["+strings[0]+"]");
            // Get document (HTML page) title
            String title = doc.title();
            Log.d("JSwA", "Title ["+title+"]");
            stringBuilder.append("Title: " + title + "\r\n");

            // Get meta info
            Elements metaElems = doc.select("meta");
            stringBuilder.append("META DATA\r\n");
            for (Element metaElem : metaElems) {
                String name = metaElem.attr("name");
                String content = metaElem.attr("content");
                stringBuilder.append("name [" + name + "] - content [" + content + "] \r\n");
                Log.d("JSwA", "name ["+name+"] - content ["+content+"] \r\n");
            }

            Elements topicList = doc.select("h2.topic");
            stringBuilder.append("Topic list\r\n");
            for (Element topic : topicList) {
                String data = topic.text();

                stringBuilder.append("Data [" + data + "] \r\n");
                Log.d("JSwA", "Data ["+data+"]");
            }

        }
        catch(Throwable t) {
            t.printStackTrace();
        }

        return stringBuilder.toString();
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
}





