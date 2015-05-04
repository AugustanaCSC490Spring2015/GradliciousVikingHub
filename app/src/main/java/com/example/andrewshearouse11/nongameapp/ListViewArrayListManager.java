package com.example.andrewshearouse11.nongameapp;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.maps.model.LatLng;
import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ethan on 5/3/2015.
 */
public class ListViewArrayListManager {

    private ArrayList<Building> mainArrayList;
    private int numProximateBuildings;
    private ArrayList<Building> defaultBuildingsArrayList;
    Context context;

    public ListViewArrayListManager(Context context){
        this.context = context;
        mainArrayList = new ArrayList<Building>();
        defaultBuildingsArrayList = new ArrayList<Building>();
        numProximateBuildings = 0;
        populateDefault();
    }

    private void populateDefault(){
        mainArrayList.add(new Building("Denkmann", new LatLng(41.504452, -90.550603)));
        mainArrayList.add(new Building("Old Main", new LatLng(41.504345, -90.549501)));
        mainArrayList.add(new Building("Evald Hall", new LatLng(41.505120, -90.550086)));
        mainArrayList.add(new Building("Bergendoff Hall", new LatLng(41.505453, -90.549239)));
        mainArrayList.add(new Building("Centennial Hall", new LatLng(41.505099, -90.548690)));
        mainArrayList.add(new Building("College Center", new LatLng(41.504345, -90.548235)));
        mainArrayList.add(new Building("Thomas Tredway Library", new LatLng(41.502316, -90.550134)));
        for(Building building : mainArrayList){
            defaultBuildingsArrayList.add(building);
        }
        Log.w("defaultBuildings", defaultBuildingsArrayList.toString());
    }

    private void pullFromParse(){

        final ParseQuery<ParseObject> query = ParseQuery.getQuery("AcademicBuildings");
        query.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> parseObjects, ParseException e) {

                if(e==null){
                    int i = 0;
                    while(i < parseObjects.size()){
                        String buildingName = parseObjects.get(i).getString("buildingName");
                        String buildingInfo = "YEAR OPENED: " + parseObjects.get(i).getInt("yearOpened");
                        buildingInfo += "\nMAJORS: " + parseObjects.get(i).getString("majors");
                        buildingInfo += "\nBUILDING SERVICES: " + parseObjects.get(i).getString("buildingServices");
                        mainArrayList.add(new Building(buildingName ,new LatLng(0,0), buildingInfo));
                        i++;
                    }
                }else{
                    Log.d("info", "Sad Panda");
                }
                Log.w("nothing left in parseQuery","");
                Toast.makeText(context,"Updated Building Information", Toast.LENGTH_SHORT);
            }
        });
    }

    public void clearMainArrayList(){
        mainArrayList.clear();
    }

    public void addProximateBuilding(Building building){
        mainArrayList.add(numProximateBuildings, building);
        numProximateBuildings ++;
    }
    public void removeProximateBuilding(Building building){
        for(int i = 0; i <= numProximateBuildings; i++){
            if(mainArrayList.get(i).equals(building)){
                mainArrayList.remove(i);
                numProximateBuildings --;
            }
        }
    }
    public Building getBuilding(int position){
        return mainArrayList.get(position);
    }
    public ArrayList<Building> getMainArrayList(){
        return mainArrayList;
    }
    public ArrayList<Building> getDefaultBuildingsArrayList(){
        return defaultBuildingsArrayList;
    }
}
