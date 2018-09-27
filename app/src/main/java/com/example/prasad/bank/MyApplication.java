package com.example.prasad.bank;

import android.app.Application;

public class MyApplication extends Application {
    public Long getMobileno() {
        return mobileno;
    }

    public void setMobileno(Long mobileno) {
        this.mobileno = mobileno;
    }

    Long mobileno;
}
