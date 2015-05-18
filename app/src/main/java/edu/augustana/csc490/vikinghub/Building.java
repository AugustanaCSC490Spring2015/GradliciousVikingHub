package edu.augustana.csc490.vikinghub;

import com.google.android.gms.maps.model.LatLng;

/**
 * Created by ethanwojcinski11 on 5/1/2015.
 */
//Class to define Building objects
public class Building implements Comparable{

    String buildingName;
    LatLng buildingLatLng;
    String buildingInfo;
    int buildingRadius;

    public Building(String buildingName, LatLng buildingLatLng, int buildingRadius){
        this.buildingName = buildingName;
        this.buildingLatLng = buildingLatLng;
        this.buildingInfo = "";
        this.buildingRadius = buildingRadius;
    }
    public Building(String buildingName, LatLng buildingLatLng, String buildingInfo, int buildingRadius){
        this.buildingName = buildingName;
        this.buildingLatLng = buildingLatLng;
        this.buildingInfo = buildingInfo;
        this.buildingRadius = buildingRadius;
    }
    //getter methods
    public int getBuildingRadius(){
        return buildingRadius;
    }
    public LatLng getLatLng(){
        return buildingLatLng;
    }
    public String getBuildingName(){
        return buildingName;
    }
    public String toString(){
        return buildingName;
    }
    public String getBuildingInfo(){
        return buildingInfo;
    }

    public Boolean equals(Building testBuilding){
        return buildingName.equals(testBuilding.getBuildingName());
    }

    //used to sort buildings
    public int compareTo(Object t){
        Building building = (Building) t;
        return this.buildingName.compareTo(building.getBuildingName());
    }

}
