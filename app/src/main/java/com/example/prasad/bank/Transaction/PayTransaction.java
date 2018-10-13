package com.example.prasad.bank.Transaction;

import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class PayTransaction {

        DatabaseReference myref;

  public   void payeeTransaction(String payeeuid,String benificaryuid,Double debitedamount,Double balance)
    {
        String uid = payeeuid;
        TransactionDetail transactionDetail = new TransactionDetail();
        transactionDetail.setPayeeUid(payeeuid);
        transactionDetail.setBenificaryuid(uid);
        transactionDetail.setDebitedamount(debitedamount);
        transactionDetail.setCreditedamount(null);
        transactionDetail.setBalance(balance);
        String date = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(new Date());
        transactionDetail.setDate(date);
        myref = FirebaseDatabase.getInstance().getReference("Users");
        myref.child(uid).child("Transaction").push().setValue(transactionDetail);
    }

  public   void benificiaryTransaction(String benificaryuid,String payeeuid,Double creitedamount,Double balance)
    {
        String uid = benificaryuid;
        TransactionDetail transactionDetail = new TransactionDetail();
        transactionDetail.setPayeeUid(payeeuid);
        transactionDetail.setBenificaryuid(uid);
        transactionDetail.setDebitedamount(null);
        transactionDetail.setCreditedamount(creitedamount);
        transactionDetail.setBalance(balance);
        String date = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(new Date());
        transactionDetail.setDate(date);
        myref = FirebaseDatabase.getInstance().getReference("Users");
        myref.child(uid).child("Transaction").push().setValue(transactionDetail);
    }

}
