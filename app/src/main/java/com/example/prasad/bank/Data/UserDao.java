package com.example.prasad.bank.Data;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.example.prasad.bank.UserData;

import java.util.List;

@Dao
public interface UserDao {
    @Query("Select * From user ")
    List<User> getAll();

    @Query("Select name,mobileno From user Where mobileno Like :mobileno")
    UserData finduserinfo(Long mobileno);

    @Query("Select * From user Where mobileno Like :mobileno" + " AND password Like :password")
    boolean findbymobile(Long mobileno, String password);

    @Query("Select name From user Where mobileno Like :mobileno")
    String returnname(Long mobileno);


    @Insert
    void insertAll(User user);

    @Delete
    void deleteAll(User user);

}
