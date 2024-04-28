package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    Button signup;
    TextView login;
    EditText et_fname,
            et_lname,
            et_email,
            et_password,
            et_Rpassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
       init();
        signup.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if(addUser())
                {Intent i=new Intent(MainActivity.this, login.class);
                startActivity(i);}
            }
        });
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(MainActivity.this, login.class);
                startActivity(i);
                onDestroy();
            }
        });
    }
    void init(){
        et_fname=findViewById(R.id.et_firstname_reg);
        et_lname=findViewById(R.id.et_lastname_reg);
        et_email=findViewById(R.id.et_email_reg);
        et_password=findViewById(R.id.et_pas_reg);
        et_Rpassword=findViewById(R.id.et_rpas_reg);

        signup=findViewById(R.id.btn_SignUp_reg);
        login=findViewById(R.id.tv_LogIn_reg);


    }
    boolean addUser()
    {
        String fname = et_fname.getText().toString().trim();
        String lname = et_lname.getText().toString().trim();
        String email = et_email.getText().toString().trim();
        String pass = et_password.getText().toString().trim();
        String rpass = et_Rpassword.getText().toString().trim();
        if (fname.isEmpty() || lname.isEmpty() || email.isEmpty() || pass.isEmpty() || rpass.isEmpty()) {
            et_fname.setError("fill it");
            et_lname.setError("fill it");
            et_email.setError("fill it");
            et_password.setError("fill it");
            et_Rpassword.setError("fill it");
            Toast.makeText(MainActivity.this, "Please fill all fields", Toast.LENGTH_SHORT).show();
            return false;
        }
        if ((pass.equals(rpass))) { // Use equals() for content comparison
            MyDatabaseHelper myDatabaseHelper = new MyDatabaseHelper(MainActivity.this);
            myDatabaseHelper.open();
            myDatabaseHelper.insert(fname, lname, email, pass);
            myDatabaseHelper.close();
            Toast.makeText(MainActivity.this, "User Registred Successfully", Toast.LENGTH_SHORT).show();
            return true;
        } else {
            // Handle case where passwords don't match
            // For example, display an error message
            // Toast.makeText(MainActivity.this, "Passwords do not match", Toast.LENGTH_SHORT).show();
            Toast.makeText(MainActivity.this, "Passwords do not match", Toast.LENGTH_SHORT).show();
            return false;
        }

    }
}