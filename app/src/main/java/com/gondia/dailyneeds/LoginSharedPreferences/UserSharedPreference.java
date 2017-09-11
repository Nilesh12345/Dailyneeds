package com.gondia.dailyneeds.LoginSharedPreferences;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

import com.gondia.dailyneeds.Login.Login;

/**
 * Created by Nilesh1 on 08-09-2017.
 */

public class UserSharedPreference {

    static SharedPreferences sharedPreferences;
    static SharedPreferences.Editor editor;
    Context context;

    int PRIVATE_MODE=0;

    private static final String PREFER_NAME="UserPref";
    private static final String IS_USER_LOGIN="IsUserLoggedIn";
    public static final String KEY_NAME="name";
    public static final String KEY_EMAIL="email";

    public UserSharedPreference(Context context){
        this.context=context;
        sharedPreferences=context.getSharedPreferences(PREFER_NAME,PRIVATE_MODE);
        editor=sharedPreferences.edit();
    }

    public  static  void createUserLoginSession(String name,String email){
        editor.putBoolean(IS_USER_LOGIN,true);
        editor.putString(KEY_NAME,name);
        editor.putString(KEY_EMAIL,email);
        editor.commit();
    }

    public static boolean isUserLoggedIn(){
        return sharedPreferences.getBoolean(IS_USER_LOGIN,false);
    }
    public static String getUsername(){
        return sharedPreferences.getString(KEY_NAME,null);
    }
    public static String getEmail(){
        return sharedPreferences.getString(KEY_EMAIL,null);
    }


    public static void logutUser(Context context){
        editor.clear();
        editor.commit();

        Intent i=new Intent(context, Login.class);
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(i);
    }
}
