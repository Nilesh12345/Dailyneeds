package com.gondia.dailyneeds.Login;

/**
 * Created by Nilesh1 on 11-09-2017.
 */

public class LoginModel implements LoginActivityMVP.Model{

    public LoginModel(LoginRepository repository) {
        this.repository = repository;
    }

    private LoginRepository repository;

    @Override
    public void createUser(String fname, String lname) {
        repository.setUser(new User(fname,lname));
    }

    @Override
    public User getUser() {
        return repository.getUser();
    }
}
