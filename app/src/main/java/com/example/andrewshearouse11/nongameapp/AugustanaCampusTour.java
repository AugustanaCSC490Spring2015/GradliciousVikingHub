package com.example.andrewshearouse11.nongameapp;

import android.app.Activity;
import android.location.Location;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import com.google.android.gms.maps.*;
import com.google.android.gms.maps.model.*;

import java.util.ArrayList;

/**
 * Created by Ethan on 4/16/2015.
 */
public class AugustanaCampusTour extends Activity implements OnMapReadyCallback {

    GoogleMap mainMap;
    Marker markerHanson;
    Marker markerOlin;
    Marker markerDenkmann;
    Marker markerOldmain;
    Marker markerEvald;
    Marker markerBerg;
    Marker markerCent;
    Marker markerCollegeCenter;
    Marker markerLibrary;

    Building hansonBuilding;
    Building olinBuilding;
    Building denkmannBuilding;
    Building oldMainBuilding;
    Building evaldBuilding;
    Building bergendoffBuilding;
    Building centennialBuilding;
    Building collegeCenterBuilding;
    Building libraryBuilding;

    ArrayList<Building> buildingArrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.campus_map_layout);

        //Setup Map Fragment
        MapFragment mapFragment = (MapFragment) getFragmentManager().findFragmentById(R.id.mapviewfragment);
        mapFragment.getMapAsync(this);



    }

    @Override
    public void onMapReady(GoogleMap map) {

        mainMap = map;
        mainMap.setMyLocationEnabled(true);


        //set onMyLocationChangeListener
        mainMap.setOnMyLocationChangeListener(onMyLocationChange);

        hansonBuilding = new Building("Hanson Hall of Science",new LatLng(41.503743, -90.551306));
        olinBuilding = new Building("Olin Center",new LatLng(41.503118, -90.550580));
        denkmannBuilding = new Building("Denkmann",new LatLng(41.504452, -90.550603));
        oldMainBuilding = new Building("Old Main",new LatLng(41.504345, -90.549501));
        evaldBuilding = new Building("Evald Hall",new LatLng(41.505120, -90.550086));
        bergendoffBuilding = new Building("Bergendoff Hall",new LatLng(41.505453, -90.549239));
        centennialBuilding = new Building("Centennial Hall",new LatLng(41.505099, -90.548690));
        collegeCenterBuilding = new Building("College Center",new LatLng(41.504345, -90.548235));
        libraryBuilding = new Building("Thomas Tredway Library",new LatLng(41.502316, -90.550134));

        buildingArrayList.add(hansonBuilding);
        buildingArrayList.add(olinBuilding);
        buildingArrayList.add(denkmannBuilding);
        buildingArrayList.add(oldMainBuilding);
        buildingArrayList.add(evaldBuilding);
        buildingArrayList.add(bergendoffBuilding);
        buildingArrayList.add(centennialBuilding);
        buildingArrayList.add(collegeCenterBuilding);
        buildingArrayList.add(libraryBuilding);

        markerHanson = mainMap.addMarker(new MarkerOptions().position(hansonBuilding.getLatLng()).visible(false));
        markerOlin = mainMap.addMarker(new MarkerOptions().position(olinBuilding.getLatLng()).visible(false));
        markerDenkmann = mainMap.addMarker(new MarkerOptions().position(denkmannBuilding.getLatLng()).visible(false));
        markerOldmain = mainMap.addMarker(new MarkerOptions().position(oldMainBuilding.getLatLng()).visible(false));
        markerEvald = mainMap.addMarker(new MarkerOptions().position(evaldBuilding.getLatLng()).visible(false));
        markerBerg = mainMap.addMarker(new MarkerOptions().position(bergendoffBuilding.getLatLng()).visible(false));
        markerCent = mainMap.addMarker(new MarkerOptions().position(centennialBuilding.getLatLng()).visible(false));
        markerCollegeCenter = mainMap.addMarker(new MarkerOptions().position(collegeCenterBuilding.getLatLng()).visible(false));
        markerLibrary = mainMap.addMarker(new MarkerOptions().position(libraryBuilding.getLatLng()).visible(false));

    }


    //On Location Change
    public GoogleMap.OnMyLocationChangeListener onMyLocationChange = new GoogleMap.OnMyLocationChangeListener() {
        @Override
        public void onMyLocationChange (Location location) {

            buildingsInRadius(location);

            //buildingsInBounds(location);
        }
    };

    public void buildingsInBounds(Location location){
        //Hanson Hall
        if(inBounds(location, new LatLng(41.503208, -90.551755), new LatLng(41.504304, -90.550639))){
            //Toast.makeText(getBaseContext(), "At Hanson", Toast.LENGTH_LONG) .show();
            markerHanson.setVisible(true);
        }else{
            if(markerHanson.isVisible()){
                markerHanson.setVisible(false);
            }
        }

        // Olin
        if(inBounds(location, new LatLng(41.502934, -90.551041), new LatLng(41.503577, -90.550177))){
            //Toast.makeText(getBaseContext(), "At Olin", Toast.LENGTH_LONG) .show();
            markerOlin.setVisible(true);
        }else{
            if(markerOlin.isVisible()){
                markerOlin.setVisible(false);
            }
        }

        // Denkmann Hall
        if(inBounds(location, new LatLng(41.504224, -90.550971), new LatLng(41.504714, -90.550183))){
            markerDenkmann.setVisible(true);
        }else{
            if(markerDenkmann.isVisible()){
                markerDenkmann.setVisible(false);
            }
        }

        // Old Main
        if(inBounds(location, new LatLng(41.504019, -90.549963), new LatLng(41.504694, -90.548981))){
            markerOldmain.setVisible(true);
        }else{
            if(markerOldmain.isVisible()){
                markerOldmain.setVisible(false);
            }
        }

        // Evald Hall
        if(inBounds(location, new LatLng(41.504867, -90.550515), new LatLng(41.505329, -90.549614))){
            markerEvald.setVisible(true);
        }else{
            if(markerEvald.isVisible()){
                markerEvald.setVisible(false);
            }
        }

        // Bergendoff/Centennial Hall
        if(inBounds(location, new LatLng(41.504851, -90.549523), new LatLng(41.505988, -90.548268))){
            markerBerg.setVisible(true);
            markerCent.setVisible(true);
        }else{
            if(markerBerg.isVisible() && markerCent.isVisible()){
                markerBerg.setVisible(false);
                markerCent.setVisible(false);
            }
        }

        // College Center
        if(inBounds(location, new LatLng(41.504035, -90.548783), new LatLng(41.504646, -90.547704))){
            markerCollegeCenter.setVisible(true);
        }else{
            if(markerCollegeCenter.isVisible()){
                markerCollegeCenter.setVisible(false);
            }
        }

        // Library
        if(inBounds(location, new LatLng(41.501850, -90.551105), new LatLng(41.502669, -90.549528))){
            markerLibrary.setVisible(true);
        }else{
            if(markerLibrary.isVisible()){
                markerLibrary.setVisible(false);
            }
        }
    }

    //inBounds: returns true if location is within given bounds
    //location: location object to test if in bounds
    //southWest: southwestern corner of bounds
    //northEast: northeastern corner of bounds
    public Boolean inBounds(Location location, LatLng southWest, LatLng northEast) {
        double locationLat = location.getLatitude();
        double locationLng = location.getLongitude();
        return (locationLat >= southWest.latitude && locationLat <= northEast.latitude && locationLng >= southWest.longitude && locationLng <= northEast.longitude);
    }

    //populates nearBuildings listView with buildings that are within radius
    public void buildingsInRadius(Location location){

        for(Building building : buildingArrayList) {
            if (inRadius(location, building.getLatLng(), 40)) {
                //add to near buildings list view
                Toast.makeText(getBaseContext(), building.getBuildingName() , Toast.LENGTH_SHORT).show();
            } else {
                //if in near buildings list view remove from near buildings list view
            }
        }
    }

    //returns true if checkLocation is within specified radius of centerLocation
    public Boolean inRadius(Location checkLocation, LatLng centerLocation, int radius){

        float[] results = new float[1];
        Location.distanceBetween(checkLocation.getLatitude() , checkLocation.getLongitude(),
                centerLocation.latitude, centerLocation.longitude, results);
        if(results[0] <= radius){
            return true;
        }

        return false;
    }
}