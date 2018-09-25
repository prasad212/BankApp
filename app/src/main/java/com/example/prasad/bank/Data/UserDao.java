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

    @Query("Select value From user Where Email Like :Email")
    int finduserinfo(String Email);

    @Query("Select * From user Where Email Like :Email And password Like :password" )
    boolean auth(String Email, String password);

    @Query("Select name From user Where Email Like :Email")
    String returnname(String Email);

    @Query("UPDATE user SET value = :value WHERE Email Like :Email ")
    int balanceupdate(String Email, int value);

    @Query("Select  * From user Where Email Like :Email")
    User returnuser(String Email);


    @Update
    void setvalue(User user);
    @Insert
    void insertAll(User user);

    @Delete
   int deleteAll(User user);

}
