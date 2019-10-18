package com.example.prasad.bank;

import android.app.ProgressDialog;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.prasad.bank.Data.Account;
import com.example.prasad.bank.Transaction.PayTransaction;
import com.example.prasad.bank.Transaction.Payee;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Setprice extends AppCompatActivity {
    FirebaseAuth mauth;
    FirebaseUser user;
    DatabaseReference myref;
    EditText priceedit;
    TextView balancetext;
    Button addbutton;
    String uid;
    Double balancestate;
    int balance;
    boolean check = false;
    PayTransaction payTransaction = new PayTransaction();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setprice);
        priceedit = (EditText) findViewById(R.id.price_id);
        addbutton = (Button) findViewById(R.id.addmoney);
        balancetext = (TextView) findViewById(R.id.balance_id);

        user = FirebaseAuth.getInstance().getCurrentUser();
        uid = user.getUid();
        myref = FirebaseDatabase.getInstance().getReference("Users");
        myref.child(uid).child("Account").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Account account = dataSnapshot.getValue(Account.class);
                Double balance = account.getBalance();
                balancestate = balance;
                balancetext.setText(String.valueOf(balance));
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        addbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                {
                    addbalance();

                }
            }
        });
    }
    void addbalance() {
        ProgressDialog progressDialog = new ProgressDialog(Setprice.this);
        progressDialog.setMessage("Adding  balance");
        progressDialog.show();
        //  String email = user.getEmail();
        String amountstring = priceedit.getText().toString();
        Double amount1 = Double.parseDouble(amountstring);
        Double amount = balancestate + amount1;
        if (amount > 10000) {
            priceedit.setError("Enter Amount less than 10000");
        } else {
            Account account = new Account(amount);
            myref.child(uid).child("Account").setValue(account);
            payTransaction.addbalance(amount1, amount);
            progressDialog.dismiss();
        }
    }

}
