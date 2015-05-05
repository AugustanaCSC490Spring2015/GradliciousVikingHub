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

        listViewArrayListManager = new ListViewArrayListManager(getBaseContext());
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
            buildingInformation.putExtra("buildingLat", listViewArrayListManager.getBuilding(position).getLatLng().latitude);
            buildingInformation.putExtra("buildingLng", listViewArrayListManager.getBuilding(position).getLatLng().longitude);
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

    //populates nearBuildings listView with buildings that are within radius
    public void buildingsInRadius(Location location){

        for(Building building : defaultBuildingsArrayList) {
            if (inRadius(location, building.getLatLng(), 40)) {
                if(!listViewArrayListManager.containsInProximate(building)){
                    listViewArrayListManager.addProximateBuilding(building);
                    arrayAdapter.notifyDataSetChanged();
                }
            } else {
                listViewArrayListManager.removeProximateBuilding(building);
                arrayAdapter.notifyDataSetChanged();
            }
        }
    }

    //returns true if checkLocation is within specified radius of centerLocation
    public Boolean inRadius(Location checkLocation, LatLng centerLocation, int radius){

        float[] results = new float[1];
        Location.distanceBetween(checkLocation.getLatitude() , checkLocation.getLongitude(),
                centerLocation.latitude, centerLocation.longitude, results);
        if(results[0] <= radius){
            Toast.makeText(getBaseContext(),"In Radius", Toast.LENGTH_SHORT);
            return true;
        }

        return false;
    }
}