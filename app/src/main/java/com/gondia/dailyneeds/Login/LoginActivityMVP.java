package com.gondia.dailyneeds.Login;

/**
 * Created by Nilesh1 on 11-09-2017.
 */

public interface LoginActivityMVP {
    interface View{
        String getFirstName();
        String getLastName();

        void showUserNotAvailable();
        void showInputError();
        void showUserSavedMessage();

        void setFirstName(String fname);
        void setLastName(String lname);


    }

    interface  Presenter{
        void setView(LoginActivityMVP.View view);

        void loginButtonedClicked();

        void getCurrentUser();

    }

    interface Model{
        void createUser(String fname,String lname);

        User getUser();
    }
}
