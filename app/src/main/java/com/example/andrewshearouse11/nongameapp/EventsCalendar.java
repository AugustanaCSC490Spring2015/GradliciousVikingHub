package com.example.andrewshearouse11.nongameapp;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Xml;

import com.google.android.gms.analytics.ecommerce.Product;

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

            XmlPullParserFactory pullParserFactory;
            try{
                pullParserFactory = XmlPullParserFactory.newInstance();
                XmlPullParser parser = pullParserFactory.newPullParser();
                InputStream in_s = getResources().openRawResource(R.raw.temp);
                parser.setFeature(XmlPullParser.FEATURE_PROCESS_NAMESPACES, false);
                parser.setInput(in_s, null);
                parseXML(parser);
            } catch(XmlPullParserException e){
                e.printStackTrace();
            } catch(IOException e){
                e.printStackTrace();
            }

        }

        private void parseXML(XmlPullParser parser) throws XmlPullParserException,IOException {
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
                        if (name == "resource"){
                            currentProduct = new ProductTest();
                        } else if (currentProduct != null){
                            if (name == "productname"){
                                currentProduct.name = parser.nextText();
                            } else if (name == "productcolor"){
                                currentProduct.color = parser.nextText();
                            } else if (name == "productquantity"){
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
            content = content + "nnnProduct :" +  currProduct.name + "n";
            content = content + "Quantity :" +  currProduct.quantity + "n";
            content = content + "Color :" +  currProduct.color + "n";

        }

        TextView display = (TextView)findViewById(R.id.info);
        display.setText(content);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }
}





