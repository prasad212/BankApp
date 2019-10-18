package com.example.prasad.bank;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.prasad.bank.Transaction.TransactionDetail;

import java.util.ArrayList;

public class Myadapter extends ArrayAdapter<TransactionDetail> {
    ArrayList<TransactionDetail>arrayList = new ArrayList<>();

    public Myadapter(@NonNull Context context, ArrayList<TransactionDetail> resource) {
            super(context,0, resource);
    }


    @NonNull
    @Override
    public View getView(int position,  View convertView,  ViewGroup parent) {
        TransactionDetail detail = getItem(position);
        if(convertView==null)
        {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.recyclerview,parent,false);
        }
        TextView t1 = (TextView)convertView.findViewById(R.id.date_id);
        TextView t2 = (TextView)convertView.findViewById(R.id.payeeuid_id);
        TextView t3 = (TextView)convertView.findViewById(R.id.benificary_id);
        TextView t4 = (TextView)convertView.findViewById(R.id.amount_id);
        TextView t5 = (TextView)convertView.findViewById(R.id.balance_id);
        ImageView image1 = (ImageView)convertView.findViewById(R.id.image);
        t1.setText(detail.getDate());
        t2.setText(detail.getPayeeUid());
        t3.setText(detail.getBenificaryuid());
        if(detail.getDebitedamount()!=null) {

            t4.setText(String.valueOf(detail.getDebitedamount()));
            image1.setImageResource(R.drawable.debited);
        }else
        {
            t4.setText(String.valueOf(detail.getCreditedamount()));
            image1.setImageResource(R.drawable.credited);
        }
        t5.setText(String.valueOf(detail.getBalance()));
        return convertView;
    }
}
