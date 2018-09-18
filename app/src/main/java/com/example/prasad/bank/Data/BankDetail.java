package com.example.prasad.bank.Data;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.PrimaryKey;

@Entity(foreignKeys = @ForeignKey(entity = User.class, parentColumns = "mobileno", childColumns = "Bmobileno"))
public class BankDetail {
    @PrimaryKey
    public static Long Bmobileno;
    @ColumnInfo(name = "value")
    public static int value;
}
