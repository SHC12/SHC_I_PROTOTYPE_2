package com.mobile.spk;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.button.MaterialButton;
import com.mobile.spk.adapter.TableAdapterCutiDanru;
import com.mobile.spk.adapter.TableAdapterPengajuanCuti;
import com.mobile.spk.anggota.FormAjukanCuti;
import com.mobile.spk.api.ApiClient;
import com.mobile.spk.api.ApiInterface;
import com.mobile.spk.model.Cuti;
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

public class CutiActivity extends AppCompatActivity {
    private ApiInterface apiInterface;
    private SessionManager sessionManager;
    private RecyclerView rv_cuti;
    private RecyclerView rv_nonUser;
    private List<Cuti> listCuti;
    private List<Cuti> listCutiNonUser;
    private TableAdapterPengajuanCuti adapterCuti;
    private TableAdapterPengajuanCuti.RecyclerViewClickListener listener;

    private TableAdapterCutiDanru adapterCutiDanru;
    private TableAdapterCutiDanru.RecyclerViewClickListener listener2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cuti);

        sessionManager = new SessionManager(this);
        HashMap<String, String> user = sessionManager.getUserDetail();
        apiInterface = ApiClient.getApiClient().create(ApiInterface.class);
        String level = user.get(SessionManager.LEVEL);
        String id = user.get(SessionManager.ID);
        getView(level,id);
        initToolbar();

    }

    private void getDataCutiUser(String id) {
        TextView batasCuti = (TextView) findViewById(R.id.batas_cuti_user);
        TextView cutiDiambil = (TextView) findViewById(R.id.cuti_diambil_user);
        TextView cutiSisa = (TextView) findViewById(R.id.sisa_cuti_user);

        Call<ResponseBody> getDataCuti = apiInterface.dataCuti(id);
        getDataCuti.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if(response.isSuccessful()){
                    try {
                        JSONObject o = new JSONObject(response.body().string());
                        if(o.getString("status").equals("1")){
                            String bc = o.getString("batas_cuti");
                            String ca = o.getString("cuti_diambil");
                            String sc = o.getString("sisa_cuti");
                            batasCuti.setText(bc);
                            cutiDiambil.setText(ca);
                            cutiSisa.setText(sc);
                        }else{
                            Toast.makeText(CutiActivity.this, "Terjadi kesalahan", Toast.LENGTH_SHORT).show();
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }else{
                    Toast.makeText(CutiActivity.this, "Koneksi Bermasalah", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Toast.makeText(CutiActivity.this, ""+t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void getView(String level,String id) {
        LinearLayout viewCutiAnggota = (LinearLayout) findViewById(R.id.view_cuti_anggota);
        LinearLayout viewCutiDanru = (LinearLayout) findViewById(R.id.view_cuti_danru);
        MaterialButton btnAjukanCutiDanru = (MaterialButton) findViewById(R.id.btnAjukanCutiAdmin);
        if(level.equals("1")){
            viewCutiAnggota.setVisibility(View.VISIBLE);
            getDataCutiUser(id);
            getRiwayatCutiUser(id);
        }else if(level.equals("2")){
            viewCutiDanru.setVisibility(View.VISIBLE);
            getDataCuti("All");
        }else if(level.equals("3")){
            viewCutiDanru.setVisibility(View.VISIBLE);
            btnAjukanCutiDanru.setVisibility(View.GONE);
            getDataCuti("All");
        }


    }


    private void getDataCuti(String id) {
        listCutiNonUser = new ArrayList<>();
        rv_nonUser = findViewById(R.id.rv_cuti_danru);
        listener2 = new TableAdapterCutiDanru.RecyclerViewClickListener() {
            @Override
            public void onRowClick(View view, int position) {
                Intent i = new Intent(CutiActivity.this, DetailCuti.class);
                i.putExtra(DetailCuti.DETAIL_CUTI,listCutiNonUser.get(position-1));
                startActivity(i);
            }
        };
        adapterCutiDanru = new TableAdapterCutiDanru(getApplicationContext(), listCutiNonUser, listener2);
        rv_nonUser.setLayoutManager(new LinearLayoutManager(this));
        rv_nonUser.setHasFixedSize(true);
        rv_nonUser.setAdapter(adapterCutiDanru);

        Call<List<Cuti>> getRiwayatCutiNonUser = apiInterface.riwayatCuti(id);
        getRiwayatCutiNonUser.enqueue(new Callback<List<Cuti>>() {
            @Override
            public void onResponse(Call<List<Cuti>> call, Response<List<Cuti>> response) {
                listCutiNonUser = response.body();
                adapterCutiDanru = new TableAdapterCutiDanru(getApplicationContext(), listCutiNonUser, listener2);
                rv_nonUser.setAdapter(adapterCutiDanru);
                adapterCutiDanru.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<List<Cuti>> call, Throwable t) {
                Toast.makeText(CutiActivity.this, ""+t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });


    }

    private void initToolbar() {
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),HomeActivity.class));
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
    private void getRiwayatCutiUser(String id) {
        listCuti = new ArrayList<>();
        rv_cuti = findViewById(R.id.rv_riwayat_pengajuan_cuti);
        listener = new TableAdapterPengajuanCuti.RecyclerViewClickListener() {
            @Override
            public void onRowClick(View view, int position) {
                Intent i = new Intent(CutiActivity.this,DetailCuti.class);
                i.putExtra(DetailCuti.DETAIL_CUTI,listCuti.get(position-1));
                startActivity(i);
            }
        };
        adapterCuti = new TableAdapterPengajuanCuti(getApplicationContext(), listCuti, listener);
        rv_cuti.setLayoutManager(new LinearLayoutManager(this));
        rv_cuti.setHasFixedSize(true);
        rv_cuti.setAdapter(adapterCuti);

        Call<List<Cuti>> getRiwayatCutiUser = apiInterface.riwayatCuti(id);
        getRiwayatCutiUser.enqueue(new Callback<List<Cuti>>() {
            @Override
            public void onResponse(Call<List<Cuti>> call, Response<List<Cuti>> response) {
                listCuti = response.body();
                adapterCuti = new TableAdapterPengajuanCuti(getApplicationContext(), listCuti, listener);
                rv_cuti.setAdapter(adapterCuti);
                adapterCuti.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<List<Cuti>> call, Throwable t) {
                Toast.makeText(CutiActivity.this, ""+t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }

    public void toFormCuti(View view) {
        startActivity(new Intent(CutiActivity.this, FormAjukanCuti.class));
    }


}