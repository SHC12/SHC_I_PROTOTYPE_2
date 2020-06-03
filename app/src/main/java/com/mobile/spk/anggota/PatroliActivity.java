package com.mobile.spk.anggota;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.android.material.button.MaterialButton;
import com.mobile.spk.R;

public class PatroliActivity extends AppCompatActivity implements View.OnClickListener {
    private MaterialButton gedungA,gedungB,gedungC;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patroli);
        gedungA = findViewById(R.id.btnGedungA);
        gedungA.setOnClickListener(this);
        gedungB = findViewById(R.id.btnGedungB);
        gedungB.setOnClickListener(this);
        gedungC = findViewById(R.id.btnGedungC);
        gedungC.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.btnGedungA:
                Intent iA = new Intent(PatroliActivity.this,FormGedung.class);
                iA.putExtra("flagGedung","A");
                startActivity(iA);
                break;
            case R.id.btnGedungB:
                Intent iB = new Intent(PatroliActivity.this,FormGedung.class);
                iB.putExtra("flagGedung","B");
                startActivity(iB);
                break;
            case R.id.btnGedungC:
                Intent iC = new Intent(PatroliActivity.this,FormGedung.class);
                iC.putExtra("flagGedung","C");
                startActivity(iC);
                break;
        }
    }
}