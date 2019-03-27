package com.example.yomaestacionamiento;

import android.annotation.SuppressLint;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;

import com.google.zxing.Result;

import me.dm7.barcodescanner.zxing.ZXingScannerView;

public class Qr extends AppCompatActivity implements ZXingScannerView.ResultHandler {

    ImageButton btnQ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qr);

        btnQ = (ImageButton) findViewById(R.id.btnqr);
    }

    private ZXingScannerView mScannerView;

    public void botonQR(View v){

        mScannerView = new ZXingScannerView(this);
        setContentView(mScannerView);
        mScannerView.setResultHandler(this);
        mScannerView.startCamera();

    }

    @Override
    public void handleResult(Result result) {

        Log.v("HandleResult",result.getText());
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Resultado del scan");
        builder.setMessage(result.getText());
        AlertDialog alertDialog = builder.create();
        alertDialog.show();

        mScannerView.resumeCameraPreview(this);
    }
}
