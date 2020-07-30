package com.mobile.spk.danru;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;
import com.mobile.spk.CutiActivity;
import com.mobile.spk.R;
import com.mobile.spk.RiwayatActivity;
import com.mobile.spk.adapter.TableAdapterAbsenUser;
import com.mobile.spk.adapter.TableAdapterJadwalGedung;
import com.mobile.spk.anggota.FormAbsensi;
import com.mobile.spk.anggota.JadwalActivityAnggota;
import com.mobile.spk.api.ApiClient;
import com.mobile.spk.api.ApiInterface;
import com.mobile.spk.model.AbsenUser;
import com.mobile.spk.model.Cuti;
import com.mobile.spk.model.Jadwal;
import com.mobile.spk.utils.SessionManager;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class JadwalByGedung extends AppCompatActivity {
    private ApiInterface apiInterface;
    private RecyclerView rv_jadwal_gedung;
    private List<Jadwal> listJadwal;
    private SessionManager sessionManager;
    private TableAdapterJadwalGedung adapter;
    private TableAdapterJadwalGedung.RecyclerViewClickListener listener;

    Button btnInputJadwal;

    String nama_gedung;
    int level;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jadwal_by_gedung2);

        btnInputJadwal = findViewById(R.id.btnInputJadwalPetugas);

        sessionManager = new SessionManager(this);
        HashMap<String, String> user = sessionManager.getUserDetail();
        apiInterface = ApiClient.getApiClient().create(ApiInterface.class);
        TextView title_gedung = (TextView) findViewById(R.id.title_gedung);
        nama_gedung = getIntent().getStringExtra("nama_gedung");
        title_gedung.setText(nama_gedung);

        level = Integer.parseInt(user.get(SessionManager.LEVEL));

        if(level != 2){
            btnInputJadwal.setVisibility(View.GONE);
        }



        getDataGedung(nama_gedung);


    }
    private void getDataGedung(String nama_gedung) {
        listJadwal = new ArrayList<>();
        rv_jadwal_gedung = findViewById(R.id.rv_jadwal_by_gedung);
        listener = new TableAdapterJadwalGedung.RecyclerViewClickListener() {
            @Override
            public void onRowClick(View view, int position) {
                Intent i = new Intent(JadwalByGedung.this, DetailJadwal.class);
                i.putExtra(DetailJadwal.DETAIL_JADWAL, listJadwal.get(position - 1));
                startActivity(i);
            }
        };
        adapter = new TableAdapterJadwalGedung(getApplicationContext(), listJadwal, listener);
        rv_jadwal_gedung.setLayoutManager(new LinearLayoutManager(this));
        rv_jadwal_gedung.setHasFixedSize(true);
        rv_jadwal_gedung.setAdapter(adapter);

        Call<List<Jadwal>> getJadwalGedung = apiInterface.getJadwalByGedung(nama_gedung);

        getJadwalGedung.enqueue(new Callback<List<Jadwal>>() {
            @Override
            public void onResponse(Call<List<Jadwal>> call, Response<List<Jadwal>> response) {
                listJadwal = response.body();
                adapter = new TableAdapterJadwalGedung(getApplicationContext(), listJadwal, listener);
                rv_jadwal_gedung.setAdapter(adapter);
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<List<Jadwal>> call, Throwable t) {
                Toast.makeText(JadwalByGedung.this, ""+t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });



    }

    public void toInputJadwalDanru(View view) {
        startActivity(new Intent(JadwalByGedung.this,FormInputJadwal.class));
    }
}