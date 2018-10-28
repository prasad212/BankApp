package com.example.prasad.bank.Transaction;

public class TransactionDetail {
    public String getPayeeUid() {
        return payeeUid;
    }

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }

    Double balance;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    String date;

    public void setPayeeUid(String payeeUid) {
        this.payeeUid = payeeUid;
    }

    public String getBenificaryuid() {
        return benificaryuid;
    }

    public void setBenificaryuid(String benificaryuid) {
        this.benificaryuid = benificaryuid;
    }

    public Double getDebitedamount() {
        return debitedamount;
    }

    public void setDebitedamount(Double debitedamount) {
        this.debitedamount = debitedamount;
    }

    public Double getCreditedamount() {
        return creditedamount;
    }

    public void setCreditedamount(Double creditedamount) {
        this.creditedamount = creditedamount;
    }

    String payeeUid;
    String benificaryuid;

    public TransactionDetail(Double balance, String date, String payeeUid, String benificaryuid, Double debitedamount, Double creditedamount) {
        this.balance = balance;
        this.date = date;
        this.payeeUid = payeeUid;
        this.benificaryuid = benificaryuid;
        this.debitedamount = debitedamount;
        this.creditedamount = creditedamount;
    }

    Double debitedamount;
    Double creditedamount;
    public TransactionDetail()
    {}
}
