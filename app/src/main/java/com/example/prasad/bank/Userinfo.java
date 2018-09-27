package com.example.prasad.bank;

import android.arch.persistence.room.Room;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.example.prasad.bank.Data.AppDatabase;
import com.example.prasad.bank.Data.User;
import com.example.prasad.bank.Data.UserDao;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Userinfo extends AppCompatActivity {
    AppDatabase db;
    TextView nameText, mobilenoText, amounttext;
    User user = new User();
    String name, mobileno;
    MyApplication application;
    private DatabaseReference databaseReference;
    DatabaseReference userref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_userinfo);
        nameText = findViewById(R.id.nameuserid);
        mobilenoText = findViewById(R.id.mobilenouserinfo);
        amounttext = findViewById(R.id.amount_id);
        databaseReference = FirebaseDatabase.getInstance().getReference();
        //userref = databaseReference.child("users");
        application = (MyApplication) getApplication();
      Long mobileno= application.getMobileno();

       /* db = Room.databaseBuilder(getApplicationContext(), AppDatabase.class, "bankDB").allowMainThreadQueries().build();
        try {
            int balance = db.userDao().finduserinfo(mobno);
            String name = db.userDao().returnname(mobno);
            String balanaceString = String.valueOf(balance);
            nameText.setText(name);
            //mobilenoText.setText(mobileno);
            amounttext.setText(balanaceString);

        } catch (RuntimeException e) {
            int length = Toast.LENGTH_SHORT;
            String msg = "Error ";
            Toast toast = Toast.makeText(this, msg, length);
            toast.show();
        }
*/
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