package com.mobile.spk.danru;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.mobile.spk.R;
import com.mobile.spk.adapter.TableAdapterCutiDanru;
import com.mobile.spk.adapter.TableAdapterPatroli;
import com.mobile.spk.model.CutiDanru;
import com.mobile.spk.model.PatroliModel;

import java.util.ArrayList;
import java.util.List;

public class CutiDanruActivity extends AppCompatActivity {
    private RecyclerView rv_cuti;
    private TableAdapterCutiDanru adapterCutiDanru;
    private TableAdapterCutiDanru.RecyclerViewClickListener listener;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cuti_danru);
        rv_cuti = findViewById(R.id.rv_cuti_danru);
        listener = new TableAdapterCutiDanru.RecyclerViewClickListener() {
            @Override
            public void onRowClick(View view, int position) {
                Intent i = new Intent(CutiDanruActivity.this, DetailCutiDanru.class);
                startActivity(i);
            }
        };
        adapterCutiDanru = new TableAdapterCutiDanru(getApplicationContext(), getData(), listener);
        rv_cuti.setLayoutManager(new LinearLayoutManager(this));
        rv_cuti.setHasFixedSize(true);
        rv_cuti.setAdapter(adapterCutiDanru);
    }

    private List<CutiDanru> getData(){
        List<CutiDanru> data = new ArrayList<>();
        data.add(new CutiDanru("03-05-2020", "Dahlan Hidayat"));
        data.add(new CutiDanru("02-05-2020", "Suhendar"));



        return data;
    }
}