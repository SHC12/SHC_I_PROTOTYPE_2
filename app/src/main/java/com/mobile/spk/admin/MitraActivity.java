package com.mobile.spk.admin;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;
import com.mobile.spk.HomeActivity;
import com.mobile.spk.R;
import com.mobile.spk.adapter.TableAdapterMitra;
import com.mobile.spk.anggota.JadwalPersonal;
import com.mobile.spk.api.ApiClient;
import com.mobile.spk.api.ApiInterface;
import com.mobile.spk.danru.DetailJadwal;
import com.mobile.spk.danru.JadwalHariIniActivity;
import com.mobile.spk.model.DataAnggota;
import com.mobile.spk.model.Mitra;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MitraActivity extends AppCompatActivity {
    private RecyclerView rv_data;
    private TableAdapterMitra adapterMitra;
    private TableAdapterMitra.RecyclerViewClickListener listener;
    private ApiInterface apiInterface;
    private List<Mitra> listMitra;
    private String idMitra;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mitra);
        EditText namaMitra = (EditText) findViewById(R.id.in_nama_mitra);
        MaterialButton btnTambahMitra = (MaterialButton) findViewById(R.id.btnTambahMitra);
        MaterialButton btnHapusMitra = (MaterialButton) findViewById(R.id.btnHapusMitra);
        MaterialButton btnExportMitra = (MaterialButton) findViewById(R.id.btnExportMitra);
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
        listMitra = new ArrayList<>();
        apiInterface = ApiClient.getApiClient().create(ApiInterface.class);
        rv_data = findViewById(R.id.rv_data_mitra);
        listener = new TableAdapterMitra.RecyclerViewClickListener() {
            @Override
            public void onRowClick(View view, int position) {
                namaMitra.setText(listMitra.get(position-1).getNama());
                idMitra = listMitra.get(position-1).getId_mitra();
            }
        };
        adapterMitra = new TableAdapterMitra(getApplicationContext(), listMitra,listener);
        rv_data.setLayoutManager(new LinearLayoutManager(this));
        rv_data.setHasFixedSize(true);
        rv_data.setAdapter(adapterMitra);
        adapterMitra.notifyDataSetChanged();
        getMitra();
        btnHapusMitra.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hapusMitra(idMitra);

             //   Toast.makeText(MitraActivity.this, ""+idMitra, Toast.LENGTH_SHORT).show();
                adapterMitra.notifyDataSetChanged();
            }
        });
        initToolbar();
    }

    private void hapusMitra(String idMitra) {
        ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.show();
        progressDialog.setMessage("Loading...");
        ApiInterface apiInterface = ApiClient.getApiClient().create(ApiInterface.class);
        Call<ResponseBody> hapusMitra = apiInterface.hapusMitra(idMitra);
        hapusMitra.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                progressDialog.dismiss();
                if(response.isSuccessful()){
                    try {
                        JSONObject o = new JSONObject(response.body().string());
                        if(o.getString("status").equals("1")){
                            Toast.makeText(MitraActivity.this, "Mitra berhasil di hapus", Toast.LENGTH_SHORT).show();
                            getMitra();
                        }else{
                            Toast.makeText(MitraActivity.this, "Terjadi kesalahan internal", Toast.LENGTH_SHORT).show();
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }else{
                    Toast.makeText(MitraActivity.this, "Koneksi bermasalah", Toast.LENGTH_SHORT).show();

                }

            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Toast.makeText(MitraActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();

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
                adapterMitra = new TableAdapterMitra(getApplicationContext(), listMitra,listener);
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




    public void ExportDataMitra(View view) {
        String fileName = "Export Data Mitra.pdf";
        Call<ResponseBody> downloadAbsen = apiInterface.downloadDataMitra();
        downloadAbsen.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if(response.isSuccessful()){
                    boolean suc = writeResponseBodyToDisk(response.body(),fileName);
                    if(suc){
                        Toast.makeText(MitraActivity.this, "Rekapan berhasil di download, file tersimpan di folder Downloads", Toast.LENGTH_SHORT).show();

                    }else{
                        Toast.makeText(MitraActivity.this, "Gagal simpan PDF", Toast.LENGTH_SHORT).show();
                    }

                }else{
                    Toast.makeText(MitraActivity.this, "error", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Toast.makeText(MitraActivity.this, ""+t.getMessage(), Toast.LENGTH_SHORT).show();
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