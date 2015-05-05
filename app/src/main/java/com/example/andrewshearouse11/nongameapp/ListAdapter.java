package com.example.andrewshearouse11.nongameapp;

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
            holder.buildingTitleView = (TextView) convertView.findViewById(R.id.rowTitle);
            holder.sectionView = (TextView) convertView.findViewById(R.id.seperator);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        Log.w("position", Integer.toString(position));
        if(position == 0){
            holder.sectionView.setText("Buildings Near You");
            holder.sectionView.setTypeface(font);
            holder.sectionView.setTextSize(24);
            holder.sectionView.setTextColor(Color.parseColor("#015a92"));

            holder.buildingTitleView.setAlpha(0);
        }
        if((position == 1) && (proximateBuildings.size()==0)){
            Log.w("no buildings near you","");
            holder.buildingTitleView.setText("No Buildings Near You");
            holder.buildingTitleView.setTypeface(font);
            holder.buildingTitleView.setTextSize(18);
            holder.buildingTitleView.setTextColor(Color.parseColor("#151515"));

        }
        if(position != 0 && position < proximateBuildings.size()+1 && proximateBuildings.size() != 0){
            holder.buildingTitleView.setText(proximateBuildings.get(position-1).getBuildingName());
            holder.buildingTitleView.setTypeface(font);
            holder.buildingTitleView.setTextSize(18);
            holder.buildingTitleView.setTextColor(Color.parseColor("#151515"));

        }
        if(position == proximateBuildings.size()+2 && proximateBuildings.size()==0){
            holder.sectionView.setText("All Buildings");
            holder.sectionView.setTypeface(font);
            holder.sectionView.setTextSize(24);
            holder.sectionView.setTextColor(Color.parseColor("#015a92"));
        }
        if(position == proximateBuildings.size()+1 && proximateBuildings.size()!=0){
            holder.sectionView.setText("All Buildings");
            holder.sectionView.setTypeface(font);
            holder.sectionView.setTextSize(24);
            holder.sectionView.setTextColor(Color.parseColor("#015a92"));
        }
        if(position > proximateBuildings.size()+2 && proximateBuildings.size()==0) {
            holder.buildingTitleView.setText(defaultBuildings.get(position-proximateBuildings.size()-3).getBuildingName());
            holder.buildingTitleView.setTypeface(font);
            holder.buildingTitleView.setTextSize(18);
            holder.buildingTitleView.setTextColor(Color.parseColor("#151515"));

        }
        if(position > proximateBuildings.size()+1 && proximateBuildings.size()!=0) {
            holder.buildingTitleView.setText(defaultBuildings.get(position-proximateBuildings.size()-2).getBuildingName());
            holder.buildingTitleView.setTypeface(font);
            holder.buildingTitleView.setTextSize(18);
            holder.buildingTitleView.setTextColor(Color.parseColor("#151515"));

        }


        return convertView;
    }

    static class ViewHolder {
        TextView buildingTitleView;
        TextView sectionView;
    }

}
