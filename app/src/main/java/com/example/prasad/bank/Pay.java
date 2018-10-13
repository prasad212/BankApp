package com.example.prasad.bank;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.prasad.bank.Transaction.PayTransaction;


import com.example.prasad.bank.Data.Account;
import com.example.prasad.bank.Transaction.Benificiary;
import com.example.prasad.bank.Transaction.Payee;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;



public class Pay extends AppCompatActivity {


    String benificaryuid = "1mYJZj7r8ZR8U4rI4aCVwhxT8V72";
    String payeeuid;
    String Benificaryname;
    Button scan, pay;
    FirebaseUser firebaseUser;
    TextView uid;
    DatabaseReference myref;
    EditText amount;
    Double accountbalance;
    Double benficarybalance;
    Account benficaryAccount;
    Payee payee = new Payee();
    Double payeebalance;
    Benificiary benificiary;
    PayTransaction payTransaction  = new PayTransaction();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pay);
        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
        payeeuid = firebaseUser.getUid();
        scan = (Button) findViewById(R.id.scanbutton_id);
        uid = (TextView) findViewById(R.id.scan_id);
        amount = (EditText) findViewById(R.id.payamount_id);
        pay = (Button) findViewById(R.id.payment_id);
        uid.setText(benificaryuid);
        myref = FirebaseDatabase.getInstance().getReference("Users");
        pay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getpayeebalance();

            }
        });
        scan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Pay.this, Scanner.class);
                startActivityForResult(intent, 1);
            }
        });
        //  pay.setVisibility(View.GONE);
        // amount.setVisibility(View.GONE);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 1) {
            if (resultCode == Activity.RESULT_OK) {
                benificaryuid = data.getStringExtra("uid");

                //

                //  pay.setVisibility(View.VISIBLE);
                // amount.setVisibility(View.VISIBLE);

            }

        }

    }


    void checkbalance() {

        String amountinstring = amount.getText().toString();
        if (amountinstring.isEmpty()) {
            amount.setError("Enter Amount Please");
        } else {
            final Double payamount = Double.parseDouble(amountinstring);
            if (payamount > 10000) {
                amount.setError("Please Enter amount less than 10 Thousand");
            } else {


                if (balnacecheck(payeebalance, payamount)) {
                    deductbalance(payeebalance, payamount);

                }

            }
        }


    }


    boolean balnacecheck(Double accountbalance, Double enterdamount) {
        boolean check = false;


        Double balance = accountbalance;
        Double payamount = enterdamount;
        if (payamount > balance) {

            amount.setError("Not Enough Blance");
            check = false;

        } else {
            check = true;

        }
        return check;
    }


    void deductbalance(Double accountbalnce, Double payamount1) {


        Double newBalance = accountbalnce - payamount1;

        if (addtoBenificary(payamount1)) {
            Account payeeAccount = new Account(newBalance);
            myref.child(payeeuid).child("Account").child("balance").setValue(newBalance);

            payTransaction.payeeTransaction(payeeuid,benificaryuid,payamount1,newBalance);

            }


    }

    boolean addtoBenificary(Double paymount) {



        Double balncefinal = benficarybalance + paymount;

        if (addtobenficary(balncefinal,paymount)) {
            Toast.makeText(this, "Payed TO Benificary", Toast.LENGTH_SHORT).show();
        }
        return true;

    }


    boolean addtobenficary(Double amount,Double payamount) {
        Account account = new Account(amount);
        myref.child(benificaryuid).child("Account").setValue(account);
        payTransaction.benificiaryTransaction(benificaryuid,payeeuid,payamount,amount);
        return true;
    }


    void getpayeebalance() {

        myref.child(payeeuid).child("Account").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Account account = dataSnapshot.getValue(Account.class);
                payeebalance = account.getBalance();
                getbenificarybalnce();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
    void getbenificarybalnce()
    {
        myref.child(benificaryuid).child("Account").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Account account = dataSnapshot.getValue(Account.class);
                benficarybalance =  account.getBalance();
                checkbalance();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }
}



