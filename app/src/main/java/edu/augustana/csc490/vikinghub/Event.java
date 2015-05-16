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

/**
 * Created by andrewshearouse11 on 5/16/2015.
 */
public class Event {
    private String eventTitle;
    private String eventDate;
    private String eventDescription;
    public Event(String title, String date, String description){
        eventTitle = title;
        eventTitle = removeSlashes(eventTitle);
        eventDate = date;
        eventDate = removeSlashes(eventDate);
        eventDescription = description;
        eventDescription = removeSlashes(eventDescription);
    }

    public String getEventTitle(){
        return eventTitle;
    }
    public String getEventDate(){
        return eventDate;
    }
    public String getEventDescription(){
        return eventDescription;
    }

    public String removeSlashes(String information){
        information.replaceAll("\\\"", "'");
        information.replaceAll("\\\'", "'");
        return information;
    }
}
