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

import com.mobile.spk.R;
import com.mobile.spk.adapter.TableAdapterJadwalPetugas;
import com.mobile.spk.api.ApiClient;
import com.mobile.spk.api.ApiInterface;
import com.mobile.spk.model.Jadwal;
import com.mobile.spk.utils.SessionManager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class JadwalByPetugas extends AppCompatActivity {
    private ApiInterface apiInterface;
    private RecyclerView rv_jadwal_gedung;
    private List<Jadwal> listJadwal;
    private SessionManager sessionManager;
    private TableAdapterJadwalPetugas adapter;
    private TableAdapterJadwalPetugas.RecyclerViewClickListener listener;
    Button btnInputJadwal;
    int level;
    String nama_petugas, id_user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jadwal_by_petugas);
        btnInputJadwal = findViewById(R.id.btnInputJadwalPetugas);
        sessionManager = new SessionManager(this);
        HashMap<String, String> user = sessionManager.getUserDetail();
        apiInterface = ApiClient.getApiClient().create(ApiInterface.class);
        TextView title_petugas = (TextView) findViewById(R.id.title_petugas);
        nama_petugas = getIntent().getStringExtra("nama_petugas");
        id_user = getIntent().getStringExtra("id_user");

        title_petugas.setText(nama_petugas);

        level = Integer.parseInt(user.get(SessionManager.LEVEL));

        if(level != 2){
            btnInputJadwal.setVisibility(View.GONE);
        }
        getDataPetugas(id_user);
    }
    private void getDataPetugas(String id_users) {
        listJadwal = new ArrayList<>();
        rv_jadwal_gedung = findViewById(R.id.rv_jadwal_by_petugas);
        listener = new TableAdapterJadwalPetugas.RecyclerViewClickListener() {
            @Override
            public void onRowClick(View view, int position) {
                Intent i = new Intent(JadwalByPetugas.this, DetailJadwal.class);
                i.putExtra(DetailJadwal.DETAIL_JADWAL, listJadwal.get(position - 1));
                startActivity(i);
            }
        };
        adapter = new TableAdapterJadwalPetugas(getApplicationContext(), listJadwal, listener);
        rv_jadwal_gedung.setLayoutManager(new LinearLayoutManager(this));
        rv_jadwal_gedung.setHasFixedSize(true);
        rv_jadwal_gedung.setAdapter(adapter);

        Call<List<Jadwal>> getJadwalPetugas = apiInterface.getJadwalByPetugas(id_users);

        getJadwalPetugas.enqueue(new Callback<List<Jadwal>>() {
            @Override
            public void onResponse(Call<List<Jadwal>> call, Response<List<Jadwal>> response) {
                listJadwal = response.body();
                adapter = new TableAdapterJadwalPetugas(getApplicationContext(), listJadwal, listener);
                rv_jadwal_gedung.setAdapter(adapter);
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<List<Jadwal>> call, Throwable t) {
                Toast.makeText(JadwalByPetugas.this, ""+t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });



    }

    public void toInputJadwalDanru(View view) {
        startActivity(new Intent(JadwalByPetugas.this,FormInputJadwal.class));
    }
}