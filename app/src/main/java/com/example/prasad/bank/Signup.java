package com.example.prasad.bank;

import android.arch.persistence.room.Room;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteConstraintException;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.prasad.bank.Data.AppDatabase;
import com.example.prasad.bank.Data.User;
import com.example.prasad.bank.Data.UserDao;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import javax.xml.transform.Result;

public class Signup extends AppCompatActivity {
    public User user;
    public UserDao userDao;
    EditText nameEdit, mobilnoEdit, passwordEdit;
    AppDatabase db;
    private FirebaseAnalytics mFirebaseAnalytics;
    FirebaseDatabase database;
    private DatabaseReference mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        nameEdit = findViewById(R.id.name);
        mobilnoEdit = findViewById(R.id.mobileno);
        passwordEdit = findViewById(R.id.password);
        db = Room.databaseBuilder(getApplicationContext(), AppDatabase.class, "bankDB").allowMainThreadQueries().build();


    }

    void insert(View view) {

        try {

            User user = new User();
            String name = nameEdit.getText().toString();
            String mobno = mobilnoEdit.getText().toString();
            String password = passwordEdit.getText().toString();
            Long mobilno = Long.parseLong(mobno);
            user.setMobileno(mobilno);
            user.setName(name);
            user.setPassword(password);

            DatabaseReference mDatabase = FirebaseDatabase.getInstance().getReference("users");

            db.userDao().insertAll(user);
            mDatabase.push().setValue(user);
            int time = Toast.LENGTH_SHORT;
            String inserted = "Account Created";
            Context c = getApplicationContext();
            Toast t = Toast.makeText(c, inserted, time);
            t.show();
        } catch (SQLiteConstraintException e) {
            int time = Toast.LENGTH_SHORT;
            String inserted = "Mobile No Already Exist";
            Context c = getApplicationContext();
            Toast t = Toast.makeText(c, inserted, time);
            t.show();
        }
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }


}
