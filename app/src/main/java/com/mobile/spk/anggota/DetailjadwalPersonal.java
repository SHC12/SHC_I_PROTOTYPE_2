package com.mobile.spk.anggota;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.mobile.spk.R;
import com.mobile.spk.adapter.TableAdapterAbsenUser;
import com.mobile.spk.adapter.TableAdapterDetailPersonal;
import com.mobile.spk.model.AbsenUser;
import com.mobile.spk.model.JadwalBulanUser;

import java.util.ArrayList;
import java.util.List;

public class DetailjadwalPersonal extends AppCompatActivity {
    private RecyclerView rv_detail_personal;
    private TableAdapterDetailPersonal adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailjadwal_personal);

        rv_detail_personal = findViewById(R.id.rv_jadwal_personal_anggota);
        adapter = new TableAdapterDetailPersonal(getApplicationContext(), getDetailJadwal());
        rv_detail_personal.setLayoutManager(new LinearLayoutManager(this));
        rv_detail_personal.setHasFixedSize(true);
        rv_detail_personal.setAdapter(adapter);
    }

    private List<JadwalBulanUser> getDetailJadwal(){
        List<JadwalBulanUser> data = new ArrayList<>();
        data.add(new JadwalBulanUser("2020-05-30", "1", "Gedung A"));
        data.add(new JadwalBulanUser("2020-05-30", "2", "Gedung B"));
        data.add(new JadwalBulanUser("2020-05-30", "1","Gedung B"));
        return data;
    }
}