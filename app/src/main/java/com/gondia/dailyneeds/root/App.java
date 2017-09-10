package com.gondia.dailyneeds.root;

import android.app.Application;

//import com.gondia.dailyneeds.DaggerApplicationComponent;
import com.gondia.dailyneeds.login.LoginModule;

/**
 * Created by ASHISH on 10-09-2017.
 */

public class App extends Application {
    private ApplicationComponent component;

    @Override
    public void onCreate(){
        super.onCreate();

        component = DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule(this))
                .loginModule(new LoginModule())
                .build();
    }

    public ApplicationComponent getComponent(){
        return component;
    }


}
