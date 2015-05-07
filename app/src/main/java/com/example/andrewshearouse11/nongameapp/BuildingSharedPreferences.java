package com.example.andrewshearouse11.nongameapp;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.util.Log;
import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import java.util.List;

/**
 * Created by Owner on 5/4/2015.
 */
public class BuildingSharedPreferences {
    private SharedPreferences buildingsList;
    final ParseQuery<ParseObject> query;
    Context context;

    public BuildingSharedPreferences(Context context, String[] buildingKeys){
        this.context = context;
        buildingsList = context.getSharedPreferences("buildings", context.MODE_PRIVATE);
        query = ParseQuery.getQuery("AcademicBuildings");
        query.findInBackground(new FindCallback<ParseObject>() {
            @Override
            public void done(List<ParseObject> parseObjects, ParseException e) {
                if (e == null) {
                    updatePreferences(parseObjects);
                } else {
                    Log.e("ERROR", "Query to Parse caused an Error: " + e);
                }
            }
        });
    }

    // This method will create/update the current sharedPreferences for the buildings
    public void updatePreferences(List<ParseObject> parseObjects){
        Editor editor = buildingsList.edit();

        String buildingName = "";
        String updatedDate = "";
        int i = 0;

        while(i < parseObjects.size()){
            //pulls out the name of the building within each parseObject for the query
            buildingName = parseObjects.get(i).getString("buildingName");
            //pulls out the current updatedAt value for the parseObject in question.  Used when
            //checking to see if table object has been updated recently
            updatedDate = parseObjects.get(i).getUpdatedAt().toString();

            //First checks if the current buildingName exists as a key, string pair
            if(buildingsList.getString(buildingName, "").equals("")){
                editor.putString(buildingName, createDataString(parseObjects.get(i))).apply();
            }else{
                //If it does exist it will check and see if the update values are the same
                String currentData = buildingsList.getString(buildingName, "");
                String[] parts = currentData.split(";");
                if(!parts[0].substring(parts[0].indexOf(" ")).equals(updatedDate)){
                    Log.w("buildingDataUpdating","");
                    editor.putString(buildingName, createDataString(parseObjects.get(i))).apply();
                }
            }
            i++;
        }
    }

    /* This method will create the data string for each building.  This method can easily be modified
    based upon the different columns in the table we are working with. */
    public String createDataString(ParseObject object){
        String buildingData = "";
        buildingData = buildingData + "updateAt: " + object.getUpdatedAt() + ";";
        if(!object.getString("buildingLat").equals("null")){
            buildingData = buildingData + "buildingLat: "+ object.getString("buildingLat") + ";";
        }
        if(!object.getString("buildingLng").equals("null")){
            buildingData = buildingData + "buildingLng: "+ object.getString("buildingLng") + ";";
        }
        if(!object.getNumber("yearOpened").equals("null")){
            buildingData = buildingData + "Opened: " + object.getNumber("yearOpened") + ";";
        }
        if(!object.getString("majors").equals("null")){
            buildingData = buildingData + "Majors: " + object.getString("majors") + ";";
        }
        if(!object.getString("fact1").equals("null")){
            buildingData = buildingData + object.getString("fact1") + ";";
        }
        if(!object.getString("buildingServices").equals("null")){
            buildingData = buildingData + object.getString("buildingServices") + ";";
        }
        if(!object.getString("groupsWithin").equals("null")){
            buildingData = buildingData + "Groups Located Here: " + object.getString("groupsWithin");
        }
        return buildingData;
    }
}
