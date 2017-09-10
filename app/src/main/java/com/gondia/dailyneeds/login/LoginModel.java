package com.gondia.dailyneeds.login;

/**
 * Created by ASHISH on 10-09-2017.
 */

public class LoginModel implements LoginActivityMVP.Model {

    private LoginRepository repository;

    public LoginModel(LoginRepository repository) {
        this.repository = repository;
    }

    @Override
    public void createUser(String fname, String lname) {
        repository.saveUser(new User(fname,lname));
    }

    @Override
    public User getUser() {
        return null;
    }
}
