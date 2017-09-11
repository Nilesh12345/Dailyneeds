package com.gondia.dailyneeds;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.gondia.dailyneeds.Login.Login;
import com.gondia.dailyneeds.LoginSharedPreferences.UserSharedPreference;

public class Splash extends AppCompatActivity {
    UserSharedPreference preference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        /*try {
            PackageInfo info = getPackageManager().getPackageInfo(
                    "com.gondia.dailyneeds",
                    PackageManager.GET_SIGNATURES);
            for (Signature signature : info.signatures) {
                MessageDigest md = MessageDigest.getInstance("SHA");
                md.update(signature.toByteArray());
                Log.d("KeyHash:", Base64.encodeToString(md.digest(), Base64.DEFAULT));
            }
        } catch (PackageManager.NameNotFoundException e) {
            Log.d("error oin:","heheheh");
        } catch (NoSuchAlgorithmException e) {
            Log.d("error oin:","heheheh");
        }*/

        preference=new UserSharedPreference(getApplicationContext());

        Thread thread=new Thread() {
            public void run() {

                try {
                    sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    if(preference.isUserLoggedIn()) {
                        Intent i = new Intent(getApplicationContext(), MainActivity.class);
                        startActivity(i);
                    }
                    else{
                        Intent i = new Intent(getApplicationContext(), Login.class);
                        startActivity(i);
                    }
                }

            }
        };
        thread.start();
    }
}