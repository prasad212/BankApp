package com.example.prasad.bank.Data;

import java.util.Date;

public class UserData {
    UserData()
    {

    }



    public UserData(String name, String mobileno) {
        this.name = name;
        this.mobileno = mobileno;
    }

    String name;



    int balance;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMobileno() {
        return mobileno;
    }

    public void setMobileno(String mobileno) {
        this.mobileno = mobileno;
    }

    String mobileno;





}
