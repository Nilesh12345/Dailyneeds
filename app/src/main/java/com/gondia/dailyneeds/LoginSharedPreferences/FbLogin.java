package com.gondia.dailyneeds.LoginSharedPreferences;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.gondia.dailyneeds.Login.Login;
import com.gondia.dailyneeds.MainActivity;

import org.json.JSONObject;

import java.net.MalformedURLException;
import java.net.URL;

import static android.content.ContentValues.TAG;

/**
 * Created by Nilesh1 on 08-09-2017.
 */

public class FbLogin {
    public  static Bundle getFacebookData(JSONObject object, Context c) {
        Login login=new Login();
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
            //login.setSession(name,email);
            UserSharedPreference.createUserLoginSession(name,email);
            //Login.flagFB=true;
            Intent i=new Intent(c, MainActivity.class);
            i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            c.startActivity(i);

        } catch (Exception e) {
            Log.d(TAG, "BUNDLE Exception : "+e.toString());
        }

        return bundle;
    }
}
