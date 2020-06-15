package com.mobile.spk.danru;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.mobile.spk.R;
import com.mobile.spk.adapter.TableAdapterAbsenHariIni;
import com.mobile.spk.adapter.TableAdapterListJadwalPersonalDanru;
import com.mobile.spk.model.AbsenHariIni;
import com.mobile.spk.model.JadwalPersonalDanruModel;

import java.util.ArrayList;
import java.util.List;

public class DaftarAbsensiHariIni extends AppCompatActivity {
    private RecyclerView rvAbsen;
    private TableAdapterAbsenHariIni tableAdapterAbsenHariIni;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daftar_absensi_hari_ini);

        rvAbsen = findViewById(R.id.rv_absen_hari_ini);

        tableAdapterAbsenHariIni = new TableAdapterAbsenHariIni(getApplicationContext(), getData());
        rvAbsen.setLayoutManager(new LinearLayoutManager(this));
        rvAbsen.setHasFixedSize(true);
        rvAbsen.setAdapter(tableAdapterAbsenHariIni);
    }

    private List<AbsenHariIni> getData(){
        List<AbsenHariIni> jadwal = new ArrayList<>();
        jadwal.add(new AbsenHariIni("1","Aji Baskoro", "1","0"));
        jadwal.add(new AbsenHariIni("2","Dahlan Hidayat", "2","1"));
        jadwal.add(new AbsenHariIni("3","Suhendar","1","2"));

        return jadwal;
    }
}