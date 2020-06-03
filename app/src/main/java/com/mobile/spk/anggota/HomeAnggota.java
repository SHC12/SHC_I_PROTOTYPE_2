package com.mobile.spk.anggota;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.mobile.spk.MenuLoginActivity;
import com.mobile.spk.R;

public class HomeAnggota extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_anggota);
    }

    public void toPatroli(View view) {
        startActivity(new Intent(HomeAnggota.this,PatroliActivity.class));
    }

    public void toAbsensiAnggota(View view) {
        startActivity(new Intent(HomeAnggota.this,AbsensiAnggota.class));
    }

    public void toJadwalPersonal(View view) {
        startActivity(new Intent(HomeAnggota.this,JadwalPersonal.class));
    }

    public void toJadwalUmum(View view) {
        startActivity(new Intent(HomeAnggota.this,JadwalUmum.class));
    }

    public void toCuti(View view) {
        startActivity(new Intent(HomeAnggota.this,CutiActivity.class));
    }

    public void toInformasi(View view) {
        startActivity(new Intent(HomeAnggota.this,InformasiActivity.class));
    }

    public void toLogout(View view) {
        startActivity(new Intent(HomeAnggota.this, MenuLoginActivity.class));
    }
}