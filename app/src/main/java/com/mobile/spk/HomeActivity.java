package com.mobile.spk;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.mobile.spk.admin.DataAnggotaActivity;
import com.mobile.spk.admin.MitraActivity;
import com.mobile.spk.anggota.JadwalActivityAnggota;
import com.mobile.spk.anggota.PatroliActivity;
import com.mobile.spk.danru.JadwalHariIniActivity;
import com.mobile.spk.utils.SessionManager;

import java.util.HashMap;

public class HomeActivity extends AppCompatActivity {
    private SessionManager sessionManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.M && checkSelfPermission(android.Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(new String[]{android.Manifest.permission.WRITE_EXTERNAL_STORAGE}, 1001);
        }
        sessionManager = new SessionManager(this);
        sessionManager.checkLogin();
        HashMap<String, String> user = sessionManager.getUserDetail();
        String name_user = user.get(SessionManager.NAMA);
        String level = user.get(SessionManager.LEVEL);
        TextView nama = (TextView) findViewById(R.id.nama_user);
        nama.setText(name_user);
        getView(level);
        


    }
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case 1001:
                if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(this, "Ijin Diterima!", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(this, "Ijin Ditolak!", Toast.LENGTH_SHORT).show();
                    finish();
                }
        }
    }
    private void getView(String level) {
        LinearLayout viewAnggota = (LinearLayout) findViewById(R.id.dashboard_anggota);
        LinearLayout viewDanru = (LinearLayout) findViewById(R.id.dashboard_danru);
        LinearLayout viewAdmin = (LinearLayout) findViewById(R.id.dashboard_admin);

        if(level.equals("1")){
            viewAnggota.setVisibility(View.VISIBLE);
        }else if(level.equals("2")){
            viewDanru.setVisibility(View.VISIBLE);
        }else if(level.equals("3")){
            viewAdmin.setVisibility(View.VISIBLE);
        }

    }


    public void toLogout(View view) {
    sessionManager.logout();
    }

    public void toCutiMenu(View view) {
    startActivity(new Intent(HomeActivity.this,CutiActivity.class));
    }

    public void toDataAnggota(View view) {
    startActivity(new Intent(HomeActivity.this, DataAnggotaActivity.class));
    }

    public void toDataMitra(View view) {
    startActivity(new Intent(HomeActivity.this, MitraActivity.class));
    }

    public void toInformasi(View view) {
    startActivity(new Intent(HomeActivity.this, InformasiActivity.class));
    }

    public void toJadwalAbsenUser(View view) {
        //startActivity(new Intent(HomeActivity.this, JadwalActivityAnggota.class).putExtra("triggerView","absen"));
        startActivity(new Intent(HomeActivity.this, JadwalActivityAnggota.class).putExtra("triggerView","absen"));
    }

    public void toJadwalDanru(View view) {
        startActivity(new Intent(HomeActivity.this, JadwalHariIniActivity.class));
    }

    public void toPatroliPetugas(View view) {
        startActivity(new Intent(HomeActivity.this, PatroliActivity.class));
    }

    public void toJadwalUmumPetugas(View view) {
        startActivity(new Intent(HomeActivity.this, JadwalHariIniActivity.class).putExtra("triggerView","petugas"));

    }
}