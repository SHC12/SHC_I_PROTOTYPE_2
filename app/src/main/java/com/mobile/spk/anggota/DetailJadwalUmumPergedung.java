package com.mobile.spk.anggota;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.mobile.spk.HomeActivity;
import com.mobile.spk.R;
import com.mobile.spk.adapter.TableAdapterAbsenUser;
import com.mobile.spk.adapter.TableAdapterJadwalUmumPergedung;
import com.mobile.spk.model.AbsenUser;
import com.mobile.spk.model.JadwalDetailUmum;

import java.util.ArrayList;
import java.util.List;

public class DetailJadwalUmumPergedung extends AppCompatActivity {
    private RecyclerView rv_detail_jadwal_umum;
    private TableAdapterJadwalUmumPergedung adapterJadwalUmumPergedung;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_jadwal_umum_pergedung);

        rv_detail_jadwal_umum = findViewById(R.id.rv_detail_jadwal_umum_gedung_anggota);
        adapterJadwalUmumPergedung = new TableAdapterJadwalUmumPergedung(getApplicationContext(), getData());
        rv_detail_jadwal_umum.setLayoutManager(new LinearLayoutManager(this));
        rv_detail_jadwal_umum.setHasFixedSize(true);
        rv_detail_jadwal_umum.setAdapter(adapterJadwalUmumPergedung);

        initToolbar();
    }

    private List<JadwalDetailUmum> getData(){
        List<JadwalDetailUmum> data = new ArrayList<>();
        data.add(new JadwalDetailUmum("1","Dahlan Hidayat"));
        data.add(new JadwalDetailUmum("1","Aji Baskoro"));
        data.add(new JadwalDetailUmum("2","Suhendar"));
        data.add(new JadwalDetailUmum("1","Suhendar"));

        return data;
    }

    private void initToolbar() {
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), HomeActivity.class));
            }
        });
        toolbar.setNavigationIcon(R.drawable.ic_baseline_arrow_back_24);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        getSupportActionBar().setTitle(null);

    }
}