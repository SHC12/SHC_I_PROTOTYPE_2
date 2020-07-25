package com.mobile.spk.admin;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.mobile.spk.HomeActivity;
import com.mobile.spk.R;
import com.mobile.spk.adapter.TableAdapterDataAnggota;
import com.mobile.spk.api.ApiClient;
import com.mobile.spk.api.ApiInterface;

import com.mobile.spk.model.DataAnggota;

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

public class DataAnggotaActivity extends AppCompatActivity {
    private RecyclerView rv_data;
    private List<DataAnggota> listAnggota;
    private ApiInterface apiInterface;
    private TableAdapterDataAnggota adapterDataAnggota;
    private TableAdapterDataAnggota.RecyclerViewClickListener listener;
    Button exportPdf;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_anggota);
        apiInterface = ApiClient.getApiClient().create(ApiInterface.class);
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

        exportPdf = findViewById(R.id.btnExportAnggota);
        exportPdf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    ExportDataAnggota();
            }
        });
        initToolbar();
        getDataAnggota();
    }

    private void ExportDataAnggota() {
        String fileName = "Data Anggota.pdf";
        Call<ResponseBody> downloadDataAnggota = apiInterface.downloadDataAnggota();
        downloadDataAnggota.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if(response.isSuccessful()){
                    boolean suc = writeResponseBodyToDisk(response.body(),fileName);
                    if(suc){
                        Toast.makeText(DataAnggotaActivity.this, "PDF berhasil di download, file tersimpan di folder Downloads", Toast.LENGTH_SHORT).show();

                    }else{
                        Toast.makeText(DataAnggotaActivity.this, "Gagal simpan PDF", Toast.LENGTH_SHORT).show();
                    }

                }else{
                    Toast.makeText(DataAnggotaActivity.this, "error", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Toast.makeText(DataAnggotaActivity.this, ""+t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
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