package com.mobile.spk;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.mobile.spk.adapter.TableAdapterInformasi;
import com.mobile.spk.anggota.Detailinformasi;
import com.mobile.spk.anggota.FormInformasi;
import com.mobile.spk.api.ApiClient;
import com.mobile.spk.api.ApiInterface;
import com.mobile.spk.model.Informasi;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class InformasiActivity extends AppCompatActivity {
    private RecyclerView rv_informaasi;
    private List<Informasi> listInformasi;
    private TableAdapterInformasi adapterInformasi;
    private TableAdapterInformasi.RecyclerViewClickListener listener;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_informasi);

        listInformasi = new ArrayList<>();
        rv_informaasi = findViewById(R.id.rv_info);
        listener = new TableAdapterInformasi.RecyclerViewClickListener() {
            @Override
            public void onRowClick(View view, int position) {
                Intent i = new Intent(InformasiActivity.this, Detailinformasi.class);
                i.putExtra(Detailinformasi.DETAIL_INFORMASI,listInformasi.get(position-1));
                startActivity(i);
            }
        };

        adapterInformasi = new TableAdapterInformasi(getApplicationContext(), listInformasi, listener);
        rv_informaasi.setLayoutManager(new LinearLayoutManager(this));
        rv_informaasi.setHasFixedSize(true);
        rv_informaasi.setAdapter(adapterInformasi);
        
        getInformasi();
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

    private void getInformasi() {
        ApiInterface apiInterface = ApiClient.getApiClient().create(ApiInterface.class);
        Call<List<Informasi>> getInformasi = apiInterface.dataInformasi();
        getInformasi.enqueue(new Callback<List<Informasi>>() {
            @Override
            public void onResponse(Call<List<Informasi>> call, Response<List<Informasi>> response) {
                listInformasi = response.body();
                adapterInformasi = new TableAdapterInformasi(getApplicationContext(), listInformasi, listener);
                rv_informaasi.setAdapter(adapterInformasi);
                adapterInformasi.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<List<Informasi>> call, Throwable t) {
                Toast.makeText(InformasiActivity.this, ""+t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

//    private List<Informasi> getDataInformasi(){
//        List<Informasi> data = new ArrayList<>();
//        data.add(new Informasi("02-05-2020","Informasi PSBB"));
//        data.add(new Informasi("02-04-2020","Perubahan Struktur Danru"));
//        data.add(new Informasi("02-04-2020","Perubahan Struktur Danru"));
//
//
//        return data;
//    }

    public void toFormInformasi(View view) {
        startActivity(new Intent(InformasiActivity.this, FormInformasi.class));
    }
}