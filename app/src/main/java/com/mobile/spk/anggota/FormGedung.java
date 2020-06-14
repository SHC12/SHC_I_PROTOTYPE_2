package com.mobile.spk.anggota;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.LinearLayout;

import com.mobile.spk.R;

import java.lang.reflect.Array;

public class FormGedung extends AppCompatActivity {
    private LinearLayout lBasement,l1,l2,l3,l4,l5,l6,l7,l8,l1b,l2b;
    private AutoCompleteTextView aL1,aL2,aL3,aL4,aL5,aL6,aL7,aL8,aL1b,aL2b,aLbasement,gedung;
    private String[] status_gedung = {"Aman","Mencurigakan","Tidak Aman","Bahaya"};
    private String[] nama_gedungA = {"Gedung A1","Gedung A2"};
    private String[] nama_gedungB = {"Gedung B1","Gedung B2"};
    private String[] nama_gedungC = {"Gedung C1","Gedung C2"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_gedung);
        getForm();
        getSpinner();
    }

    private void getSpinner() {
        aL1 = findViewById(R.id.in_lantai_1);
        getAdapter(aL1,status_gedung);
        aL2 = findViewById(R.id.in_lantai_2);
        getAdapter(aL2,status_gedung);
        aL3 = findViewById(R.id.in_lantai_3);
        getAdapter(aL3,status_gedung);
        aL4 = findViewById(R.id.in_lantai_4);
        getAdapter(aL4,status_gedung);
        aL5 = findViewById(R.id.in_lantai_5);
        getAdapter(aL5,status_gedung);
        aL6 = findViewById(R.id.in_lantai_6);
        getAdapter(aL6,status_gedung);
        aL7 = findViewById(R.id.in_lantai_7);
        getAdapter(aL7,status_gedung);
        aL8 = findViewById(R.id.in_lantai_8);
        getAdapter(aL8,status_gedung);
        aL1b = findViewById(R.id.in_lantai_1b);
        getAdapter(aL1b,status_gedung);
        aL2b = findViewById(R.id.in_lantai_2b);
        getAdapter(aL2b,status_gedung);
        aLbasement = findViewById(R.id.in_basement);
        getAdapter(aLbasement,status_gedung);
        String flagGedung = getIntent().getStringExtra("flagGedung");
        if(flagGedung.equals("A")) {
            gedung = findViewById(R.id.in_nama_gedung);
            getAdapter(gedung, nama_gedungA);
        }else if(flagGedung.equals("B")) {
            gedung = findViewById(R.id.in_nama_gedung);
            getAdapter(gedung, nama_gedungB);
        }else if(flagGedung.equals("C")) {
            gedung = findViewById(R.id.in_nama_gedung);
            getAdapter(gedung, nama_gedungC);
        }
    }

    private void getAdapter(AutoCompleteTextView at,String[] status){
      ArrayAdapter<String> adapter= new ArrayAdapter<>(getApplicationContext(),R.layout.spinner_list_item,status);
        at.setAdapter(adapter);
    }


    private void getForm() {
        lBasement = findViewById(R.id.line_basement);
        l4 = findViewById(R.id.line_lantai_4);
        l5 = findViewById(R.id.line_lantai_5);
        l6 = findViewById(R.id.line_lantai_6);
        l7 = findViewById(R.id.line_lantai_7);
        l8 = findViewById(R.id.line_lantai_8);
        l1b = findViewById(R.id.line_lantai_1b);
        l2b = findViewById(R.id.line_lantai_2b);

        String flagGedung = getIntent().getStringExtra("flagGedung");
        if(flagGedung.equals("A")){
            lBasement.setVisibility(View.GONE);
            l6.setVisibility(View.GONE);
            l7.setVisibility(View.GONE);
            l8.setVisibility(View.GONE);
            l1b.setVisibility(View.GONE);
            l2b.setVisibility(View.GONE);
        } else if(flagGedung.equals("B")){
            lBasement.setVisibility(View.GONE);
            l4.setVisibility(View.GONE);
            l5.setVisibility(View.GONE);
            l6.setVisibility(View.GONE);
            l7.setVisibility(View.GONE);
            l8.setVisibility(View.GONE);
        } else if(flagGedung.equals("C")){
            l1b.setVisibility(View.GONE);
            l2b.setVisibility(View.GONE);

        }

    }
}