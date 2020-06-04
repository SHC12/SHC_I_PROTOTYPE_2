package com.mobile.spk.admin;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.mobile.spk.R;
import com.mobile.spk.adapter.TableAdapterDataAnggota;
import com.mobile.spk.danru.DetailLaporanDanru;
import com.mobile.spk.danru.LaporanPatroliDanru;
import com.mobile.spk.model.PatroliModel;

import java.util.ArrayList;
import java.util.List;

public class DataAnggota extends AppCompatActivity {
    private RecyclerView rv_data;
    private TableAdapterDataAnggota adapterDataAnggota;
    private TableAdapterDataAnggota.RecyclerViewClickListener listener;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_anggota);
        rv_data = findViewById(R.id.recyclerView2);
        listener = new TableAdapterDataAnggota.RecyclerViewClickListener() {
            @Override
            public void onRowClick(View view, int position) {
                Intent i = new Intent(DataAnggota.this, DetailDataAnggota.class);
                startActivity(i);
            }
        };
        adapterDataAnggota = new TableAdapterDataAnggota(getApplicationContext(), getData(),listener);
        rv_data.setLayoutManager(new LinearLayoutManager(this));
        rv_data.setHasFixedSize(true);
        rv_data.setAdapter(adapterDataAnggota);
    }

    private List<com.mobile.spk.model.DataAnggota> getData(){
        List<com.mobile.spk.model.DataAnggota> data = new ArrayList<>();
        data.add(new com.mobile.spk.model.DataAnggota("1","Dahlan Hidayat", "Anggota"));
        data.add(new com.mobile.spk.model.DataAnggota("2","Kurniawan", "PKD"));
        data.add(new com.mobile.spk.model.DataAnggota("3","Dedy N", "Danru"));


        return data;
    }
}