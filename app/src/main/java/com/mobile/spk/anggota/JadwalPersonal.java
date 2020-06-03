package com.mobile.spk.anggota;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.mobile.spk.R;
import com.mobile.spk.adapter.TableAdapterAbsenUser;
import com.mobile.spk.adapter.TableAdapterJadwalTahunUser;
import com.mobile.spk.model.AbsenUser;
import com.mobile.spk.model.JadwalTahunUser;

import java.util.ArrayList;
import java.util.List;

public class JadwalPersonal extends AppCompatActivity {
    private RecyclerView rv_jadwal_personal;
    private TableAdapterJadwalTahunUser adapterJadwalTahunUser;
    private TableAdapterJadwalTahunUser.RecyclerViewClickListener listener;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jadwal_personal);

        rv_jadwal_personal = findViewById(R.id.rv_jadwal_personal_anggota);
        listener = new TableAdapterJadwalTahunUser.RecyclerViewClickListener() {
            @Override
            public void onRowClick(View view, int position) {
                Intent i = new Intent(JadwalPersonal.this,DetailjadwalPersonal.class);
                i.putExtra("bulan", "mei");
                startActivity(i);
            }
        };
        adapterJadwalTahunUser = new TableAdapterJadwalTahunUser(getApplicationContext(), getJadwalTahun(), listener);
        rv_jadwal_personal.setLayoutManager(new LinearLayoutManager(this));
        rv_jadwal_personal.setHasFixedSize(true);
        rv_jadwal_personal.setAdapter(adapterJadwalTahunUser);

    }

    private List<JadwalTahunUser> getJadwalTahun(){
        List<JadwalTahunUser> jadwal = new ArrayList<>();
        jadwal.add(new JadwalTahunUser("Mei","2020"));
        jadwal.add(new JadwalTahunUser("Juni","2020"));
        jadwal.add(new JadwalTahunUser("Juli","2020"));
        return jadwal;
    }
}