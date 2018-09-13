package com.example.prasad.bank;

import android.app.Activity;
import android.app.Application;
import android.arch.persistence.room.Room;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.Toast;

import com.example.prasad.bank.Data.AppDatabase;

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;

public class MainActivity extends AppCompatActivity {

    AppDatabase db;
    String no = "12A@sda", pas = "sadas";
    Long mobno = 0L;
    EditText mobileno, password;
    MyApplication application;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mobileno = findViewById(R.id.loginmobile);
        password = findViewById(R.id.loginpassword);
        db = Room.databaseBuilder(getApplicationContext(), AppDatabase.class, "bankDB").allowMainThreadQueries().build();
    }


    void login(View view) {

        try {
            no = mobileno.getText().toString();
            mobno = Long.parseLong(no);
            pas = password.getText().toString();
            boolean empty = pas.isEmpty();
            String value = mobno.toString();

        } catch (NumberFormatException d) {
            int time = Toast.LENGTH_SHORT;
            String msg = "Please Enter Valid Credential";
            Toast t = Toast.makeText(this, msg, time);
            t.show();
        }
        boolean i = db.userDao().findbymobile(mobno, pas);
        if (i == true) {

            String mobileno = String.valueOf(mobno);


            application = (MyApplication) getApplication();
            application.setMobileno(mobno);

            Intent intent = new Intent(this, MainPage.class);
            startActivity(intent);
            finish();
        } else {
            int time = Toast.LENGTH_SHORT;
            String msg = "Invalid Login";
            Toast t = Toast.makeText(this, msg, time);
            t.show();
        }
    }


    void signup(View view) {
        Intent i = new Intent(this, Signup.class);
        startActivity(i);

    }


}
