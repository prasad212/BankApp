package com.example.prasad.bank.Data;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

@Dao
public interface UserDao {
    @Query("Select * From user ")
    List<User> getAll();

    @Query("Select value From user Where mobileno Like :mobileno")
    int finduserinfo(Long mobileno);

    @Query("Select * From user Where mobileno Like :mobileno" + " AND password Like :password")
    boolean findbymobile(Long mobileno, String password);

    @Query("Select name From user Where mobileno Like :mobileno")
    String returnname(Long mobileno);

    @Query("UPDATE user SET value = :value WHERE mobileno Like :mobileno ")
    int balanceupdate(Long mobileno, int value);

    @Update
    void setvalue(User user);
    @Insert
    void insertAll(User user);

    @Delete
    void deleteAll(User user);

}
