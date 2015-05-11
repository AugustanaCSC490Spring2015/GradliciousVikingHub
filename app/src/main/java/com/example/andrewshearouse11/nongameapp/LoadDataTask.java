package com.example.andrewshearouse11.nongameapp;

import android.os.AsyncTask;
import android.provider.DocumentsContract;
import android.util.Log;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.jsoup.nodes.Element;

/**
 * Created by andrewshearouse11 on 5/5/2015.
 */
public class LoadDataTask extends AsyncTask<String, Void, String> {
    @Override
    protected String doInBackground(String... strings) {
        StringBuilder builder = new StringBuilder();
        try {
            Log.d("JSwa", "Connecting to ["+strings[0]+"]");
            Document doc  = Jsoup.connect(strings[0]).get();
            Log.d("JSwa", "Connected to ["+strings[0]+"]");
            // Get document (HTML page) title
            String title = doc.title();
            Log.d("JSwA", "Title ["+title+"]");
            builder.append("Title: " + title + "\r\n");

            // Get meta info//
            Elements metaElems = doc.select("meta");
            builder.append("META DATA\r\n");
            for (Element metaElem : metaElems) {
                String name = metaElem.attr("name");
                String content = metaElem.attr("content");
                builder.append("name [" + name + "] - content [" + content + "] \r\n");
            }

            Elements topicList = doc.select("h2.topic");
            builder.append("Topic list\r\n");
            for (Element topic : topicList) {
                String data = topic.text();

                builder.append("Data [" + data + "] \r\n");
            }

        }
        catch(Throwable t) {
            t.printStackTrace();
        }

        return builder.toString();
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
    }
}