package com.example.prasad.bank;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.prasad.bank.Data.Account;
import com.example.prasad.bank.Data.User;
import com.example.prasad.bank.Data.UserData;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Date;

public class Getuserinfo extends AppCompatActivity {
    EditText nametext, mobilenotext;


    FirebaseUser firebaseUser;
    FirebaseDatabase database;
    DatabaseReference myref;
    FirebaseAuth mauth;
    String userid;
    TextView textView;
    Signup signup;
    String name, mobileno;
    String uid;
    Button setprofileButton;
    MyApplication myApplication;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_getuserinfo);
        nametext = (EditText) findViewById(R.id.name_id);
        mobilenotext = (EditText) findViewById(R.id.mobileno_id);
        textView = (TextView) findViewById(R.id.uid_id);
        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        uid = firebaseUser.getUid();
        textView.setText(uid);

        database = FirebaseDatabase.getInstance();
        myref = database.getReference();
        setprofileButton = (Button) findViewById(R.id.setprofile_id);
        setprofileButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setProfile(v);
            }
        });


    }

    void setProfile(View view) {

        UserData userData;


        Account account = new Account(1.0);


        if (getdata()) {
            userData = new UserData();
            userData.setMobileno(mobileno);
            userData.setName(name);

            myref.child("Users").child(uid).child("Info").setValue(userData);
            myref.child("Accounts").child(mobileno).setValue(uid);
            myref.child("Users").child(uid).child("Account").setValue(account).addOnSuccessListener(new OnSuccessListener<Void>() {
                @Override
                public void onSuccess(Void aVoid) {
                    Intent intent = new Intent(Getuserinfo.this, MainActivity.class);
                    startActivity(intent);
                }
            });
            int length = Toast.LENGTH_SHORT;
            Toast toast = Toast.makeText(this, "added to user", length);
            toast.show();
        }

        }
        void login (View view){
            Intent intent = new Intent(this, MainPage.class);
        }

        boolean getdata ()
        {
            boolean ch = false;

            name = nametext.getText().toString();
            mobileno = mobilenotext.getText().toString();
            if (name.isEmpty()) {
                nametext.setError("Enter name First");
                ch = false;
            } else {

                ch = true;
            }
            if (mobileno.isEmpty()) {
                mobilenotext.setError("Enter Mobileno");
                ch = true;
            } else {

                ch = true;
            }
            return ch;
        }
    }

