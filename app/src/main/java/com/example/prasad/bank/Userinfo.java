package com.example.prasad.bank;

import android.content.Intent;
import android.graphics.Camera;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.prasad.bank.Data.Account;
import com.example.prasad.bank.Data.UserData;
import com.example.prasad.bank.Transaction.Payee;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class Userinfo extends AppCompatActivity {
    TextView nametext,contactnotext,balancetext;
    FirebaseUser firebaseUser;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference myref;
    DatabaseReference myref2;
    Payee payee =new Payee();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_userinfo);
        nametext=(TextView)findViewById(R.id.name_id_profile);
        contactnotext=(TextView)findViewById(R.id.contactno_id);
        balancetext = (TextView)findViewById(R.id.balance_id);
        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        final String userid = firebaseUser.getUid();
    firebaseDatabase = FirebaseDatabase.getInstance();
    myref =firebaseDatabase.getReference("Users");


      myref.child(userid).addValueEventListener(new ValueEventListener() {
        @Override
        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
          //  UserData userData = dataSnapshot.getValue(UserData.class);
            UserData userData = dataSnapshot.child("Info").getValue(UserData.class);
            Account account = dataSnapshot.child("Account").getValue(Account.class);
            Double balance  = account.getBalance();


            String name =  userData.getName();
            String mobileno = userData.getMobileno();
            nametext.setText(name);
            contactnotext.setText(mobileno);

            balancetext.setText(String.valueOf(balance)+" ₹ ");

        }

        @Override
        public void onCancelled(@NonNull DatabaseError databaseError) {

        }
    });


    }

}
