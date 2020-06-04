package com.mobile.spk.danru;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.mobile.spk.R;
import com.mobile.spk.adapter.TableAdapterFormInputJadwalPersonal;
import com.mobile.spk.adapter.TableAdapterJadwalBulanUmum;
import com.mobile.spk.model.JadwalBulanUmum;
import com.mobile.spk.model.JadwalPersonalModel;

import java.util.ArrayList;
import java.util.List;

public class FormInputJadwal extends AppCompatActivity {
    private RecyclerView rv_input_jadwal_personal;
    private TableAdapterFormInputJadwalPersonal adapterInputpersonal;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_input_jadwal);


        rv_input_jadwal_personal = findViewById(R.id.recyclerViewInputJadwalPersonal);
        adapterInputpersonal = new TableAdapterFormInputJadwalPersonal(getApplicationContext(), getData());
        rv_input_jadwal_personal.setLayoutManager(new LinearLayoutManager(this));
        rv_input_jadwal_personal.setHasFixedSize(true);
        rv_input_jadwal_personal.setAdapter(adapterInputpersonal);
    }

    private List<JadwalPersonalModel> getData(){
        List<JadwalPersonalModel> jadwal = new ArrayList<>();
        jadwal.add(new JadwalPersonalModel("02-05-2020","0","Gedung A"));
        jadwal.add(new JadwalPersonalModel("05-05-2020","0","Gedung A"));
        jadwal.add(new JadwalPersonalModel("04-05-2020","0","Gedung A"));
        jadwal.add(new JadwalPersonalModel("03-05-2020","0","Gedung A"));

        return jadwal;
    }
}