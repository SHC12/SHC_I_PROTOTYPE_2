package com.mobile.spk.danru;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;

import com.mobile.spk.HomeActivity;
import com.mobile.spk.R;
import com.mobile.spk.anggota.FormAbsensi;

public class AbsensiDanru extends AppCompatActivity {

    AutoCompleteTextView bulanAbsen;
    private String[] bulan = {"Januari","Februari", "Maret", "April", "Mei", "Juni", "Juli", "Agustus", "September", "Oktober", "November", "Desember"};
    String username;

    Button btnGenerate, btnAbsen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_absensi_danru);

        bulanAbsen = findViewById(R.id.in_basement);
        btnAbsen = findViewById(R.id.btnAbsenDanru);
        btnGenerate = findViewById(R.id.materialButton5);

        username = getIntent().getStringExtra("username");
        getSpinner(bulanAbsen, bulan);

        if(username.equals("Admin")){
            btnGenerate.setVisibility(View.INVISIBLE);
            btnAbsen.setVisibility(View.INVISIBLE);
        }else{
            btnGenerate.setVisibility(View.VISIBLE);
            btnAbsen.setVisibility(View.VISIBLE);
        }


        initToolbar();


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

    private void getSpinner(AutoCompleteTextView target, String[] item) {
        ArrayAdapter<String> adapter= new ArrayAdapter<>(getApplicationContext(),R.layout.spinner_list_item,item);
        target.setAdapter(adapter);
    }

    public void toAbsenPetugas(View view) {
        startActivity(new Intent(AbsensiDanru.this, FormAbsensi.class));
    }

    public void toAbsenHariIni(View view) {
        startActivity(new Intent(AbsensiDanru.this, DaftarAbsensiHariIni.class));
    }
}