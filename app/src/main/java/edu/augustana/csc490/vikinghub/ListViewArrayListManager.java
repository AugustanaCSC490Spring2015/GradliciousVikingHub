package edu.augustana.csc490.vikinghub;

import android.content.Context;
import android.content.SharedPreferences;

import com.google.android.gms.maps.model.LatLng;

import java.util.ArrayList;
import java.util.Map;

/**
 * Created by Ethan on 5/3/2015.
 */
public class ListViewArrayListManager {

    private ArrayList<Building> mainArrayList;
    private ArrayList<Building> proximateBuildingsArrayList;
    private ArrayList<Building> defaultBuildingsArrayList;
    private int numProximateBuildings;
    private Context context;
    private SharedPreferences buildingsList;

    public ListViewArrayListManager(Context context){
        this.context = context;
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
            int buildingRadius = 0;
            String buildingInformation = "";

            for(String splitValue : buildingInfo){
                if(splitValue.startsWith("buildingLat")){
                    buildingLat = Double.parseDouble(splitValue.substring(splitValue.indexOf(" ")));
                }
                if(splitValue.startsWith("buildingLng")){
                    buildingLng = Double.parseDouble(splitValue.substring(splitValue.indexOf(" ")));
                }
                if(splitValue.startsWith("buildingRadius")){
                    buildingRadius = Integer.parseInt(splitValue.substring(splitValue.indexOf(" ")+1));
                }
                if(!splitValue.startsWith("buildingLat") && !splitValue.startsWith("buildingLng") && !splitValue.startsWith("updateAt") && !splitValue.startsWith("buildingRadius")){
                    buildingInformation += splitValue + "\n\n";
                }
            }
            defaultBuildingsArrayList.add(new Building(entry.getKey(), new LatLng(buildingLat,buildingLng), buildingInformation, buildingRadius));
        }
        sortDefaultBuildingsArrayList();
    }

    public void addProximateBuilding(Building building){
        proximateBuildingsArrayList.add(building);
    }
    public void removeProximateBuilding(Building building){
        proximateBuildingsArrayList.remove(building);
    }
    public boolean contains(Building building){
        return defaultBuildingsArrayList.contains(building) || proximateBuildingsArrayList.contains(building);
    }
    public boolean containsInProximate(Building building){
        return proximateBuildingsArrayList.contains(building);
    }
    public ArrayList<Building> getDefaultBuildingsArrayList(){
        return defaultBuildingsArrayList;
    }
    public ArrayList<Building> getProximateBuildingsArrayList(){
        return proximateBuildingsArrayList;
    }
    public void sortDefaultBuildingsArrayList(){
        java.util.Collections.sort(defaultBuildingsArrayList);
    }
}
