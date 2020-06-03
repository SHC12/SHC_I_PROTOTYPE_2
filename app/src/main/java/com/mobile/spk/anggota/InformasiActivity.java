package com.mobile.spk.anggota;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.mobile.spk.R;
import com.mobile.spk.adapter.TableAdapterInformasi;
import com.mobile.spk.adapter.TableAdapterPengajuanCuti;
import com.mobile.spk.model.Cuti;
import com.mobile.spk.model.Informasi;

import java.util.ArrayList;
import java.util.List;

public class InformasiActivity extends AppCompatActivity {
    private RecyclerView rv_informaasi;
    private TableAdapterInformasi adapterInformasi;
    private TableAdapterInformasi.RecyclerViewClickListener listener;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_informasi);
        rv_informaasi = findViewById(R.id.rv_info);
        listener = new TableAdapterInformasi.RecyclerViewClickListener() {
            @Override
            public void onRowClick(View view, int position) {
                Intent i = new Intent(InformasiActivity.this,Detailinformasi.class);
                startActivity(i);
            }
        };

        adapterInformasi = new TableAdapterInformasi(getApplicationContext(), getDataInformasi(), listener);
        rv_informaasi.setLayoutManager(new LinearLayoutManager(this));
        rv_informaasi.setHasFixedSize(true);
        rv_informaasi.setAdapter(adapterInformasi);
    }

    private List<Informasi> getDataInformasi(){
        List<Informasi> data = new ArrayList<>();
        data.add(new Informasi("02-05-2020","Informasi PSBB"));
        data.add(new Informasi("02-04-2020","Perubahan Struktur Danru"));
        data.add(new Informasi("02-04-2020","Perubahan Struktur Danru"));


        return data;
    }

    public void toFormInformasi(View view) {
        startActivity(new Intent(InformasiActivity.this, FormInformasi.class));
    }
}