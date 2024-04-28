package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class login extends AppCompatActivity {
    TextView signup;
    Button login;
    EditText
            et_email,
            et_password;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        init();

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                verify();
            }
        });
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(login.this, MainActivity.class);
                startActivity(i);
                onDestroy();
            }
        });
    }
    void init(){
        et_email=findViewById(R.id.et_email_login);
        et_password=findViewById(R.id.et_pas_login);

        signup=findViewById(R.id.tv_register_login);
        login=findViewById(R.id.btn_login_login);

    }
    void verify()
    {
        String email = et_email.getText().toString().trim();
        String pass = et_password.getText().toString().trim();

        MyDatabaseHelper myDatabaseHelper = new MyDatabaseHelper(login.this);
        myDatabaseHelper.open();
        if(myDatabaseHelper.authenticate(email, pass))
        {
            myDatabaseHelper.close();
            Toast.makeText(login.this, "Login Successful", Toast.LENGTH_SHORT).show();

        }
        else {
            myDatabaseHelper.close();
            Toast.makeText(login.this, "No user exists", Toast.LENGTH_SHORT).show();
        }


    }
}