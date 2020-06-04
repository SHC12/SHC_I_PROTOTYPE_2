package com.mobile.spk.danru;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.mobile.spk.MenuLoginActivity;
import com.mobile.spk.R;
import com.mobile.spk.anggota.HomeAnggota;
import com.mobile.spk.anggota.InformasiActivity;
import com.mobile.spk.anggota.JadwalUmum;

public class HomeDanru extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_danru);
    }

    public void LogoutDanru(View view) {
        Intent in = new Intent(HomeDanru.this, MenuLoginActivity.class);
        startActivity(in);
    }

    public void toAbsensiDanru(View view) {
        startActivity(new Intent(getApplicationContext(),AbsensiDanru.class));
    }

    public void toJadwalHariIni(View view) {
        startActivity(new Intent(getApplicationContext(),JadwalHariIniActivity.class));
    }

    public void toJadwalUmum(View view) {
        startActivity(new Intent(HomeDanru.this, JadwalUmum.class));
    }

    public void toJadwalPersonalHomeDanru(View view) {
        startActivity(new Intent(HomeDanru.this,JadwalPersonalDanru.class));
    }

    public void toPatroli(View view) {
        startActivity(new Intent(HomeDanru.this,LaporanPatroliDanru.class));
    }

    public void toCutiDanru(View view) {
        startActivity(new Intent(HomeDanru.this,CutiDanruActivity.class));
    }

    public void toInformasi(View view) {
        startActivity(new Intent(HomeDanru.this, InformasiActivity.class));
    }
}