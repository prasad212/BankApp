package com.example.prasad.bank.Data;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.PrimaryKey;

import javax.annotation.Nonnull;

@Entity(tableName = "user")
public class User {
    @PrimaryKey
    private long mobileno;
    @ColumnInfo(name = "name")
    private String name;
    @ColumnInfo(name = "password")
    private String password;

    public long getMobileno() {
        return mobileno;
    }

    public void setMobileno(long mobileno) {
        this.mobileno = mobileno;

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
