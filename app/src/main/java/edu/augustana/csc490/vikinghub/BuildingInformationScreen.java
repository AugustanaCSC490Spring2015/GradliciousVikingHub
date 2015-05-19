package edu.augustana.csc490.vikinghub;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;


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
        final Typeface font2 = Typeface.createFromAsset(this.getAssets(), "fonts/HelveticaNeue_Thin.otf");

        TextView buildingNameTextView = (TextView) findViewById(R.id.buildingName);
        buildingNameTextView.setTypeface(font);
        buildingNameTextView.setTextSize(24);
        buildingNameTextView.setTextColor(Color.parseColor("#151515"));
        buildingNameTextView.setText(buildingName);

        TextView buildingInformationTextView = (TextView) findViewById(R.id.buildingInformatin);
        buildingInformationTextView.setTypeface(font2);
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

    public void setBuildingImage(){
        ImageView buildingImageView = (ImageView) findViewById(R.id.buildingImageView);

        String buildingImageName;
        if(buildingName.contains(" ")) {
            buildingImageName = buildingName.substring(0, buildingName.indexOf(" "));
        }else{
            buildingImageName = buildingName;
        }

        switch (buildingImageName){
            case "Hanson":
                buildingImageView.setImageResource(R.mipmap.hanson);
                break;
            case "Olin":
                buildingImageView.setImageResource(R.mipmap.olin);
                break;
            case "Old":
                buildingImageView.setImageResource(R.mipmap.old);
                break;
            case "Bergendoff":
                buildingImageView.setImageResource(R.mipmap.bergendoff);
                break;
            case "College":
                buildingImageView.setImageResource(R.mipmap.college);
                break;
            case "Studio":
                buildingImageView.setImageResource(R.mipmap.art);
                break;
            case "Roy":
                buildingImageView.setImageResource(R.mipmap.carver);
                break;
            case "Austin":
                buildingImageView.setImageResource(R.mipmap.austin);
                break;
            case "Centennial":
                buildingImageView.setImageResource(R.mipmap.centennial);
                break;
            case "Sorenson":
                buildingImageView.setImageResource(R.mipmap.sorenson);
                break;
            case "Anderson":
                buildingImageView.setImageResource(R.mipmap.anderson);
                break;
            case "Andreen":
                buildingImageView.setImageResource(R.mipmap.andreen);
                break;
            case "Arbaugh":
                buildingImageView.setImageResource(R.mipmap.arbaugh);
                break;
            case "Bartholomew":
                buildingImageView.setImageResource(R.mipmap.bartholomew);
                break;
            case "Betsey":
                buildingImageView.setImageResource(R.mipmap.betsy);
                break;
            case "Center":
                buildingImageView.setImageResource(R.mipmap.thomas);
                break;
            case "Denkman":
                buildingImageView.setImageResource(R.mipmap.denkmann);
                break;
            case "Doris":
                buildingImageView.setImageResource(R.mipmap.doris);
                break;
            case "Emmy":
                buildingImageView.setImageResource(R.mipmap.evald);
                break;
            case "Erickson":
                buildingImageView.setImageResource(R.mipmap.erickson);
                break;
            case "House":
                buildingImageView.setImageResource(R.mipmap.house);
                break;
            case"Naeseth":
                buildingImageView.setImageResource(R.mipmap.naeseth);
                break;
            case "Parkander":
                buildingImageView.setImageResource(R.mipmap.parkander);
                break;
            case "Pepsico":
                buildingImageView.setImageResource(R.mipmap.pepsico);
                break;
            case "Ryden":

                break;
            case "Seminary":

                break;
            case "Swanson":
                buildingImageView.setImageResource(R.mipmap.swanson);
                break;
            case "Swenson":
                buildingImageView.setImageResource(R.mipmap.swenson);
                break;
            case "Thomas":
                buildingImageView.setImageResource(R.mipmap.thomas);
                break;
            case "Westerlin":
                buildingImageView.setImageResource(R.mipmap.westerlin);
                break;
        }
    }


}