package com.mobile.spk.anggota;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.mobile.spk.R;
import com.mobile.spk.adapter.TableAdapterJadwalTahunUmum;
import com.mobile.spk.model.JadwalTahunUmum;
import com.mobile.spk.model.JadwalTahunUser;

import java.util.ArrayList;
import java.util.List;

public class JadwalUmum extends AppCompatActivity {
    private RecyclerView rv_jadwal_umum_tahun;
    private TableAdapterJadwalTahunUmum adapterJadwalTahunUmum;
    private TableAdapterJadwalTahunUmum.RecyclerViewClickListener listener;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jadwal_umum);

        rv_jadwal_umum_tahun = findViewById(R.id.rv_jadwal_umum_anggota);
        listener = new TableAdapterJadwalTahunUmum.RecyclerViewClickListener() {
            @Override
            public void onRowClick(View view, int position) {
                Intent i = new Intent(JadwalUmum.this,DetailJadwalUmum.class);
                i.putExtra("bulan", "mei");
                startActivity(i);
            }
        };

        adapterJadwalTahunUmum = new TableAdapterJadwalTahunUmum(getApplicationContext(), getJadwalUmum(), listener);
        rv_jadwal_umum_tahun.setLayoutManager(new LinearLayoutManager(this));
        rv_jadwal_umum_tahun.setHasFixedSize(true);
        rv_jadwal_umum_tahun.setAdapter(adapterJadwalTahunUmum);
    }

    private List<JadwalTahunUmum> getJadwalUmum(){
        List<JadwalTahunUmum> jadwal = new ArrayList<>();
        jadwal.add(new JadwalTahunUmum("Mei","2020"));
        jadwal.add(new JadwalTahunUmum("Juni","2020"));
        jadwal.add(new JadwalTahunUmum("Juli","2020"));
        return jadwal;
    }
}