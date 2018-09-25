package com.example.prasad.bank;

import android.app.Application;

public class MyApplication extends Application {
    String Email;

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
