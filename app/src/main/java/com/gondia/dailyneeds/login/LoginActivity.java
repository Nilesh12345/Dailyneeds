package com.gondia.dailyneeds.login;

import android.support.v7.app.AppCompatActivity;

import com.gondia.dailyneeds.R;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import javax.inject.Inject;
import com.gondia.dailyneeds.root.App;
/**
 * Created by ASHISH on 10-09-2017.
 */

public class LoginActivity extends AppCompatActivity implements LoginActivityMVP.View {
    @Inject
    LoginActivityMVP.Presenter presenter;

    private EditText firstName;
    private EditText lastName;
    private Button login;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        ((App) getApplication()).getComponent().inject(this);

        firstName = (EditText) findViewById(R.id.loginActivity_firstName_editText);
        lastName = (EditText) findViewById(R.id.loginActivity_lastName_editText);
        login = (Button) findViewById(R.id.loginActivity_login_button);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();
        presenter.setView(this);
    }

    @Override
    public String getFirstName() {
        return firstName.getText().toString();
    }

    @Override
    public String getLastName() {
        return lastName.getText().toString();
    }

    @Override
    public void showUserNotAvailable() {
        Toast.makeText(this, "User is not Available", Toast.LENGTH_SHORT).show();

    }

    @Override
    public void showInputError() {
        Toast.makeText(this, "First Name or Last Name cannot be empty", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showUserSavedMessage() {
        Toast.makeText(this, "User is saved successfully", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void setFirstName(String fname) {
        firstName.setText(fname);
    }

    @Override
    public void setLastName(String lname) {
        lastName.setText(lname);
    }
}
