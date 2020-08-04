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
import com.mobile.spk.adapter.TableAdapterJadwalBulanUmum;
import com.mobile.spk.adapter.TableAdapterJadwalTahunUmum;
import com.mobile.spk.model.JadwalBulanUmum;
import com.mobile.spk.model.JadwalTahunUmum;

import java.util.ArrayList;
import java.util.List;

public class DetailJadwalUmum extends AppCompatActivity {
    private RecyclerView rv_jadwal_umum_bulan;
    private TableAdapterJadwalBulanUmum adapterJadwalBulanUmum;
    private TableAdapterJadwalBulanUmum.RecyclerViewClickListener listener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_jadwal_umum);

        rv_jadwal_umum_bulan = findViewById(R.id.rv_detail_jadwal_umum_anggota);
        listener = new TableAdapterJadwalBulanUmum.RecyclerViewClickListener() {
            @Override
            public void onRowClick(View view, int position) {
                Intent i = new Intent(DetailJadwalUmum.this,DetailJadwalUmumPergedung.class);
                i.putExtra("gedung", "a");
                startActivity(i);
            }
        };

        adapterJadwalBulanUmum = new TableAdapterJadwalBulanUmum(getApplicationContext(), getJadwalDetailUmum(), listener);
        rv_jadwal_umum_bulan.setLayoutManager(new LinearLayoutManager(this));
        rv_jadwal_umum_bulan.setHasFixedSize(true);
        rv_jadwal_umum_bulan.setAdapter(adapterJadwalBulanUmum);

        initToolbar();
    }

    private List<JadwalBulanUmum> getJadwalDetailUmum(){
        List<JadwalBulanUmum> jadwal = new ArrayList<>();
        jadwal.add(new JadwalBulanUmum("02-05-2020","Gedung A"));
        jadwal.add(new JadwalBulanUmum("02-05-2020","Gedung A"));
        jadwal.add(new JadwalBulanUmum("02-05-2020","Gedung A"));
        return jadwal;
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