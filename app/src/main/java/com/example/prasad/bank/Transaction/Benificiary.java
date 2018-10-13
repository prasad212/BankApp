package com.example.prasad.bank.Transaction;

import android.support.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Benificiary {
    DatabaseReference myref = FirebaseDatabase.getInstance().getReference("Users");
    String benificiaryuid;
    Double benficarybalance;
    private  void getBenificiary() {
        myref.child(benificiaryuid).child("Account").child("balance").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                benficarybalance = dataSnapshot.getValue(Double.class);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

      public   Double getBenificiaryBalance(String uid)
        {
            benificiaryuid = uid;
            getBenificiary();
            return benficarybalance;

        }
    }

