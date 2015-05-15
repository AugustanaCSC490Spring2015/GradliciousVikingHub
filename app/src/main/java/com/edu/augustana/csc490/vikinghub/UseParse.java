package com.edu.augustana.csc490.vikinghub;

import android.app.Application;
import com.parse.Parse;

/**
 * Created by Owner on 4/22/2015.
 */
public class UseParse extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Parse.enableLocalDatastore(this);
        Parse.initialize(this, "2GAreP3ttZmR3s6rsYLmOou88mhFOdBGbxKwr2q5", "kjUX2mQLz1yvSxYFfuIFC0MD7bAv6DBYPJiouEfC");
    }


}
