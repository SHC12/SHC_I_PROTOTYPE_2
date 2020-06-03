package com.mobile.spk.danru;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.mobile.spk.R;
import com.mobile.spk.adapter.TableAdapterListJadwalPersonalDanru;

public class JadwalPersonalDanru extends AppCompatActivity {
    private RecyclerView rvDanru;
    private TableAdapterListJadwalPersonalDanru adapterListJadwalPersonalDanru;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jadwal_personal_danru);
    }
}