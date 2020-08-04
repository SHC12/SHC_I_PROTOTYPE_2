package com.mobile.spk.anggota;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;
import com.mobile.spk.HomeActivity;
import com.mobile.spk.R;
import com.mobile.spk.RiwayatActivity;
import com.mobile.spk.adapter.TableAdapterPatroli;
import com.mobile.spk.api.ApiClient;
import com.mobile.spk.api.ApiInterface;
import com.mobile.spk.danru.DetailJadwal;
import com.mobile.spk.danru.JadwalByGedung;
import com.mobile.spk.model.Jadwal;
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

public class PatroliActivity extends AppCompatActivity {
    private SessionManager sessionManager;
    private ApiInterface apiInterface;
    private RecyclerView rv_patroli_petugas;
    private List<PatroliModel> listPatroli;

    private TableAdapterPatroli adapter;
    private TableAdapterPatroli.RecyclerViewClickListener listener;

    String id_user;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patroli);
        sessionManager = new SessionManager(this);
        HashMap<String, String> user = sessionManager.getUserDetail();
        apiInterface = ApiClient.getApiClient().create(ApiInterface.class);

        id_user = user.get(SessionManager.ID);

        getDataPatroli(id_user);

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

    public void toRiwayatPatroli(View view) {
        startActivity(new Intent(PatroliActivity.this, RiwayatPatroli.class));
    }

    private void getDataPatroli(String id_user) {
        listPatroli = new ArrayList<>();
        rv_patroli_petugas = findViewById(R.id.rv_patroli_petugas);
        listener = new TableAdapterPatroli.RecyclerViewClickListener() {
            @Override
            public void onRowClick(View view, int position) {
                Intent i = new Intent(PatroliActivity.this, FormGedung.class);
                i.putExtra(FormGedung.DETAIL_PATROLI, listPatroli.get(position - 1));
                startActivity(i);
            }
        };
        adapter = new TableAdapterPatroli(getApplicationContext(), listPatroli, listener);
        rv_patroli_petugas.setLayoutManager(new LinearLayoutManager(this));
        rv_patroli_petugas.setHasFixedSize(true);
        adapter.notifyDataSetChanged();
        rv_patroli_petugas.setAdapter(adapter);

        Call<List<PatroliModel>> getPatroli = apiInterface.getPatroliPetugas(id_user);

        getPatroli.enqueue(new Callback<List<PatroliModel>>() {
            @Override
            public void onResponse(Call<List<PatroliModel>> call, Response<List<PatroliModel>> response) {
                listPatroli = response.body();
                adapter = new TableAdapterPatroli(getApplicationContext(), listPatroli, listener);
                rv_patroli_petugas.setAdapter(adapter);
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<List<PatroliModel>> call, Throwable t) {
                Toast.makeText(PatroliActivity.this, ""+t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });



    }



}