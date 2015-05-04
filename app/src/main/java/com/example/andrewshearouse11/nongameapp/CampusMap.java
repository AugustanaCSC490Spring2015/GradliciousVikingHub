package com.example.andrewshearouse11.nongameapp;

import android.app.Activity;
import android.content.Intent;
import android.location.Location;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Window;
import android.view.WindowManager;

import com.google.android.gms.maps.*;
import com.google.android.gms.maps.model.*;

/**
 * Created by Ethan on 4/16/2015.
 */
public class CampusMap extends Activity implements OnMapReadyCallback {

    GoogleMap mainMap;
    String buildingLat;
    String buildingLng;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.campus_map_layout);

        Intent intent = getIntent();
        buildingLat = intent.getStringExtra("buildingLat");
        buildingLng = intent.getStringExtra("buildingLng");

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


        if(!buildingLat.isEmpty()){
            mainMap.addMarker(new MarkerOptions().position(new LatLng(Double.parseDouble(buildingLat), Double.parseDouble(buildingLng))));
        }
    }


    //On Location Change
    public GoogleMap.OnMyLocationChangeListener onMyLocationChange = new GoogleMap.OnMyLocationChangeListener() {
        @Override
        public void onMyLocationChange (Location location) {


        }
    };

}
