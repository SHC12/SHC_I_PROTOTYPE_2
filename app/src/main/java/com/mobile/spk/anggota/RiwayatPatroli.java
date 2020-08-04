package com.mobile.spk.anggota;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;
import com.mobile.spk.HomeActivity;
import com.mobile.spk.R;
import com.mobile.spk.adapter.TableAdapterPatroli;
import com.mobile.spk.adapter.TableAdapterRiwayatPatroli;
import com.mobile.spk.api.ApiClient;
import com.mobile.spk.api.ApiInterface;
import com.mobile.spk.danru.JadwalHariIniActivity;
import com.mobile.spk.model.PatroliModel;
import com.mobile.spk.model.Petugas;
import com.mobile.spk.model.RiwayatPatroliModel;
import com.mobile.spk.utils.SessionManager;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RiwayatPatroli extends AppCompatActivity {
    private SessionManager sessionManager;
    private ApiInterface apiInterface;
    private RecyclerView rv_patroli_petugas;
    private List<RiwayatPatroliModel> listPatroli;
    private ProgressDialog progressDialog;
    String getID_user;
    String namaSearch;
    private List<Petugas> dataList = new ArrayList<>();
    private ArrayList<String> petugasList = new ArrayList<>();
    private TableAdapterRiwayatPatroli adapter;
    private TableAdapterRiwayatPatroli.RecyclerViewClickListener listener;

    String id_user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_riwayat_patroli);

        sessionManager = new SessionManager(this);
        HashMap<String, String> user = sessionManager.getUserDetail();
        apiInterface = ApiClient.getApiClient().create(ApiInterface.class);

        id_user = user.get(SessionManager.ID);
        progressDialog = new ProgressDialog(this);
        LinearLayout viewPatroliDanru = findViewById(R.id.riwayatPatroliDanru);
        if(user.get(SessionManager.LEVEL).equals("1")){
            viewPatroliDanru.setVisibility(View.GONE);
        }
        AutoCompleteTextView petugas = (AutoCompleteTextView) findViewById(R.id.in_patroli_pilih_petugas);
        petugas.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                getID_user = dataList.get(position).getId_user();
                namaSearch = dataList.get(position).getNama_lengkap();

            }
        });
        MaterialButton btnCheckRiwayat =  (MaterialButton) findViewById(R.id.btnRiwayatPatroliPetugas);
        btnCheckRiwayat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getRiwayatPatroli(getID_user);

            }
        });
        getPetugas(petugas);
        getRiwayatPatroli(id_user);

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

    private void getRiwayatPatroli(String id_user) {
        listPatroli = new ArrayList<>();
        rv_patroli_petugas = findViewById(R.id.rv_riwayat_patroli_petugas);
        listener = new TableAdapterRiwayatPatroli.RecyclerViewClickListener() {
            @Override
            public void onRowClick(View view, int position) {
                Intent i = new Intent(RiwayatPatroli.this, DetailRiwayatPatroli.class);
                i.putExtra(DetailRiwayatPatroli.DETAIL_PATROLI, listPatroli.get(position - 1));
                startActivity(i);
            }
        };
        adapter = new TableAdapterRiwayatPatroli(getApplicationContext(), listPatroli, listener);
        rv_patroli_petugas.setLayoutManager(new LinearLayoutManager(this));
        rv_patroli_petugas.setHasFixedSize(true);
        rv_patroli_petugas.setAdapter(adapter);
        adapter.notifyDataSetChanged();


        Call<List<RiwayatPatroliModel>> getPatroli = apiInterface.getRiwayatPatroliPetugas(id_user);

        getPatroli.enqueue(new Callback<List<RiwayatPatroliModel>>() {
            @Override
            public void onResponse(Call<List<RiwayatPatroliModel>> call, Response<List<RiwayatPatroliModel>> response) {
                listPatroli = response.body();
                adapter = new TableAdapterRiwayatPatroli(getApplicationContext(), listPatroli, listener);
                rv_patroli_petugas.setAdapter(adapter);
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<List<RiwayatPatroliModel>> call, Throwable t) {
                Toast.makeText(RiwayatPatroli.this, ""+t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void getSpinnerAPI(AutoCompleteTextView target, ArrayList<String> item) {
        ArrayAdapter<String> adapter = new ArrayAdapter<>(getApplicationContext(), R.layout.spinner_list_item, item);
        target.setAdapter(adapter);
    }

    private void getPetugas(AutoCompleteTextView target){
        progressDialog.show();
        progressDialog.setMessage("Loading...");
        Call<ResponseBody> getPetugas = apiInterface.getPetugas();
        getPetugas.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if(response.isSuccessful()){
                    progressDialog.dismiss();
                    try {
                        JSONObject o = new JSONObject(response.body().string());
                        JSONArray a = o.getJSONArray("petugas");
                        for(int i = 0; i<a.length();i++){


                            JSONObject ao = a.getJSONObject(i);


                            petugasList.add(ao.getString("nama_lengkap"));
                            dataList.add(new Petugas(ao.getString("id_user"), ao.getString("nama_lengkap")));
                        }
                        getSpinnerAPI(target,petugasList);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

            }
        });
    }


    public void ExportRiwayatPatroli(View view) {
        String fileName = "Export Riwayat Patroli.pdf";
        Call<ResponseBody> downloadAbsen = apiInterface.downloadRiwayatPatroliPetugas(id_user);
        downloadAbsen.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if(response.isSuccessful()){
                    boolean suc = writeResponseBodyToDisk(response.body(),fileName);
                    if(suc){
                        Toast.makeText(RiwayatPatroli.this, "Rekapan berhasil di download, file tersimpan di folder Downloads", Toast.LENGTH_SHORT).show();

                    }else{
                        Toast.makeText(RiwayatPatroli.this, "Gagal simpan PDF", Toast.LENGTH_SHORT).show();
                    }

                }else{
                    Toast.makeText(RiwayatPatroli.this, "error", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Toast.makeText(RiwayatPatroli.this, ""+t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private boolean writeResponseBodyToDisk(ResponseBody body,String path) {
        try {
            // todo change the file location/name according to your needs
            File futureStudioIconFile = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS),
                    path);

            InputStream inputStream = null;
            OutputStream outputStream = null;

            try {
                byte[] fileReader = new byte[4096];

                long fileSize = body.contentLength();
                long fileSizeDownloaded = 0;

                inputStream = body.byteStream();
                outputStream = new FileOutputStream(futureStudioIconFile);

                while (true) {
                    int read = inputStream.read(fileReader);

                    if (read == -1) {
                        break;
                    }

                    outputStream.write(fileReader, 0, read);

                    fileSizeDownloaded += read;

                }

                outputStream.flush();

                return true;
            } catch (IOException e) {
                return false;
            } finally {
                if (inputStream != null) {
                    inputStream.close();
                }

                if (outputStream != null) {
                    outputStream.close();
                }
            }
        } catch (IOException e) {
            return false;
        }
    }
}