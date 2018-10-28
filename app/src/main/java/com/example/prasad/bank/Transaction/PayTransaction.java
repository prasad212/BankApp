package com.example.prasad.bank.Transaction;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class PayTransaction {

        DatabaseReference myref;
        FirebaseUser firebaseUser  =FirebaseAuth.getInstance().getCurrentUser();
        String uid  = firebaseUser.getUid();
  public   void payeeTransaction(String benificaryuid,Double debitedamount,Double balance)
    {

        TransactionDetail transactionDetail = new TransactionDetail();
       transactionDetail.setBenificaryuid(benificaryuid);
        //transactionDetail.setPayeeUid(null);

        transactionDetail.setDebitedamount(debitedamount);
        transactionDetail.setCreditedamount(null);
        transactionDetail.setBalance(balance);
        String date = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(new Date());
        transactionDetail.setDate(date);
        myref = FirebaseDatabase.getInstance().getReference("Users");
        myref.child(uid).child("Transaction").push().setValue(transactionDetail);
    }

  public  void benificiaryTransaction(String getuid,Double creitedamount,Double balance)
    {

        TransactionDetail transactionDetail = new TransactionDetail();
        transactionDetail.setPayeeUid(uid);
        transactionDetail.setBenificaryuid(null);
        transactionDetail.setDebitedamount(null);
        transactionDetail.setCreditedamount(creitedamount);
        transactionDetail.setBalance(balance);
        String date = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(new Date());
        transactionDetail.setDate(date);
        myref = FirebaseDatabase.getInstance().getReference("Users");
        myref.child(getuid).child("Transaction").push().setValue(transactionDetail);
    }
    public void addbalance(Double addedbalance,Double balance)
    {
        String added = "Added balance";
            TransactionDetail transactionDetail = new TransactionDetail();
            transactionDetail.setBenificaryuid(null);
            transactionDetail.setPayeeUid(added);
          String date = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(new Date());
            transactionDetail.setDate(date);
        transactionDetail.setCreditedamount(addedbalance);
        transactionDetail.setBalance(balance);
        myref = FirebaseDatabase.getInstance().getReference("Users");
        myref.child(uid).child("Transaction").push().setValue(transactionDetail);

    }
}
