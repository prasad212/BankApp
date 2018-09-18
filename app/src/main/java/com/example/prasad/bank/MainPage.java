package com.example.prasad.bank;

import android.arch.persistence.room.Room;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.prasad.bank.Data.AppDatabase;
import com.example.prasad.bank.Data.User;

public class MainPage extends AppCompatActivity {
    AppDatabase db;
    TextView nametextView;

    long mobno;
    MyApplication application;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_page);
        application = (MyApplication) getApplication();
        mobno = application.getMobileno();


        db = Room.databaseBuilder(getApplicationContext(), AppDatabase.class, "bankDB").allowMainThreadQueries().build();

        nametextView = findViewById(R.id.nameTextView);

        String name = db.userDao().returnname(mobno);

        nametextView.setText("Hello " + name + mobno);
    }

    void userinfo(View view) {
        Intent intent = new Intent(this, Userinfo.class);

        startActivity(intent);

    }

    void logout(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    void webview(View view) {
        Intent intent = new Intent(this, WebActivity.class);
        startActivity(intent);

    }
}
