package com.example.prasad.bank.Transaction;

import android.support.annotation.NonNull;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Payee extends Thread{
    String uid;
    DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("Users");
    Double accountbalance;
    FirebaseUser firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
    Double balance;



    public  Double payeebalance() {

        String uid = firebaseUser.getUid();
        databaseReference.child(uid).child("Account").child("balance").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

        Double           balance = dataSnapshot.getValue(Double.class);
              setbalnce(balance);


            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
return balance;

    }

    public void setbalnce(Double balance)
    {
        this.balance = balance;
    }

   public Double getbalance ()
    {
      payeebalance();
          Double balance1 =balance;
        return balance1;

    }
}
