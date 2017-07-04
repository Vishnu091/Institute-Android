package com.nit.institute;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.nit.institute.SQLite.DatabaseHandler;

public class LoginActivity extends Activity {

    // Email, password edittext
    EditText txtUsername, txtPassword;

    // login button
    Button btnLogin,bNew;

    // Alert Dialog Manager
    AlertDialogManager alert = new AlertDialogManager();

    DatabaseHandler db;
    // Session Manager Class
    SessionManager session;
    boolean flag=false;
    String na=null,us=null,pa=null,em=null,u,p;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        ActivityCompat.requestPermissions(LoginActivity.this,
                new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                1);


        // Session Manager
        session = new SessionManager(getApplicationContext());

        // Email, Password input text
        txtUsername = (EditText) findViewById(R.id.txtUsername);
        txtPassword = (EditText) findViewById(R.id.txtPassword);

        Toast.makeText(getApplicationContext(), "User Login Status: " + session.isLoggedIn(), Toast.LENGTH_LONG).show();


        // Login button
        btnLogin = (Button) findViewById(R.id.btnLogin);
        bNew=(Button)findViewById(R.id.bNew);

        db=new DatabaseHandler(this);
        //na=getIntent().getStringExtra("n");
        //us=getIntent().getStringExtra("u");
        //pa=getIntent().getStringExtra("p");

        //em=getIntent().getStringExtra("e");
        //Log.d("TAG",na +" "+ us+" "+pa);


        bNew.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent v=new Intent(LoginActivity.this,RegisterActivity.class);
                startActivity(v);
            }
        });


        // Login button click event
        btnLogin.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                // Get username, password from EditText
                String username = txtUsername.getText().toString();
                String password = txtPassword.getText().toString();
                flag=false;
                flag=db.checkUser(username,password);
                if(flag==true) {
                    na=db.getname(username,password);
                    if (na == null ) {
                        Toast.makeText(getApplicationContext(), "EMPTY", Toast.LENGTH_SHORT).show();
                    }
                    //Toast.makeText(getApplicationContext(), "EMPTY", Toast.LENGTH_SHORT).show();
                    // Check if username, password is filled
                    if (username.trim().length() > 0 && password.trim().length() > 0) {
                        // For testing puspose username, password is checked with sample data
                        // username = test
                        // password = test


                        // Creating user login session
                        // For testing i am stroing name, email as follow
                        // Use user real data
                        session.createLoginSession(na);

                        // Staring MainActivity
                        Intent i = new Intent(getApplicationContext(), MainActivity.class);
                        startActivity(i);
                        finish();


                    } else {
                        // user didn't entered username or password
                        // Show alert asking him to enter the details
                        alert.showAlertDialog(LoginActivity.this, "Login failed..", "Please enter username and password", false);
                    }
                }
                else
                    alert.showAlertDialog(LoginActivity.this, "Login failed..", "username and password don't exist", false);


            }
        });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        switch (requestCode) {
            case 1: {

                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                    // permission was granted, yay! Do the
                    // contacts-related task you need to do.
                } else {

                    // permission denied, boo! Disable the
                    // functionality that depends on this permission.
                    Toast.makeText(LoginActivity.this, "Permission denied to read your External storage", Toast.LENGTH_SHORT).show();
                }
                return;
            }

            // other 'case' lines to check for other
            // permissions this app might request
        }
    }

}
