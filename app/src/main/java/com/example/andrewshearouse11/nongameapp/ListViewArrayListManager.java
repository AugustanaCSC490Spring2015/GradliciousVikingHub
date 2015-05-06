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
    private ArrayList<Building> proximateBuildingsArrayList;
    private ArrayList<Building> defaultBuildingsArrayList;
    private int numProximateBuildings;
    Context context;

    public ListViewArrayListManager(Context context){
        this.context = context;
        mainArrayList = new ArrayList<Building>();
        defaultBuildingsArrayList = new ArrayList<Building>();
        proximateBuildingsArrayList = new ArrayList<Building>();
        numProximateBuildings = 0;
        populateDefault();
    }

    private void populateDefault(){
        mainArrayList.add(new Building("Hanson Hall of Science", new LatLng(41.503743, -90.551306)));
        mainArrayList.add(new Building("Olin Center", new LatLng(41.503118, -90.550580)));
        mainArrayList.add(new Building("Denkmann", new LatLng(41.504452, -90.550603)));
        mainArrayList.add(new Building("Old Main", new LatLng(41.504345, -90.549501)));
        mainArrayList.add(new Building("Evald Hall", new LatLng(41.505120, -90.550086)));
        mainArrayList.add(new Building("Bergendoff Hall", new LatLng(41.505453, -90.549239)));
        mainArrayList.add(new Building("Centennial Hall", new LatLng(41.505099, -90.548690)));
        mainArrayList.add(new Building("College Center", new LatLng(41.504345, -90.548235)));
        mainArrayList.add(new Building("Thomas Tredway Library", new LatLng(41.502316, -90.550134)));
        mainArrayList.add(new Building("Sorenson Hall", new LatLng(0,0)));
        mainArrayList.add(new Building("Swenson Hall of Geosciences", new LatLng(0,0)));
        mainArrayList.add(new Building("PepsiCo Recreation Center", new LatLng(0,0)));
        mainArrayList.add(new Building("Studio Art Building", new LatLng(0,0)));
        mainArrayList.add(new Building("Doris and Victor Day Broadcasting Center", new LatLng(0,0)));
        mainArrayList.add(new Building("Austin E. Knowlton Outdoor Athletic Complex", new LatLng(0,0)));
        mainArrayList.add(new Building("Roy J. Carver Center for Physical Education", new LatLng(0,0)));
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
