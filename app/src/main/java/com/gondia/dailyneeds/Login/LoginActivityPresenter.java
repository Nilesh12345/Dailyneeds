package com.gondia.dailyneeds.Login;

import android.support.annotation.Nullable;

/**
 * Created by Nilesh1 on 11-09-2017.
 */

public class LoginActivityPresenter implements LoginActivityMVP.Presenter {
    @Nullable
    private LoginActivityMVP.View view;
    private LoginActivityMVP.Model model;

    public LoginActivityPresenter(LoginActivityMVP.Model model) {
        this.model = model;
    }

    @Override
    public void setView(LoginActivityMVP.View view) {
        this.view=view;
    }

    @Override
    public void loginButtonedClicked() {
        if(view !=null){
            if(view.getFirstName().trim().equals("") || view.getLastName().trim().equals("")){
                view.showInputError();
            }
            else {
                model.createUser(view.getFirstName(),view.getLastName());
                view.userSuccess();

            }
        }

    }

    @Override
    public void getCurrentUser() {
        User user=model.getUser();
        if(user==null){
            if (view!=null){
                view.showUserNotAvailable();
            }
        }
        else {
            if (view!=null){
                view.setFirstName(user.getFirstName());
                view.setLastName(user.getLastName());
            }
        }
    }
}
