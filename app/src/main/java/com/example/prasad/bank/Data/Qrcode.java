package com.example.prasad.bank.Data;

import android.graphics.Bitmap;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.journeyapps.barcodescanner.BarcodeEncoder;

public class Qrcode {
    //for bitimage
    private  Bitmap bitmap;
    //
    MultiFormatWriter multiFormatWriter;
    //bit matrix for creating matrix
    private BitMatrix bitMatrix;
    private String uid;
    BarcodeEncoder barcodeEncoder;

    public Bitmap createQrCode(String uid)
    {

        multiFormatWriter = new MultiFormatWriter();
        try{
            bitMatrix =multiFormatWriter.encode(uid,BarcodeFormat.QR_CODE,200,200);
            barcodeEncoder = new BarcodeEncoder();
            bitmap = barcodeEncoder.createBitmap(bitMatrix);

        }catch (WriterException e)
        {
            e.printStackTrace();
        }
        return bitmap;
    }


}
