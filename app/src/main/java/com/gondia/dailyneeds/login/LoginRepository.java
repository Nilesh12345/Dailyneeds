package com.gondia.dailyneeds.login;

/**
 * Created by ASHISH on 10-09-2017.
 */

public interface LoginRepository {

    User getUser();
    void saveUser(User user);
}
