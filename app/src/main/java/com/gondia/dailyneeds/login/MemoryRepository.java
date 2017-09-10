package com.gondia.dailyneeds.login;

/**
 * Created by ASHISH on 10-09-2017.
 */

public class MemoryRepository implements LoginRepository {

    private User user;


    @Override
    public User getUser(){
        if (user == null) {
            User user = new User("Amish", "Kundnani");
            user.setId(0);
            return user;
        }
        else{
            return user;
        }

    }

    @Override
    public void saveUser(User user) {
        if(user == null){
            user = getUser();
        }
        this.user = user;
    }
}
