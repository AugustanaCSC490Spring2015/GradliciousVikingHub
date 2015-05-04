package com.example.andrewshearouse11.nongameapp;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.location.Location;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;


/**
 * Created by Ethan on 4/16/2015.
 */
public class BuildingInformationScreen extends Activity{

    String buildingName;
    String buildingInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.building_information);

        Intent intent = getIntent();
        buildingName = intent.getStringExtra("buildingName");
        buildingInfo = intent.getStringExtra("buildingInfo");

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

        setBuildingImage();

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