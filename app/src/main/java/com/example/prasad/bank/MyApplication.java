package com.example.prasad.bank;

import android.app.Application;

public class MyApplication extends Application {
    String Email;

    public MyApplication(String userid) {
        this.userid = userid;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    String userid;
    String password;

    public void setEmail(String Email) {
        this.Email = Email;
    }

    public String getEmail() {
        return Email;
    }
    public  MyApplication()
    {}

}
