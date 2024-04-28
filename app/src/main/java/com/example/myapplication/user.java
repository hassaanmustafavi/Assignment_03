package com.example.myapplication;

import android.provider.ContactsContract;

public class user {
    String fname, lname,email,pass,rpass;
    public user() {
    }
    public user(String fname, String lname, String email, String pass, String rpass, Boolean gender) {
        this.fname = fname;
        this.lname = lname;
        this.email = email;
        this.pass = pass;
        this.rpass = rpass;

    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public String getLname() {
        return lname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getRpass() {
        return rpass;
    }

    public void setRpass(String rpass) {
        this.rpass = rpass;
    }


}
