package com.gondia.dailyneeds.NavigationHandler;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.login.LoginManager;
import com.gondia.dailyneeds.Login;
import com.gondia.dailyneeds.LoginSharedPreferences.UserSharedPreference;
import com.gondia.dailyneeds.R;

/**
 * Created by Nilesh1 on 08-09-2017.
 */

public class NavigationHandler {
    public static void processNavigationDrawer(View headerView, Context c) {
        setLoginHeaderData(headerView,c);

    }
    public static boolean  navigation(Context context,View d,View headerView) {
        Intent i;

        switch (d.getId()) {
            case R.id.navLogout:
                logout(context, d);
                break;
            case R.id.navLogin:
                context.startActivity(new Intent(context,Login.class));
                break;
            case R.id.navSignUp:
                context.startActivity(new Intent(context,Login.class));
                break;
        }
        return true;

    }
    private static void logout(final Context context, final View headerView) {
        AlertDialog.Builder alertBuilder = new AlertDialog.Builder(context);
        alertBuilder.setMessage("Are you sure you want to logout");
        alertBuilder.setPositiveButton("Yes", new Dialog.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                UserSharedPreference.logutUser(context);
                Toast.makeText(context, "Logged Out Successfully..", Toast.LENGTH_LONG).show();
                setLoginHeaderData(headerView,context);
            }
        });
        alertBuilder.setNegativeButton("Cancel", null);
        Toast.makeText(context,"inside alert",Toast.LENGTH_LONG).show();

        AlertDialog alertDialog = alertBuilder.create();
        alertDialog.setTitle("Confirm ?");
        alertDialog.show();
        if(Login.flagFB==true) {
            LoginManager.getInstance().logOut();
            Login.flagFB=false;
        }
    }

    public static void setLoginHeaderData(View headerView, Context c){
        if(UserSharedPreference.isUserLoggedIn()){
            String username=UserSharedPreference.getUsername();
            String email=UserSharedPreference.getEmail();
            TextView UserName=(TextView)headerView.findViewById(R.id.navusername);
            TextView Email=(TextView) headerView.findViewById(R.id.navmailid);
            UserName.setText(username);
            UserName.setVisibility(View.VISIBLE);
            Email.setVisibility(View.VISIBLE);
            Email.setText(email);
            Button logout= (Button) headerView.findViewById(R.id.navLogout);
            logout.setVisibility(View.VISIBLE);
        }
        else{
            Button login= (Button) headerView.findViewById(R.id.navLogin);
            login.setVisibility(View.VISIBLE);
            //login.setOnClickListener(this);
            Button signup= (Button) headerView.findViewById(R.id.navSignUp);
            signup.setVisibility(View.VISIBLE);

        }
    }
}
