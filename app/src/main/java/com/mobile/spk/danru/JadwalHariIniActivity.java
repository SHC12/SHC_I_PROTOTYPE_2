package com.mobile.spk.danru;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.mobile.spk.R;

public class JadwalHariIniActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jadwal_hari_ini);
    }

    public void toJadwalUmumDanru(View view) {
        startActivity(new Intent(JadwalHariIniActivity.this,DetailJadwalUmumBulan.class));
    }

    public void toJadwalPersonalDanru(View view) {
        startActivity(new Intent(JadwalHariIniActivity.this,JadwalPersonalDanru.class));
    }

    public void toGedungA(View view) {
        startActivity(new Intent(JadwalHariIniActivity.this,DetailJadwalHariIni.class));
    }
}