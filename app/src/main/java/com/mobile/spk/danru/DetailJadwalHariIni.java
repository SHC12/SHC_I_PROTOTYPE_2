package com.mobile.spk.danru;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.mobile.spk.R;
import com.mobile.spk.adapter.TableAdapterJadwalUmumPergedung;
import com.mobile.spk.model.JadwalDetailUmum;

import java.util.ArrayList;
import java.util.List;

public class DetailJadwalHariIni extends AppCompatActivity {
    private RecyclerView rv_detail_jadwal_hari_ini;
    private TableAdapterJadwalUmumPergedung adapterJadwalHariIniPergedung;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_jadwal_hari_ini);

        rv_detail_jadwal_hari_ini = findViewById(R.id.rv_detail_jadwal_hari_ini);
        adapterJadwalHariIniPergedung = new TableAdapterJadwalUmumPergedung(getApplicationContext(), getData());
        rv_detail_jadwal_hari_ini.setLayoutManager(new LinearLayoutManager(this));
        rv_detail_jadwal_hari_ini.setHasFixedSize(true);
        rv_detail_jadwal_hari_ini.setAdapter(adapterJadwalHariIniPergedung);
    }

    private List<JadwalDetailUmum> getData(){
        List<JadwalDetailUmum> data = new ArrayList<>();
        data.add(new JadwalDetailUmum("1","Dahlan Hidayat"));
        data.add(new JadwalDetailUmum("1","Aji Baskoro"));
        data.add(new JadwalDetailUmum("2","Suhendar"));
        data.add(new JadwalDetailUmum("2","Suhendar"));

        return data;
    }
}