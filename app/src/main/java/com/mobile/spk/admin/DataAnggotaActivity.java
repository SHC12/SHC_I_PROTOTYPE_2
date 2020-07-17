package com.mobile.spk.admin;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.mobile.spk.HomeActivity;
import com.mobile.spk.R;
import com.mobile.spk.adapter.TableAdapterDataAnggota;
import com.mobile.spk.api.ApiClient;
import com.mobile.spk.api.ApiInterface;
import com.mobile.spk.model.DataAnggota;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DataAnggotaActivity extends AppCompatActivity {
    private RecyclerView rv_data;
    private List<DataAnggota> listAnggota;
    private TableAdapterDataAnggota adapterDataAnggota;
    private TableAdapterDataAnggota.RecyclerViewClickListener listener;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_anggota);

        listAnggota = new ArrayList<>();
        rv_data = findViewById(R.id.rv_data_anggota);
        listener = new TableAdapterDataAnggota.RecyclerViewClickListener() {
            @Override
            public void onRowClick(View view, int position) {
                Intent i = new Intent(DataAnggotaActivity.this, DetailDataAnggota.class);
                i.putExtra(DetailDataAnggota.DETAIL_ANGGOTA,listAnggota.get(position-1));
                startActivity(i);
            }
        };
        adapterDataAnggota = new TableAdapterDataAnggota(getApplicationContext(), listAnggota,listener);
        rv_data.setLayoutManager(new LinearLayoutManager(this));
        rv_data.setHasFixedSize(true);
        rv_data.setAdapter(adapterDataAnggota);
        adapterDataAnggota.notifyDataSetChanged();
        initToolbar();
        getDataAnggota();
    }

    private void getDataAnggota() {
        ApiInterface apiInterface = ApiClient.getApiClient().create(ApiInterface.class);
        Call<List<DataAnggota>> getAnggota = apiInterface.dataAnggota();
        getAnggota.enqueue(new Callback<List<DataAnggota>>() {
            @Override
            public void onResponse(Call<List<DataAnggota>> call, Response<List<DataAnggota>> response) {
                if(response.isSuccessful()){
                    listAnggota = response.body();
                    adapterDataAnggota = new TableAdapterDataAnggota(getApplicationContext(),listAnggota,listener);
                    rv_data.setAdapter(adapterDataAnggota);
                    adapterDataAnggota.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(Call<List<DataAnggota>> call, Throwable t) {
                Toast.makeText(DataAnggotaActivity.this, ""+t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
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

}