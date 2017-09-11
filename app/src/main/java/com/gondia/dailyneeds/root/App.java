package com.gondia.dailyneeds.root;

import android.app.Application;

import com.gondia.dailyneeds.Login.LoginModule;

/**
 * Created by Nilesh1 on 11-09-2017.
 */

public class App extends Application {
    private ApplicationComponent applicationComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        applicationComponent = DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule(this))
                .loginModule(new LoginModule())
                .build();
    }

    public ApplicationComponent getApplicationComponent() {
        return applicationComponent;
    }
}
