package com.mobile.spk.danru;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.mobile.spk.R;
import com.mobile.spk.adapter.TableAdapterJadwalBulanUmum;
import com.mobile.spk.adapter.TableAdapterJadwalTahunUser;
import com.mobile.spk.adapter.TableAdapterListJadwalPersonalDanru;
import com.mobile.spk.anggota.DetailjadwalPersonal;
import com.mobile.spk.anggota.JadwalPersonal;
import com.mobile.spk.model.JadwalPersonalDanruModel;
import com.mobile.spk.model.JadwalTahunUser;

import java.util.ArrayList;
import java.util.List;

public class JadwalPersonalDanru extends AppCompatActivity {
    private RecyclerView rvDanru;
    private TableAdapterListJadwalPersonalDanru adapterListJadwalPersonalDanru;
    private TableAdapterListJadwalPersonalDanru.RecyclerViewClickListener listener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jadwal_personal_danru);

        rvDanru = findViewById(R.id.rv_jadwal_personal_danru);
        listener = new TableAdapterListJadwalPersonalDanru.RecyclerViewClickListener() {
            @Override
            public void onRowClick(View view, int position) {
                Intent i = new Intent(JadwalPersonalDanru.this, FormInputJadwal.class);

                startActivity(i);
            }
        };
        adapterListJadwalPersonalDanru = new TableAdapterListJadwalPersonalDanru(getApplicationContext(), getData(),listener);
        rvDanru.setLayoutManager(new LinearLayoutManager(this));
        rvDanru.setHasFixedSize(true);
        rvDanru.setAdapter(adapterListJadwalPersonalDanru);


    }
    private List<JadwalPersonalDanruModel> getData(){
        List<JadwalPersonalDanruModel> jadwal = new ArrayList<>();
        jadwal.add(new JadwalPersonalDanruModel("1","Aji Baskoro"));
        jadwal.add(new JadwalPersonalDanruModel("2","Dahlan Hidayat"));
        jadwal.add(new JadwalPersonalDanruModel("3","Suhendar"));


        return jadwal;
    }

}