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
    private String eventUrl;
    public Event(String title, String date, String description, String url){
        eventTitle = title;
        eventTitle = removeSlashes(eventTitle);
        eventDate = date;
        eventDate = removeSlashes(eventDate);
        eventDescription = description;
        eventDescription = removeSlashes(eventDescription);
        eventUrl = url;
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
    public String getEventUrl(){
        return eventUrl;
    }

    /**
     * @param information the String of information that is being edited
     * @return String with backslashes removed
     * Fixes the occasional problem of adding backslashes to the text from the HTML when there is
     * a quotation mark or an apostrophe
     */
    public String removeSlashes(String information){
        information.replace("\\", "");
        return information;
    }
}
