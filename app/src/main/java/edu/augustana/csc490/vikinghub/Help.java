package edu.augustana.csc490.vikinghub;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

/**
 * Created by Ethan on 5/16/2015.
 */
public class Help extends Activity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.help_screen);

        ArrayList<String> helpArrayList = new ArrayList<>();

        helpArrayList.add("Main Phone Number");
        //(309)-794-7000

        helpArrayList.add("Public Safety (Emergency)");
        //(309)-794-7711

        helpArrayList.add("Augustana Website");
        //www.augustana.edu

        helpArrayList.add("Augustana Gmail");
        //www.email.augustana.edu

        helpArrayList.add("Academic Calendar");
        //https://www.augustana.edu/academics/registrar/academic-calendar

        helpArrayList.add("Moodle");
        //moodle.augustana.edu

        helpArrayList.add("Arches(Web Advisor)");
        //webadvisor.augustana.edu

        ListView listView = (ListView) findViewById(R.id.helpListView);
        HelpListAdapter listAdapter = new HelpListAdapter(this,helpArrayList);
        listView.setAdapter(listAdapter);
        listView.setOnItemClickListener(onItemClick);
    }
    private ListView.OnItemClickListener onItemClick = new ListView.OnItemClickListener(){
        @Override
        public void onItemClick(AdapterView<?> parent, View view,int position, long id) {

            //sets action based on the item in the ListView selected
            switch (position){
                case 0:
                    if(isTelephonyEnabled()) {
                        Intent callAugie = new Intent(Intent.ACTION_DIAL);
                        callAugie.setData(Uri.parse("tel:3097947000"));
                        startActivity(callAugie);
                    }else{
                        AlertDialog.Builder builder = new AlertDialog.Builder(Help.this);
                        builder.setTitle("Main Phone Number");
                        builder.setMessage("(309)-794-7000");
                        builder.show();
                    }
                    break;
                case 1:
                    if(isTelephonyEnabled()) {
                        Intent callEmergency = new Intent(Intent.ACTION_DIAL);
                        callEmergency.setData(Uri.parse("tel:3097947711"));
                        startActivity(callEmergency);
                    }else{
                        AlertDialog.Builder builder = new AlertDialog.Builder(Help.this);
                        builder.setTitle("Public Safety (Emergency)");
                        builder.setMessage("(309)-794-7711");
                        builder.show();
                    }
                    break;
                case 2:
                    Intent goToAugustana = new Intent(Intent.ACTION_VIEW);
                    goToAugustana.setData(Uri.parse("http://www.augustana.edu/"));
                    startActivity(goToAugustana);
                    break;
                case 3:
                    Intent goToMail = new Intent(Intent.ACTION_VIEW);
                    goToMail.setData(Uri.parse("http://email.augustana.edu"));
                    startActivity(goToMail);
                    break;
                case 4:
                    Intent goToCalendar = new Intent(Intent.ACTION_VIEW);
                    goToCalendar.setData(Uri.parse("https://www.augustana.edu/academics/registrar/academic-calendar"));
                    startActivity(goToCalendar);
                    break;
                case 5:
                    Intent goToMoodle = new Intent(Intent.ACTION_VIEW);
                    goToMoodle.setData(Uri.parse("https://moodle.augustana.edu/"));
                    startActivity(goToMoodle);
                    break;
                case 6:
                    Intent goToArches = new Intent(Intent.ACTION_VIEW);
                    goToArches.setData(Uri.parse("https://webadvisor.augustana.edu/"));
                    startActivity(goToArches);
                    break;
            }

        }
    };

    private boolean isTelephonyEnabled(){
        TelephonyManager tm = (TelephonyManager)getSystemService(TELEPHONY_SERVICE);
        //return tm != null && tm.getSimState()==TelephonyManager.SIM_STATE_READY;
        return false;
    }

}
