package com.example.prasad.bank;

import android.app.Application;

public class MyApplication extends Application {
    Long mobileno;

    public void setMobileno(Long mobileno) {
        this.mobileno = mobileno;
    }

    public Long getMobileno() {
        return mobileno;
    }
}
