package com.example.prasad.bank;

import android.arch.persistence.room.Room;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.example.prasad.bank.Data.AppDatabase;

public class Userinfo extends AppCompatActivity {
    AppDatabase db;
    TextView nameText, mobilenoText;
    UserData userData;
    String name, mobileno;
    MyApplication application;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_userinfo);
        nameText = findViewById(R.id.nameuserid);
        mobilenoText = findViewById(R.id.mobilenouserinfo);


        application = (MyApplication) getApplication();
        Long mobno = application.getMobileno();

        db = Room.databaseBuilder(getApplicationContext(), AppDatabase.class, "bankDB").allowMainThreadQueries().build();

        userData = db.userDao().finduserinfo(mobno);
        String mobileno = String.valueOf(mobno);
        nameText.setText(userData.name);
        mobilenoText.setText(mobileno);
    }
}



    /*  if(i == true)
    {
        nameText.setText("helloo");
    }else {
        nameText.setText("nothing to show ");
    }
    }

}
*/