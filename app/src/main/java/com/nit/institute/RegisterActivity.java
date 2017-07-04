package com.nit.institute;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.nit.institute.SQLite.DatabaseHandler;

/**
 * Created by Lenovo on 10-06-2017.
 */

public class RegisterActivity extends Activity {

    EditText eName;
    EditText eUsername;
    EditText ePassword;
    EditText eEmail;
    Button bReg;
    DatabaseHandler db;

    String na,us,pa;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        eName=(EditText)findViewById(R.id.eName);
        eUsername=(EditText)findViewById(R.id.eUsername);
        ePassword=(EditText)findViewById(R.id.ePassword);
        // eEmail=(EditText)findViewById(R.id.eEmail);
        db=new DatabaseHandler(this);
        bReg=(Button)findViewById(R.id.bReg);



        bReg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                na=eName.getText().toString();
                us=eUsername.getText().toString();
                pa=ePassword.getText().toString();

                db.addUser(eName.getText().toString(),eUsername.getText().toString(),ePassword.getText().toString());

                Intent i=new Intent(RegisterActivity.this,LoginActivity.class);
                //i.putExtra("n", na);
                //i.putExtra("u", us);
                //i.putExtra("p", pa);
                //i.putExtra("e",em);
                startActivity(i);
            }
        });

    }
}