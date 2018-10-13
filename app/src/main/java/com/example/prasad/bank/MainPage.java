package com.example.prasad.bank;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.prasad.bank.Data.Qrcode;
import com.example.prasad.bank.Data.User;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainPage extends AppCompatActivity {

    TextView nametextView;
    
    User user = new User();
    long mobno;
    MyApplication application;
    Button addmoney,pay;
    ImageView imageView;

    Qrcode qrcode = new Qrcode();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_page);
        application = (MyApplication) getApplication();
        imageView  = (ImageView)findViewById(R.id.qrcode_id);
            addmoney = (Button)findViewById(R.id.add_money_id);
            pay = (Button)findViewById(R.id.scantopay_id);
            pay.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity(new Intent(MainPage.this,Pay.class));
                }
            });
            addmoney.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent= new Intent(MainPage.this,Setprice.class);
                    startActivity(intent);
                }
            });
        FirebaseUser firebaseUser  = FirebaseAuth.getInstance().getCurrentUser();
        String name  =   firebaseUser.getEmail();
         String uid =  firebaseUser.getUid();
        nametextView = findViewById(R.id.nameTextView);
        Bitmap bitmap = qrcode.createQrCode(uid);
        imageView.setImageBitmap(bitmap);

        nametextView.setText("Hello " + uid);
    }

    void userinfo(View view) {
        Intent intent = new Intent(this, Userinfo.class);

        startActivity(intent);

    }

    void logout(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }

}
