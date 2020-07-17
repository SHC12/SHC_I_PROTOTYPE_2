package com.mobile.spk.admin;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;
import com.mobile.spk.HomeActivity;
import com.mobile.spk.R;
import com.mobile.spk.adapter.TableAdapterMitra;
import com.mobile.spk.api.ApiClient;
import com.mobile.spk.api.ApiInterface;
import com.mobile.spk.model.DataAnggota;
import com.mobile.spk.model.Mitra;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MitraActivity extends AppCompatActivity {
    private RecyclerView rv_data;
    private TableAdapterMitra adapterMitra;
    private ApiInterface apiInterface;
    private List<Mitra> listMitra;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mitra);

        listMitra = new ArrayList<>();
        apiInterface = ApiClient.getApiClient().create(ApiInterface.class);
        rv_data = findViewById(R.id.rv_data_mitra);
        adapterMitra = new TableAdapterMitra(getApplicationContext(), listMitra);
        rv_data.setLayoutManager(new LinearLayoutManager(this));
        rv_data.setHasFixedSize(true);
        rv_data.setAdapter(adapterMitra);
        getMitra();
        initTambahMitra();
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
    private void initTambahMitra() {
        EditText namaMitra = (EditText) findViewById(R.id.in_nama_mitra);
        MaterialButton btnTambahMitra = (MaterialButton) findViewById(R.id.btnTambahMitra);
        btnTambahMitra.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nama = namaMitra.getText().toString();
                if(nama.equals("")){
                    Toast.makeText(MitraActivity.this, "Nama mitra tidak boleh kosong", Toast.LENGTH_SHORT).show();
                }else{
                    tambahMitra(nama);
                }
            }
        });
    }

    private void tambahMitra(String nama) {
        Call<ResponseBody> tambahMitra = apiInterface.tambahMitra(nama);
        tambahMitra.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if(response.isSuccessful()){
                    try {
                        JSONObject o = new JSONObject(response.body().string());
                        if(o.getString("status").equals("1")){
                            Toast.makeText(MitraActivity.this, "Mitra berhasil di tambahkan", Toast.LENGTH_SHORT).show();
                            getMitra();
                            adapterMitra.notifyDataSetChanged();
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Toast.makeText(MitraActivity.this, ""+t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void getMitra() {
        ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.show();
        progressDialog.setMessage("Loading..");
        Call<List<Mitra>> getMitra = apiInterface.dataMitra();
        getMitra.enqueue(new Callback<List<Mitra>>() {
            @Override
            public void onResponse(Call<List<Mitra>> call, Response<List<Mitra>> response) {
                progressDialog.dismiss();
                listMitra = response.body();
                adapterMitra = new TableAdapterMitra(getApplicationContext(), listMitra);
                rv_data.setAdapter(adapterMitra);
                adapterMitra.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<List<Mitra>> call, Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(MitraActivity.this, ""+t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }



}