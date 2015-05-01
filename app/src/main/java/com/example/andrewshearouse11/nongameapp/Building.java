package com.example.andrewshearouse11.nongameapp;

import com.google.android.gms.maps.model.LatLng;

/**
 * Created by ethanwojcinski11 on 5/1/2015.
 */
public class Building {

    String buildingName;
    LatLng buildingLatLng;

    public Building(String buildingName, LatLng buildingLatLng){
        this.buildingName = buildingName;
        this.buildingLatLng = buildingLatLng;
    }

    public LatLng getLatLng(){
        return buildingLatLng;
    }
    public String getBuildingName(){
        return buildingName;
    }
    public String getBuildingInfo(){
        return "BUILDING INFORMATION";
    }

}
