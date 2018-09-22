package com.example.prasad.bank;

import android.arch.persistence.room.Room;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.prasad.bank.Data.AppDatabase;
import com.example.prasad.bank.Data.User;

public class MainPage extends AppCompatActivity {
    AppDatabase db;
    TextView nametextView;
    EditText amounttext;
    User user = new User();
    long mobno;
    MyApplication application;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_page);
        application = (MyApplication) getApplication();
        // mobno = application.getMobileno();
        amounttext = findViewById(R.id.ammount);

        db = Room.databaseBuilder(getApplicationContext(), AppDatabase.class, "bankDB").allowMainThreadQueries().build();

        nametextView = findViewById(R.id.nameTextView);

        String name = db.userDao().returnname(mobno);

        nametextView.setText("Hello " + name);
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

    void setamount(View view) {
        String amount = amounttext.getText().toString();
        int amountint = Integer.parseInt(amount);

        int i = db.userDao().balanceupdate(mobno, amountint);
        if (i == 1) {
            int length = Toast.LENGTH_SHORT;
            String msg = "Balance Updated";
            Toast toast = Toast.makeText(this, msg, length);
            toast.show();
        } else {
            int length = Toast.LENGTH_SHORT;
            String msg = "Error ";
            Toast toast = Toast.makeText(this, msg, length);
            toast.show();
        }
    }
}
