package com.gondia.dailyneeds.root;

import com.gondia.dailyneeds.Login.Login;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by Nilesh1 on 11-09-2017.
 */
@Singleton
@Component(modules = {ApplicationModule.class})
public interface ApplicationComponent {
    void inject(Login target);

}
