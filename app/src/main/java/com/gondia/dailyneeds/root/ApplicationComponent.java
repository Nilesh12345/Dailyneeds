package com.gondia.dailyneeds.root;

import com.gondia.dailyneeds.login.LoginActivity;
import com.gondia.dailyneeds.login.LoginModule;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by ASHISH on 10-09-2017.
 */


@Singleton
@Component(modules = {ApplicationModule.class, LoginModule.class})
public interface ApplicationComponent {
    void inject (LoginActivity target);
}