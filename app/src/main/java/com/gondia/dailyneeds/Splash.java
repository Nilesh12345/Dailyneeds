package com.gondia.dailyneeds;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.gondia.dailyneeds.LoginSharedPreferences.UserSharedPreference;

public class Splash extends AppCompatActivity {
    UserSharedPreference preference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

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
