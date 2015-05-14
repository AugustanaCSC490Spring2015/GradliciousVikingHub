package com.edu.augustana.csc490.vikinghub;

import android.app.Activity;
import android.content.Intent;
import android.location.Location;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.google.android.gms.maps.*;
import com.google.android.gms.maps.model.*;

import java.util.ArrayList;

/**
 * Created by Ethan on 4/16/2015.
 */
public class AugustanaCampusTour extends Activity implements OnMapReadyCallback {

    GoogleMap mainMap;

    ArrayList<Building> defaultBuildingsArrayList;
    ListAdapter arrayAdapter;

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

            int adjustedPosition = position;
            ArrayList<Building> proximateBuildings = listViewArrayListManager.getProximateBuildingsArrayList();
            ArrayList<Building> defaultBuildings = listViewArrayListManager.getDefaultBuildingsArrayList();
            Building building = new Building("Error", new LatLng(0,0),0);

            Log.w("position", Integer.toString(position));
            if(position == 0){
                return;
            }
            if((position == 1) && (proximateBuildings.size()==0)){
                return;

            }
            if(position != 0 && position < proximateBuildings.size()+1 && proximateBuildings.size() != 0){
                building = (proximateBuildings.get(position-1));
            }
            if(position == proximateBuildings.size()+2 && proximateBuildings.size()==0){
                return;
            }
            if(position == proximateBuildings.size()+1 && proximateBuildings.size()!=0){
                return;
            }
            if(position > proximateBuildings.size()+2 && proximateBuildings.size()==0) {
                building = (defaultBuildings.get(position-proximateBuildings.size()-3));
            }
            if(position > proximateBuildings.size()+1 && proximateBuildings.size()!=0) {
                building = (defaultBuildings.get(position-proximateBuildings.size()-2));
            }

            Log.w(Integer.toString(position),"-Position");
            Intent buildingInformation = new Intent(getBaseContext(), BuildingInformationScreen.class);
            buildingInformation.putExtra("buildingName", building.getBuildingName());
            buildingInformation.putExtra("buildingInfo", building.getBuildingInfo());
            buildingInformation.putExtra("buildingLat", building.getLatLng().latitude);
            buildingInformation.putExtra("buildingLng", building.getLatLng().longitude);
            startActivity(buildingInformation);
        }
    };

    private void setUpListView(){
        arrayAdapter = new ListAdapter(this, listViewArrayListManager.getDefaultBuildingsArrayList(),listViewArrayListManager.getProximateBuildingsArrayList());
        listView.setAdapter(arrayAdapter);
        listView.setOnItemClickListener(listViewItemClickListener);
    }

    //populates nearBuildings listView with buildings that are within radius
    public void buildingsInRadius(Location location){

        for(Building building : defaultBuildingsArrayList) {
            if (inRadius(location, building.getLatLng(), building.getBuildingRadius())) {
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
            return true;
        }

        return false;
    }
}