package com.example.andrewshearouse11.nongameapp;

import com.google.android.gms.maps.model.LatLng;

/**
 * Created by ethanwojcinski11 on 5/1/2015.
 */
public class Building {

    String buildingName;
    LatLng buildingLatLng;
    String buildingInfo;

    public Building(String buildingName, LatLng buildingLatLng){
        this.buildingName = buildingName;
        this.buildingLatLng = buildingLatLng;
        this.buildingInfo = "";
    }
    public Building(String buildingName, LatLng buildingLatLng, String buildingInfo){
        this.buildingName = buildingName;
        this.buildingLatLng = buildingLatLng;
        this.buildingInfo = buildingInfo;
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

}
