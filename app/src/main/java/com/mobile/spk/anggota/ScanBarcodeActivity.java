package com.mobile.spk.anggota;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import com.mobile.spk.R;

import java.text.Normalizer;

public class ScanBarcodeActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scan_barcode);

    }



    public void toFormAbsensi(View view) {
        startActivity(new Intent(ScanBarcodeActivity.this, FormAbsensi.class));
    }
}