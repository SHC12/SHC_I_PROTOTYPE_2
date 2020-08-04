package com.mobile.spk.danru;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.mobile.spk.HomeActivity;
import com.mobile.spk.R;
import com.mobile.spk.adapter.TableAdapterListJadwalPersonalDanru;
import com.mobile.spk.adapter.TableAdapterPatroli;
import com.mobile.spk.model.JadwalPersonalDanruModel;
import com.mobile.spk.model.PatroliModel;

import java.util.ArrayList;
import java.util.List;

public class LaporanPatroliDanru extends AppCompatActivity {
    private RecyclerView rv_patroli;
    private TableAdapterPatroli adapterPatroli;
    private TableAdapterPatroli.RecyclerViewClickListener listener;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_laporan_patroli_danru);
        rv_patroli = findViewById(R.id.rv_patroli_danru);
        listener = new TableAdapterPatroli.RecyclerViewClickListener() {
            @Override
            public void onRowClick(View view, int position) {
                Intent i = new Intent(LaporanPatroliDanru.this, DetailLaporanDanru.class);

                startActivity(i);
            }
        };
        adapterPatroli = new TableAdapterPatroli(getApplicationContext(), getData(),listener);
        rv_patroli.setLayoutManager(new LinearLayoutManager(this));
        rv_patroli.setHasFixedSize(true);
        rv_patroli.setAdapter(adapterPatroli);
        initToolbar();
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

    private List<PatroliModel> getData(){
        List<PatroliModel> data = new ArrayList<>();
//        data.add(new PatroliModel("1","03-05-2020", "1"));
//        data.add(new PatroliModel("2","02-05-2020","1"));
//        data.add(new PatroliModel("3","01-05-2020","1"));


        return data;
    }
}