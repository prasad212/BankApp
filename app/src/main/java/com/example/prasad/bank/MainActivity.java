package com.example.prasad.bank;

import android.app.Activity;
import android.app.Application;
import android.arch.persistence.room.Room;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.TextView;
import android.widget.Toast;

import com.example.prasad.bank.Data.AppDatabase;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;

public class MainActivity extends AppCompatActivity {

    //database class global object
    AppDatabase db;




    String email, password;
    Long mobno = 0L;
    EditText emailtext, passwordtext;
    MyApplication application;
    TextView textView;
    FirebaseAuth auth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        emailtext = findViewById(R.id.login_id_email);
        passwordtext = findViewById(R.id.loginpassword);


        //database object initialisation in on create
        db = Room.databaseBuilder(getApplicationContext(), AppDatabase.class, "bankDB").allowMainThreadQueries().build();
        //

        textView = findViewById(R.id.signup_text);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, Signup.class);
                startActivity(i);
            }
        });
        auth = FirebaseAuth.getInstance();

    }


    void login(View view) {

        try {
            email = emailtext.getText().toString();
            password = passwordtext.getText().toString();
            boolean i = db.userDao().auth(email, password);

            if (i == true) {

                //   String mobileno = String.valueOf(mobno);
                int time = Toast.LENGTH_SHORT;
                String msg = "Exist in Local database";
                Toast t = Toast.makeText(this, msg, time);
                t.show();

            } else
                {
                int time = Toast.LENGTH_SHORT;
                String msg = "Invalid Login";
                Toast t = Toast.makeText(this, msg, time);
                t.show();
            }
              //firebase
            auth.signInWithEmailAndPassword(email, password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()) {
                        application = (MyApplication) getApplication();
                        application.setEmail(email);
                        Intent intent = new Intent(MainActivity.this, MainPage.class);
                        startActivity(intent);
                        finish();
                    } else {
                        int time = Toast.LENGTH_SHORT;
                        String msg = "invalid in Firebase";
                        Toast t = Toast.makeText(MainActivity.this, msg, time);
                        t.show();
                    }
                }
            });
        } catch (IllegalArgumentException e) {
            int time = Toast.LENGTH_SHORT;
            String msg = "Please Enter Email and password";
            Toast t = Toast.makeText(MainActivity.this, msg, time);
            t.show();
        }
    }


}
