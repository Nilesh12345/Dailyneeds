package com.gondia.dailyneeds.LoginSharedPreferences;

import android.os.Bundle;
import android.util.Log;

import org.json.JSONObject;

import java.net.MalformedURLException;
import java.net.URL;

import static android.content.ContentValues.TAG;

/**
 * Created by Nilesh1 on 08-09-2017.
 */

public class FbLogin {
    public  static Bundle getFacebookData(JSONObject object) {
        Bundle bundle = new Bundle();

        try {
            String id = object.getString("id");
            URL profile_pic;
            try {
                profile_pic = new URL("https://graph.facebook.com/" + id + "/picture?type=large");
                Log.i("profile_pic", profile_pic + "");
                bundle.putString("profile_pic", profile_pic.toString());
            } catch (MalformedURLException e) {
                e.printStackTrace();
                return null;
            }
            String name=null;
            String email=null;
            String gender=null;
            bundle.putString("idFacebook", id);
            if (object.has("first_name")) {
                bundle.putString("first_name", object.getString("first_name"));
                name=object.getString("first_name");

            }
            if (object.has("last_name")) {
                bundle.putString("last_name", object.getString("last_name"));
                name=name+" "+object.getString("last_name");
            }
            if (object.has("email")) {
                bundle.putString("email", object.getString("email"));
                email = object.getString("email");
            }
            if (object.has("gender")) {
                bundle.putString("gender", object.getString("gender"));
                gender=object.getString("gender");
            }

            UserSharedPreference.createUserLoginSession(name,email);


        } catch (Exception e) {
            Log.d(TAG, "BUNDLE Exception : "+e.toString());
        }

        return bundle;
    }
}
