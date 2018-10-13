package com.example.prasad.bank;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import me.dm7.barcodescanner.zbar.Result;
import me.dm7.barcodescanner.zbar.ZBarScannerView;

public class Scanner extends AppCompatActivity implements ZBarScannerView.ResultHandler {
        private  ZBarScannerView zBarScannerView;
        String uid;
    private static final int REQUEST_CAMERA = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        zBarScannerView = new ZBarScannerView(this);
        setContentView(zBarScannerView);
    }

    @Override
    public void handleResult(Result result) {
        uid =      result.getContents();


       /*  AlertDialog.Builder builder = new AlertDialog.Builder(this);
          builder.setTitle("Scan Result");
         builder.setMessage(uid);
         AlertDialog alert1 = builder.create();
         alert1.show();
        */
        Intent intent = new Intent();
        intent.putExtra("uid",uid);
        setResult(Activity.RESULT_OK,intent);
        finish();


    }

    @Override
    protected void onPostResume() {
            super.onPostResume();
            zBarScannerView.setResultHandler(this);
            zBarScannerView.startCamera();
    }

    @Override
    protected void onPause() {
        super.onPause();
        zBarScannerView.stopCamera();
    }


}
