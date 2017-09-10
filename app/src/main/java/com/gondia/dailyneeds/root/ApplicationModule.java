package com.gondia.dailyneeds.root;

import android.app.Application;
import android.content.Context;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by ASHISH on 10-09-2017.
 */

@Module
public class ApplicationModule {

    private Application application;

    public ApplicationModule(Application application){
        this.application = application;
    }
    @Provides
    @Singleton
    public Context provideContext(){
        return application;
    }
}
