package com.example.andrewshearouse11.nongameapp;

import com.google.android.gms.maps.model.LatLng;

import java.util.ArrayList;

/**
 * Created by Ethan on 5/3/2015.
 */
public class ListViewArrayListManager {

    private ArrayList<Building> mainArrayList;
    private int numProximateBuildings;
    private ArrayList<Building> defaultBuildingsArrayList;

    public ListViewArrayListManager(){
        mainArrayList = new ArrayList<Building>();
        defaultBuildingsArrayList = new ArrayList<Building>();
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
        for(Building building : mainArrayList){
            defaultBuildingsArrayList.add(building);
        }
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
