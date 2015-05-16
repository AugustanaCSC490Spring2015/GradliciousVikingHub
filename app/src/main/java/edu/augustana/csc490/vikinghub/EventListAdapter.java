package edu.augustana.csc490.vikinghub;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Ethan on 5/4/2015.
 */
public class EventListAdapter extends BaseAdapter {

    private ArrayList<Event> eventArrayList;
    private LayoutInflater layoutInflater;
    private Context context;
    public EventListAdapter(Context aContext, ArrayList<Event> eventArrayList) {
        this.eventArrayList = eventArrayList;
        layoutInflater = LayoutInflater.from(aContext);
        context = aContext;
    }

    @Override
    public int getCount() {
        return eventArrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return eventArrayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        final Typeface font = Typeface.createFromAsset(context.getAssets(), "fonts/moon_light.otf");
        final Typeface font2 = Typeface.createFromAsset(context.getAssets(), "fonts/HelveticaNeue_Thin.otf");
        ViewHolder holder;
        if (convertView == null) {
            convertView = layoutInflater.inflate(R.layout.event_row, null);
            holder = new ViewHolder();
            holder.eventTitle = (TextView) convertView.findViewById(R.id.eventTitle);
            holder.eventDate = (TextView) convertView.findViewById(R.id.eventDate);
            holder.eventDescription = (TextView) convertView.findViewById(R.id.eventDescription);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        Event event = eventArrayList.get(position);
        holder.eventTitle.setText(event.getEventTitle());
        holder.eventTitle.setTypeface(font);
        holder.eventTitle.setTextSize(18);
        holder.eventTitle.setTextColor(Color.parseColor("#015a92"));
        holder.eventDate.setText(event.getEventDate());
        holder.eventDate.setTypeface(font);
        holder.eventDate.setTextSize(16);
        holder.eventDate.setTextColor(Color.parseColor("#151515"));
        holder.eventDescription.setText(event.getEventDescription());
        holder.eventDescription.setTypeface(font2);
        holder.eventDescription.setTextSize(18);
        holder.eventDescription.setTextColor(Color.parseColor("#151515"));

        return convertView;
    }

    static class ViewHolder {
        TextView eventTitle;
        TextView eventDate;
        TextView eventDescription;
    }

}
