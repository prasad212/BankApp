package com.example.prasad.bank.Data;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.PrimaryKey;

import org.checkerframework.checker.nullness.compatqual.NonNullType;

import javax.annotation.Nonnull;

import io.reactivex.annotations.NonNull;

@Entity(tableName = "user")
public class User {

    @android.support.annotation.NonNull
    public Long getMobileno() {
        return mobileno;
    }

    public void setMobileno(@android.support.annotation.NonNull Long mobileno) {
        this.mobileno = mobileno;
    }

    @PrimaryKey
@android.support.annotation.NonNull
    private Long mobileno;
    @ColumnInfo(name = "name")
    private String name;
    @ColumnInfo(name = "password")
    private String password;


    @ColumnInfo(name = "value")
    private int value;


    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
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
