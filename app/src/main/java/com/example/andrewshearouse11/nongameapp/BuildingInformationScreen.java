package com.example.andrewshearouse11.nongameapp;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.location.Location;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.List;


/**
 * Created by Ethan on 4/16/2015.
 */
public class BuildingInformationScreen extends Activity{

    String buildingName;
    String buildingInfo;
    double buildingLat;
    double buildingLng;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.building_information);

        Intent intent = getIntent();
        buildingName = intent.getStringExtra("buildingName");
        buildingInfo = intent.getStringExtra("buildingInfo");
        buildingLat = intent.getDoubleExtra("buildingLat",-1000);
        buildingLng = intent.getDoubleExtra("buildingLng",-1000);

        final Typeface font = Typeface.createFromAsset(this.getAssets(), "fonts/moon_light.otf");

        TextView buildingNameTextView = (TextView) findViewById(R.id.buildingName);
        buildingNameTextView.setTypeface(font);
        buildingNameTextView.setTextSize(24);
        buildingNameTextView.setTextColor(Color.parseColor("#151515"));
        buildingNameTextView.setText(buildingName);

        TextView buildingInformationTextView = (TextView) findViewById(R.id.buildingInformatin);
        buildingInformationTextView.setTypeface(font);
        buildingInformationTextView.setTextSize(18);
        buildingInformationTextView.setTextColor(Color.parseColor("#151515"));
        buildingInformationTextView.setText(buildingInfo);

        Button showOnMapButton = (Button) findViewById(R.id.showOnMapButton);
        showOnMapButton.setTypeface(font);
        showOnMapButton.setTextSize(18);
        showOnMapButton.setTextColor(Color.WHITE);

        showOnMapButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent campusMap = new Intent(getBaseContext(), CampusMap.class);
                campusMap.putExtra("buildingLat",buildingLat);
                campusMap.putExtra("buildingLng", buildingLng);
                campusMap.putExtra("buildingName", buildingName);
                startActivity(campusMap);
            }
        });
        setBuildingImage();
    }
    //Method will pull the information for that building from the localDataStore
    public void populateBuildingInfo(){
        ParseQuery<ParseObject> query = ParseQuery.getQuery("AcademicBuildings");
        query.whereEqualTo("buildingName", buildingName);
        query.fromLocalDatastore();
        query.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> parseObjects, ParseException e) {
                Log.d("RESULTS", parseObjects.size() + "");
            }
        });
    }


    public void setBuildingImage(){
        ImageView buildingImageView = (ImageView) findViewById(R.id.buildingImageView);

        String buildingImageName;
        if(buildingName.contains(" ")) {
            buildingImageName = buildingName.substring(0, buildingName.indexOf(" "));
        }else{
            buildingImageName = buildingName;
        }

        if(buildingImageName.equals("Hanson")){
            buildingImageView.setImageResource(R.mipmap.hanson);
        }else if(buildingImageName.equals("Thomas")){
            buildingImageView.setImageResource(R.mipmap.library);
        }
    }


}