package com.example.prasad.bank;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import com.example.prasad.bank.Transaction.TransactionDetail;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class Transactions extends AppCompatActivity {
    ListView listView;
    FirebaseUser firebaseUser;
    DatabaseReference myref;
    ArrayList<TransactionDetail> details = new ArrayList<TransactionDetail>();
    TransactionDetail transactionDetail = new TransactionDetail();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transaction);
        final Myadapter myadapter = new Myadapter(this, details);
        listView = (android.widget.ListView) findViewById(R.id.listview_id);
        listView.setAdapter(myadapter);


        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        String uid = firebaseUser.getUid();
        myref = FirebaseDatabase.getInstance().getReference("Users");
        myref.child(uid).child("Transaction").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
               Set<String> set = new HashSet<>();
               Iterator iterator = dataSnapshot.getChildren().iterator();

                while (iterator.hasNext()) {

                    set.add(((DataSnapshot)iterator.next()).getKey());
                }
                    details.clear();
              //  details.addAll(set);
                    myadapter.notifyDataSetChanged();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


    }
}
