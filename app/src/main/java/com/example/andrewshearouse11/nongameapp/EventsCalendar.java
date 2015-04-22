package com.example.andrewshearouse11.nongameapp;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Xml;

import com.google.android.gms.internal.ns;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import android.os.AsyncTask.*;

/**
 * Created by Ethan on 4/16/2015.
 * Added code to experiment with XML - Andy
 */

public class EventsCalendar extends Activity {

        String urlString = "http://www.augustana.edu/x11818.xml";

        @Override
        protected void onCreate(Bundle savedInstanceState) {

            super.onCreate(savedInstanceState);
            //setContentView(R.layout.events_calendar);
        }

    public void loadFeed(){
        new DownloadXmlTask().execute(urlString);

    }

    //XML Parser from http://developer.android.com/training/basics/network-ops/xml.html
}





