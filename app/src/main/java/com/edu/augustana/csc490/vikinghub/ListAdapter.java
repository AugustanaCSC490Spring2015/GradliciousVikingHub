package com.edu.augustana.csc490.vikinghub;

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
public class ListAdapter extends BaseAdapter {

    private ArrayList<Building> defaultBuildings;
    private ArrayList<Building> proximateBuildings;
    private LayoutInflater layoutInflater;
    private Context context;
    public ListAdapter(Context aContext, ArrayList<Building> defaultBuildings, ArrayList<Building> proximateBuildings) {
        this.defaultBuildings = defaultBuildings;
        this.proximateBuildings = proximateBuildings;
        layoutInflater = LayoutInflater.from(aContext);
        context = aContext;
    }

    @Override
    public int getCount() {
        if(proximateBuildings.size() == 0){
            return defaultBuildings.size()+3;
        }
        return defaultBuildings.size()+proximateBuildings.size()+2;
    }

    @Override
    public Object getItem(int position) {
        if(position < proximateBuildings.size()){
            return proximateBuildings.get(position);
        }else {
            return defaultBuildings.get(position - proximateBuildings.size());
        }
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        final Typeface font = Typeface.createFromAsset(context.getAssets(), "fonts/moon_light.otf");
        ViewHolder holder;
        if (convertView == null) {
            convertView = layoutInflater.inflate(R.layout.building_row, null);
            holder = new ViewHolder();
            holder.rowItem = (TextView) convertView.findViewById(R.id.rowItem);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        final float scale = context.getResources().getDisplayMetrics().density;
        int padding_10dp = (int) (10 * scale + 0.5f);
        int padding_20dp = (int) (20 * scale + 0.5f);
        if(position == 0){
            holder.rowItem.setText("Buildings Near You");
            holder.rowItem.setTypeface(font);
            holder.rowItem.setTextSize(24);
            holder.rowItem.setTextColor(Color.parseColor("#015a92"));
            holder.rowItem.setPadding(padding_10dp,padding_10dp,0,padding_10dp);
        }
        if((position == 1) && (proximateBuildings.size()==0)){
            Log.w("No buildings near you","");
            holder.rowItem.setText("No Buildings Near You");
            holder.rowItem.setTypeface(font);
            holder.rowItem.setTextSize(18);
            holder.rowItem.setTextColor(Color.parseColor("#151515"));
            holder.rowItem.setPadding(padding_20dp,padding_10dp,0,padding_10dp);
        }
        if(position != 0 && position < proximateBuildings.size()+1 && proximateBuildings.size() != 0){
            holder.rowItem.setText(proximateBuildings.get(position-1).getBuildingName());
            holder.rowItem.setTypeface(font);
            holder.rowItem.setTextSize(18);
            holder.rowItem.setTextColor(Color.parseColor("#151515"));
            holder.rowItem.setPadding(padding_20dp,padding_10dp,0,padding_10dp);
        }
        if(position == proximateBuildings.size()+2 && proximateBuildings.size()==0){
            holder.rowItem.setText("All Buildings");
            holder.rowItem.setTypeface(font);
            holder.rowItem.setTextSize(24);
            holder.rowItem.setTextColor(Color.parseColor("#015a92"));
            holder.rowItem.setPadding(padding_10dp,padding_10dp,0,padding_10dp);
        }
        if(position == proximateBuildings.size()+1 && proximateBuildings.size()!=0){
            holder.rowItem.setText("All Buildings");
            holder.rowItem.setTypeface(font);
            holder.rowItem.setTextSize(24);
            holder.rowItem.setTextColor(Color.parseColor("#015a92"));
            holder.rowItem.setPadding(padding_10dp,padding_10dp,0,padding_10dp);
        }
        if(position > proximateBuildings.size()+2 && proximateBuildings.size()==0) {
            holder.rowItem.setText(defaultBuildings.get(position-proximateBuildings.size()-3).getBuildingName());
            holder.rowItem.setTypeface(font);
            holder.rowItem.setTextSize(18);
            holder.rowItem.setTextColor(Color.parseColor("#151515"));
            holder.rowItem.setPadding(padding_20dp,padding_10dp,0,padding_10dp);
        }
        if(position > proximateBuildings.size()+1 && proximateBuildings.size()!=0) {
            holder.rowItem.setText(defaultBuildings.get(position-proximateBuildings.size()-2).getBuildingName());
            holder.rowItem.setTypeface(font);
            holder.rowItem.setTextSize(18);
            holder.rowItem.setTextColor(Color.parseColor("#151515"));
            holder.rowItem.setPadding(padding_20dp,padding_10dp,0,padding_10dp);
        }
        return convertView;
    }

    static class ViewHolder {
        TextView rowItem;
    }

}
