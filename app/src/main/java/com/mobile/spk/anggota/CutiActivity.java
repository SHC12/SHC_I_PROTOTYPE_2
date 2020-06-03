package com.mobile.spk.anggota;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.mobile.spk.R;
import com.mobile.spk.adapter.TableAdapterJadwalBulanUmum;
import com.mobile.spk.adapter.TableAdapterPengajuanCuti;
import com.mobile.spk.model.Cuti;
import com.mobile.spk.model.JadwalBulanUmum;

import java.util.ArrayList;
import java.util.List;

public class CutiActivity extends AppCompatActivity {
    private RecyclerView rv_cuti;
    private TableAdapterPengajuanCuti adapterCuti;
    private TableAdapterPengajuanCuti.RecyclerViewClickListener listener;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cuti);

        rv_cuti = findViewById(R.id.rv_riwayat_pengajuan_cuti);
        listener = new TableAdapterPengajuanCuti.RecyclerViewClickListener() {
            @Override
            public void onRowClick(View view, int position) {
                Intent i = new Intent(CutiActivity.this,DetailFormAjuanCuti.class);

                startActivity(i);
            }
        };

        adapterCuti = new TableAdapterPengajuanCuti(getApplicationContext(), getDataCuti(), listener);
        rv_cuti.setLayoutManager(new LinearLayoutManager(this));
        rv_cuti.setHasFixedSize(true);
        rv_cuti.setAdapter(adapterCuti);
    }

    public void toFormCuti(View view) {
        startActivity(new Intent(CutiActivity.this, FormAjukanCuti.class));
    }

    private List<Cuti> getDataCuti(){
        List<Cuti> cuti = new ArrayList<>();
        cuti.add(new Cuti("1","02-05-2020","Disetujui"));
        cuti.add(new Cuti("2","02-04-2020","Ditolak"));

        return cuti;
    }
}