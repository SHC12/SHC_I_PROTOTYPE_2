package com.mobile.spk.anggota;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.mobile.spk.R;
import com.mobile.spk.adapter.TableAdapterAbsenUser;
import com.mobile.spk.model.AbsenUser;

import java.util.ArrayList;
import java.util.List;

public class AbsensiAnggota extends AppCompatActivity {

    private RecyclerView rvRiwayatAbsenUser;
    private TableAdapterAbsenUser adapterAbsenUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_absensi_anggota);

        rvRiwayatAbsenUser = findViewById(R.id.rv_riwayat_absen_user);
        adapterAbsenUser = new TableAdapterAbsenUser(getApplicationContext(), getAbsen());
        rvRiwayatAbsenUser.setLayoutManager(new LinearLayoutManager(this));
        rvRiwayatAbsenUser.setHasFixedSize(true);
        rvRiwayatAbsenUser.setAdapter(adapterAbsenUser);
    }

    public void toKeamananAbsensi(View view) {
        startActivity(new Intent(AbsensiAnggota.this,ScanBarcodeActivity.class));
    }

    private List<AbsenUser> getAbsen(){
        List<AbsenUser> absen = new ArrayList<>();
        absen.add(new AbsenUser("1","2020-05-30", "08:00", "1", "1"));
        absen.add(new AbsenUser("2","2020-05-30", "09:00",  "2", "0"));
        absen.add(new AbsenUser("3","2020-05-30", "10:00","2", "0"));
        return absen;
    }
}