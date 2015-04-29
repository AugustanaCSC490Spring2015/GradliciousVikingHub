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

        markerHanson = mainMap.addMarker(new MarkerOptions().position(new LatLng(41.503743, -90.551306)));
        markerOlin = mainMap.addMarker(new MarkerOptions().position(new LatLng(41.503118, -90.550580)));
        markerDenkmann = mainMap.addMarker(new MarkerOptions().position(new LatLng(41.504452, -90.550603)));
        markerOldmain = mainMap.addMarker(new MarkerOptions().position(new LatLng(41.504345, -90.549501)));
        markerEvald = mainMap.addMarker(new MarkerOptions().position(new LatLng(41.505120, -90.550086)));
        markerBerg = mainMap.addMarker(new MarkerOptions().position(new LatLng(41.505453, -90.549239)));
        markerCent = mainMap.addMarker(new MarkerOptions().position(new LatLng(41.505099, -90.548690)));

        markerHanson.setVisible(false);
        markerOlin.setVisible(false);
        markerDenkmann.setVisible(false);
        markerOldmain.setVisible(false);
        markerEvald.setVisible(false);
        markerBerg.setVisible(false);
        markerCent.setVisible(false);
    }


    //On Location Change
    public GoogleMap.OnMyLocationChangeListener onMyLocationChange = new GoogleMap.OnMyLocationChangeListener() {
        @Override
        public void onMyLocationChange (Location location) {

            //Hanson Hall
            if(inBounds(location, new LatLng(41.503272, -90.551696), new LatLng(41.504288, -90.550620))){
                //Toast.makeText(getBaseContext(), "At Hanson", Toast.LENGTH_LONG) .show();
                markerHanson.setVisible(true);
            }else{
                if(markerHanson.isVisible()){
                    markerHanson.setVisible(false);
                }
            }

            // Olin
            if(inBounds(location, new LatLng(41.502960, -90.550993), new LatLng(41.503554, -90.550041))){
                //Toast.makeText(getBaseContext(), "At Olin", Toast.LENGTH_LONG) .show();
                markerOlin.setVisible(true);
            }else{
                if(markerOlin.isVisible()){
                    markerOlin.setVisible(false);
                }
            }

            // Denkmann Hall
            if(inBounds(location, new LatLng(41.504204, -90.551100), new LatLng(41.504770, -90.550603))){
                markerDenkmann.setVisible(true);
            }else{
                if(markerDenkmann.isVisible()){
                    markerDenkmann.setVisible(false);
                }
            }

            // Old Main
            if(inBounds(location, new LatLng(41.503738, -90.550049), new LatLng(41.504746, -90.548922))){
                markerOldmain.setVisible(true);
            }else{
                if(markerOldmain.isVisible()){
                    markerOldmain.setVisible(false);
                }
            }

            // Evald Hall
            if(inBounds(location, new LatLng(41.504774, -90.550564), new LatLng(41.505433, -90.549426))){
                markerEvald.setVisible(true);
            }else{
                if(markerEvald.isVisible()){
                    markerEvald.setVisible(false);
                }
            }

            // Bergendoff/Centennial Hall
            if(inBounds(location, new LatLng(41.504959, -90.549571), new LatLng(41.506084, -90.547924))){
                markerBerg.setVisible(true);
                markerCent.setVisible(true);
            }else{
                if(markerBerg.isVisible() && markerCent.isVisible()){
                    markerBerg.setVisible(false);
                    markerCent.setVisible(false);
                }
            }
            //Toast.makeText(getBaseContext(), location.getLatitude() + " " + location.getLongitude(), Toast.LENGTH_LONG).show();

        }
    };

    //Returns true if location is within southWest and northWast Lat & Lng
    public Boolean inBounds(Location location, LatLng southWest, LatLng northEast) {
        double locationLat = location.getLatitude();
        double locationLng = location.getLongitude();
        return (locationLat >= southWest.latitude && locationLat <= northEast.latitude && locationLng >= southWest.longitude && locationLng <= northEast.longitude);
    }
}