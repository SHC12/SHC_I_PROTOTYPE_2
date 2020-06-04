package com.mobile.spk.danru;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.mobile.spk.R;

public class DetailCutiDanru extends AppCompatActivity {
    Spinner status;
    String items[] = {"Pengajuan","Proses","Disetujui","Ditolak"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_cuti_danru);

        status = findViewById(R.id.statusDetailCuti);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,R.layout.support_simple_spinner_dropdown_item,items);
        status.setAdapter(adapter);

    }
}