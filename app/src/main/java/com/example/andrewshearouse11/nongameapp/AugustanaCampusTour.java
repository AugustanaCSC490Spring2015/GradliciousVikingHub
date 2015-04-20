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
    }


    //On Location Change
    public GoogleMap.OnMyLocationChangeListener onMyLocationChange = new GoogleMap.OnMyLocationChangeListener() {
        @Override
        public void onMyLocationChange (Location location) {

            if(inBounds(location, new LatLng(41.503272, -90.551696), new LatLng(41.504288, -90.550880))){
                Toast.makeText(getBaseContext(), "At Hanson", Toast.LENGTH_LONG) .show();
            }

        }
    };

    public Boolean inBounds(Location location, LatLng southWest, LatLng northEast) {
        double locationLat = location.getLatitude();
        double locationLng = location.getLongitude();
        return (locationLat >= southWest.latitude && locationLat <= northEast.latitude && locationLng <= southWest.longitude && locationLng >= northEast.longitude);
    }
}