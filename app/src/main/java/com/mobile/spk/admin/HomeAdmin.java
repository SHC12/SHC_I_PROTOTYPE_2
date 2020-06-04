package com.mobile.spk.admin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.mobile.spk.MenuLoginActivity;
import com.mobile.spk.R;
import com.mobile.spk.anggota.InformasiActivity;
import com.mobile.spk.danru.CutiDanruActivity;
import com.mobile.spk.danru.HomeDanru;

public class HomeAdmin extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_admin);
    }

    public void toDataAnggota(View view) {
        startActivity(new Intent(HomeAdmin.this, DataAnggota.class));
    }

    public void toMitra(View view) {
        startActivity(new Intent(HomeAdmin.this, MitraActivity.class));
    }

    public void toCutiAdmin(View view) {
        startActivity(new Intent(HomeAdmin.this, CutiDanruActivity.class));
    }

    public void toLogout(View view) {
        startActivity(new Intent(HomeAdmin.this, MenuLoginActivity.class));
    }

    public void toInformasi(View view) {
        startActivity(new Intent(HomeAdmin.this, InformasiActivity.class));
    }
}