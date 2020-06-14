package com.mobile.spk.anggota;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;

import com.mobile.spk.R;

public class FormAbsensi extends AppCompatActivity {
    private String[] status_shift = {"1", "2"};
    private String[] status_absen = {"Hadir","Ijin","Sakit","Cuti"};

    AutoCompleteTextView shift, absen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_absensi);

        shift = findViewById(R.id.in_shift);
        absen = findViewById(R.id.in_status_absen);

        getSpinner(shift, status_shift);
        getSpinner(absen, status_absen);

    }

    private void getSpinner(AutoCompleteTextView target, String[] item) {
        ArrayAdapter<String> adapter= new ArrayAdapter<>(getApplicationContext(),R.layout.spinner_list_item,item);
        target.setAdapter(adapter);
    }
}