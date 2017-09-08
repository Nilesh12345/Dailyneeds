package com.gondia.dailyneeds;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;
import com.gondia.dailyneeds.LoginSharedPreferences.UserSharedPreference;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;

import com.facebook.FacebookSdk;
import com.facebook.appevents.AppEventsLogger;

public class Login extends AppCompatActivity implements View.OnClickListener,GoogleApiClient.OnConnectionFailedListener {
    private Button login,register;
    private EditText username,password;
    UserSharedPreference session;
    private ImageButton glogin;

    private static final String TAG = "SignInActivity";
    private static final int RC_SIGN_IN = 9001;

    private LoginButton fb;



    private CallbackManager callbackManager;
    private GoogleApiClient mGoogleApiClient;
    private TextView mStatusTextView;
    private ProgressDialog mProgressDialog;
    private TextView skip;

    @Override
    protected void onCreate(Bundle savedInstanceState)  {
        super.onCreate(savedInstanceState);
        FacebookSdk.sdkInitialize(getApplicationContext());
        setContentView(R.layout.activity_login);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        session=new UserSharedPreference(getApplicationContext());

        login= (Button) findViewById(R.id.btlogin);
        username= (EditText) findViewById(R.id.etusername);
        password= (EditText) findViewById(R.id.etpassword);
        glogin= (ImageButton) findViewById(R.id.gplusloginbt);
        glogin.setOnClickListener(this);
        login.setOnClickListener(this);

        skip= (TextView) findViewById(R.id.skipToMain);
        skip.setOnClickListener(this);

        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();
        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .enableAutoManage(this , this/* OnConnectionFailedListener*/ )
                .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
                .build();
        callbackManager = CallbackManager.Factory.create();
        fb= (LoginButton) findViewById(R.id.fbbt);


        fb.setReadPermissions("email");
        fb.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                //getUserDetails(loginResult);
                System.out.println("onSuccess "+loginResult);
                loginResult.getRecentlyGrantedPermissions();

                Intent mainLobby = new Intent(Login.this, MainActivity.class);

                startActivity(mainLobby);

            }

            @Override
            public void onCancel() {
                // App code
            }

            @Override
            public void onError(FacebookException exception) {
                // App code
            }
        });
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btlogin:
                login();
                break;
            case R.id.gplusloginbt:
                signIn();
                break;
            case R.id.skipToMain:
                startActivity(new Intent(this,MainActivity.class));
                break;
        }
    }

    public void login(){
        String usernamee=username.getText().toString();
        String passworde=password.getText().toString();
        if(usernamee.length()>0 && passworde.length()>0){

            if(usernamee.equals("admin")&&passworde.equals("admin")){
                setSession(usernamee,passworde);
            }
        }
    }

    private void signIn() {
        Intent signInIntent = Auth.GoogleSignInApi.getSignInIntent(mGoogleApiClient);
        // Toast.makeText(this,"in side sign in method",Toast.LENGTH_SHORT);
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        callbackManager.onActivityResult(requestCode, resultCode, data);
        // Result returned from launching the Intent from GoogleSignInApi.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
            // Toast.makeText(this,"on activity result method",Toast.LENGTH_SHORT);
            handleSignInResult(result);
        }
    }

    private void handleSignInResult(GoogleSignInResult result) {
        Log.d(TAG, "handleSignInResult:" + result.isSuccess());
        if (result.isSuccess()) {
            // Signed in successfully, show authenticated UI.
            GoogleSignInAccount acct = result.getSignInAccount();

            setSession(acct.getDisplayName(),acct.getEmail());
            //mStatusTextView.setText(getString(R.string.signed_in_fmt, acct.getDisplayName()));
            // Toast.makeText(this,"Success",Toast.LENGTH_SHORT);

        } else {
            // Toast.makeText(this,"failed",Toast.LENGTH_SHORT);// Signed out, show unauthenticated UI.
            //updateUI(false);
        }
    }

    public void setSession(String name,String email){
        session.createUserLoginSession(name,email);
        Intent i=new Intent(this,MainActivity.class);
        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(i);
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }

}
