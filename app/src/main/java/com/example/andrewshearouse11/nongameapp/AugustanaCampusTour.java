package com.example.andrewshearouse11.nongameapp;

import android.app.Activity;
import android.content.Intent;
import android.location.Location;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
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

    ArrayList<Building> defaultBuildingsArrayList;
    ArrayAdapter<Building> arrayAdapter;

    ListView listView;
    ListViewArrayListManager listViewArrayListManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.campus_tour_layout);

        listViewArrayListManager = new ListViewArrayListManager();
        defaultBuildingsArrayList = listViewArrayListManager.getDefaultBuildingsArrayList();

        //Setup Map Fragment
        MapFragment mapFragment = (MapFragment) getFragmentManager().findFragmentById(R.id.mapviewtourfragment);
        mapFragment.getMapAsync(this);

        listView = (ListView) findViewById(R.id.buildingListView);
        setUpListView();
    }

    @Override
    public void onMapReady(GoogleMap map) {

        mainMap = map;
        mainMap.setMyLocationEnabled(true);


        //set onMyLocationChangeListener
        mainMap.setOnMyLocationChangeListener(onMyLocationChange);

        /*
        markerHanson = mainMap.addMarker(new MarkerOptions().position(hansonBuilding.getLatLng()).visible(false));
        markerOlin = mainMap.addMarker(new MarkerOptions().position(olinBuilding.getLatLng()).visible(false));
        markerDenkmann = mainMap.addMarker(new MarkerOptions().position(denkmannBuilding.getLatLng()).visible(false));
        markerOldmain = mainMap.addMarker(new MarkerOptions().position(oldMainBuilding.getLatLng()).visible(false));
        markerEvald = mainMap.addMarker(new MarkerOptions().position(evaldBuilding.getLatLng()).visible(false));
        markerBerg = mainMap.addMarker(new MarkerOptions().position(bergendoffBuilding.getLatLng()).visible(false));
        markerCent = mainMap.addMarker(new MarkerOptions().position(centennialBuilding.getLatLng()).visible(false));
        markerCollegeCenter = mainMap.addMarker(new MarkerOptions().position(collegeCenterBuilding.getLatLng()).visible(false));
        markerLibrary = mainMap.addMarker(new MarkerOptions().position(libraryBuilding.getLatLng()).visible(false));
        */
    }


    //On Location Change
    public GoogleMap.OnMyLocationChangeListener onMyLocationChange = new GoogleMap.OnMyLocationChangeListener() {
        @Override
        public void onMyLocationChange (Location location) {

            buildingsInRadius(location);

            //buildingsInBounds(location);
        }
    };

    public ListView.OnItemClickListener listViewItemClickListener = new ListView.OnItemClickListener(){
        @Override
        public void onItemClick(AdapterView<?> parent, View view,int position, long id) {
            Log.w(Integer.toString(position),"-Position");
            Intent buildingInformation = new Intent(getBaseContext(), BuildingInformationScreen.class);
            buildingInformation.putExtra("buildingName", listViewArrayListManager.getBuilding(position).getBuildingName());
            buildingInformation.putExtra("buildingInfo", listViewArrayListManager.getBuilding(position).getBuildingInfo());
            startActivity(buildingInformation);
        }
    };

    private void setUpListView(){
        arrayAdapter = new ArrayAdapter<Building>(
                this,
                android.R.layout.simple_list_item_1,
                listViewArrayListManager.getMainArrayList());
        listView.setAdapter(arrayAdapter);
        listView.setOnItemClickListener(listViewItemClickListener);
    }

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

        for(Building building : defaultBuildingsArrayList) {
            if (inRadius(location, building.getLatLng(), 40)) {
                listViewArrayListManager.addProximateBuilding(building);
            } else {
                listViewArrayListManager.removeProximateBuilding(building);
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