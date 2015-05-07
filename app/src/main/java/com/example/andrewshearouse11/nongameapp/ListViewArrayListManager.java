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
import java.util.Map;

/**
 * Created by Ethan on 5/3/2015.
 */
public class ListViewArrayListManager {

    private ArrayList<Building> mainArrayList;
    private ArrayList<Building> proximateBuildingsArrayList;
    private ArrayList<Building> defaultBuildingsArrayList;
    private int numProximateBuildings;
    Context context;
    private SharedPreferences buildingsList;

    public ListViewArrayListManager(Context context){
        this.context = context;
        mainArrayList = new ArrayList<Building>();
        defaultBuildingsArrayList = new ArrayList<Building>();
        proximateBuildingsArrayList = new ArrayList<Building>();
        numProximateBuildings = 0;
        buildingsList = context.getSharedPreferences("buildings", context.MODE_PRIVATE);
        populateDefault();
    }

    private void populateDefault(){

        Map<String,?> keys = buildingsList.getAll();

        for(Map.Entry<String,?> entry : keys.entrySet()){

            String[] buildingInfo = ((String) entry.getValue()).split(";");

            double buildingLat = 0;
            double buildingLng = 0;
            String buildingInformation = "";

            for(String splitValue : buildingInfo){
                if(splitValue.startsWith("buildingLat")){
                    buildingLat = Double.parseDouble(splitValue.substring(splitValue.indexOf(" ")));
                }
                if(splitValue.startsWith("buildingLng")){
                    buildingLng = Double.parseDouble(splitValue.substring(splitValue.indexOf(" ")));
                }
                if(!splitValue.startsWith("buildingLat") && !splitValue.startsWith("buildingLng") && !splitValue.startsWith("updateAt")){
                    buildingInformation += splitValue + "\n\n";
                }
            }

            mainArrayList.add(new Building(entry.getKey(), new LatLng(buildingLat,buildingLng), buildingInformation));
        }

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
            }
        });
    }

    public void clearMainArrayList(){
        mainArrayList.clear();
    }

    public void addProximateBuilding(Building building){
        mainArrayList.add(numProximateBuildings, building);
        numProximateBuildings ++;
        proximateBuildingsArrayList.add(building);
    }
    public void removeProximateBuilding(Building building){
        for(int i = 0; i <= numProximateBuildings; i++){
            if(mainArrayList.get(i).equals(building)){
                mainArrayList.remove(i);
                numProximateBuildings --;
            }
        }
        proximateBuildingsArrayList.remove(building);
    }
    public boolean contains(Building building){
        return mainArrayList.contains(building);
    }
    public boolean containsInProximate(Building building){
        return proximateBuildingsArrayList.contains(building);
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
    public ArrayList<Building> getProximateBuildingsArrayList(){
        return proximateBuildingsArrayList;
    }
}
