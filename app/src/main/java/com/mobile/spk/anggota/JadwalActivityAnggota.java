package com.mobile.spk.anggota;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.button.MaterialButton;
import com.mobile.spk.CutiActivity;
import com.mobile.spk.DetailCuti;
import com.mobile.spk.R;
import com.mobile.spk.RiwayatActivity;
import com.mobile.spk.adapter.TableAdapterAbsenUser;
import com.mobile.spk.adapter.TableAdapterCutiDanru;
import com.mobile.spk.api.ApiClient;
import com.mobile.spk.api.ApiInterface;
import com.mobile.spk.model.AbsenUser;
import com.mobile.spk.model.Cuti;
import com.mobile.spk.model.PatroliModel;
import com.mobile.spk.model.Petugas;
import com.mobile.spk.utils.SessionManager;

import org.json.JSONArray;
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

public class JadwalActivityAnggota extends AppCompatActivity {
    private SessionManager sessionManager;
    private List<AbsenUser> listJadwal;
    private RecyclerView rvRiwayatAbsenUser;
    private ApiInterface apiInterface;
    private RecyclerView rvRiwayatPatroliUser;
    private String triggerAbsen = "jadwal";

    private TableAdapterAbsenUser adapterAbsenUser;
    private TableAdapterAbsenUser.RecyclerViewClickListener listener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jadwal_anggota);
        sessionManager = new SessionManager(this);



        apiInterface = ApiClient.getApiClient().create(ApiInterface.class);
        HashMap<String, String> user = sessionManager.getUserDetail();
        String idUser = user.get(SessionManager.ID);
        LinearLayout viewAbsen = findViewById(R.id.view_absen_petugas);

        getDataAbsen(idUser);


    }

    private void getDataAbsen(String id) {
        listJadwal = new ArrayList<>();
        rvRiwayatAbsenUser = findViewById(R.id.rv_jadwal_absen_user);
        listener = new TableAdapterAbsenUser.RecyclerViewClickListener() {
            @Override
            public void onRowClick(View view, int position) {
                Intent i = new Intent(JadwalActivityAnggota.this, FormAbsensi.class);
                i.putExtra(FormAbsensi.DETAIL_JADWAL, listJadwal.get(position - 1));
                startActivity(i);
            }
        };
        adapterAbsenUser = new TableAdapterAbsenUser(getApplicationContext(), listJadwal, listener);
        rvRiwayatAbsenUser.setLayoutManager(new LinearLayoutManager(this));
        rvRiwayatAbsenUser.setHasFixedSize(true);
        adapterAbsenUser.notifyDataSetChanged();
        rvRiwayatAbsenUser.setAdapter(adapterAbsenUser);

        Call<List<AbsenUser>> getAbsenUser = apiInterface.jadwalUser(id);
        getAbsenUser.enqueue(new Callback<List<AbsenUser>>() {
            @Override
            public void onResponse(Call<List<AbsenUser>> call, Response<List<AbsenUser>> response) {
                listJadwal = response.body();
                adapterAbsenUser = new TableAdapterAbsenUser(getApplicationContext(), listJadwal, listener);
                rvRiwayatAbsenUser.setAdapter(adapterAbsenUser);
                adapterAbsenUser.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<List<AbsenUser>> call, Throwable t) {
                Toast.makeText(JadwalActivityAnggota.this, ""+t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

        MaterialButton btnRiwayat = findViewById(R.id.btnRiwayatAbsen);
        btnRiwayat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(JadwalActivityAnggota.this, RiwayatActivity.class).putExtra("triggerRiwayat", "absen"));
            }
        });


    }









}