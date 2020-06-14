package com.mobile.spk.admin;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.mobile.spk.R;
import com.mobile.spk.adapter.TableAdapterMitra;
import com.mobile.spk.model.DataAnggota;
import com.mobile.spk.model.Mitra;

import java.util.ArrayList;
import java.util.List;

public class MitraActivity extends AppCompatActivity {
    private RecyclerView rv_data;
    private TableAdapterMitra adapterMitra;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mitra);

        rv_data = findViewById(R.id.recyclerView2);
//        listener = new TableAdapterMitra.RecyclerViewClickListener() {
//            @Override
//            public void onRowClick(View view, int position) {
//                Intent i = new Intent(MitraActivity.this, DetailDataAnggota.class);
//                startActivity(i);
//            }
//        };
        adapterMitra = new TableAdapterMitra(getApplicationContext(), getData());
        rv_data.setLayoutManager(new LinearLayoutManager(this));
        rv_data.setHasFixedSize(true);
        rv_data.setAdapter(adapterMitra);
    }

    private List<Mitra> getData(){
        List<Mitra> data = new ArrayList<>();
        data.add(new Mitra("1","Universitas M.H ", "Jakarta Timur"));
        data.add(new Mitra("2","Bintaro Mansion", "Jakarta Selatan"));
        data.add(new Mitra("3","Rumkit Bhayangkara Brimob", "Depok"));


        return data;
    }
    public void toTambahMitra(View view) {
        startActivity(new Intent(MitraActivity.this, TambahMitra.class));
    }
}