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
        initToolbar();
    }

    private List<JadwalBulanUser> getDetailJadwal(){
        List<JadwalBulanUser> data = new ArrayList<>();
        data.add(new JadwalBulanUser("2020-05-30", "1", "Gedung A"));
        data.add(new JadwalBulanUser("2020-05-30", "2", "Gedung B"));
        data.add(new JadwalBulanUser("2020-05-30", "1","Gedung B"));
        return data;
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