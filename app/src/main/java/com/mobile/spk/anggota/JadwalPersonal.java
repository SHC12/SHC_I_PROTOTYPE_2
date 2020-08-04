package com.mobile.spk.anggota;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.mobile.spk.DetailAbsen;
import com.mobile.spk.HomeActivity;
import com.mobile.spk.R;
import com.mobile.spk.adapter.TableAdapterAbsenUser;
import com.mobile.spk.adapter.TableAdapterJadwalPetugas;
import com.mobile.spk.adapter.TableAdapterJadwalTahunUser;
import com.mobile.spk.api.ApiClient;
import com.mobile.spk.api.ApiInterface;
import com.mobile.spk.danru.DetailJadwal;
import com.mobile.spk.model.AbsenUser;
import com.mobile.spk.model.Jadwal;
import com.mobile.spk.model.JadwalTahunUser;
import com.mobile.spk.utils.SessionManager;

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

public class JadwalPersonal extends AppCompatActivity {
    private RecyclerView rv_jadwal_personal;
    private TableAdapterJadwalPetugas adapterJadwalTahunUser;
    private TableAdapterJadwalPetugas.RecyclerViewClickListener listener;
    private List<Jadwal> listJadwalPersonal = new ArrayList<>();
    SessionManager sessionManager;
    String id_user;
    private ApiInterface apiInterface;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jadwal_personal);

        apiInterface = ApiClient.getApiClient().create(ApiInterface.class);

        sessionManager = new SessionManager(this);
        HashMap<String,String> user = sessionManager.getUserDetail();
        rv_jadwal_personal = findViewById(R.id.rv_jadwal_personal_anggota);
        listener = new TableAdapterJadwalPetugas.RecyclerViewClickListener() {
            @Override
            public void onRowClick(View view, int position) {
                Intent i = new Intent(JadwalPersonal.this, DetailJadwal.class);
                i.putExtra(DetailJadwal.DETAIL_JADWAL,listJadwalPersonal.get(position-1));
                startActivity(i);
            }
        };
        id_user = user.get(SessionManager.ID);
        adapterJadwalTahunUser = new TableAdapterJadwalPetugas(getApplicationContext(), listJadwalPersonal, listener);
        rv_jadwal_personal.setLayoutManager(new LinearLayoutManager(this));
        rv_jadwal_personal.setHasFixedSize(true);
        rv_jadwal_personal.setAdapter(adapterJadwalTahunUser);

        TextView nama = (TextView) findViewById(R.id.textView13);
        nama.setText(user.get(SessionManager.NAMA));

        getJadwalPersonal(user.get(SessionManager.ID));

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

    private void getJadwalPersonal(String s) {
        ApiInterface apiInterface = ApiClient.getApiClient().create(ApiInterface.class);
        Call<List<Jadwal>> getJadwalPersonalList = apiInterface.getJadwalPersonal(s);
        getJadwalPersonalList.enqueue(new Callback<List<Jadwal>>() {
            @Override
            public void onResponse(Call<List<Jadwal>> call, Response<List<Jadwal>> response) {
                listJadwalPersonal = response.body();
                adapterJadwalTahunUser = new TableAdapterJadwalPetugas(getApplicationContext(), listJadwalPersonal, listener);
                rv_jadwal_personal.setAdapter(adapterJadwalTahunUser);
                adapterJadwalTahunUser.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<List<Jadwal>> call, Throwable t) {

            }
        });
    }




    public void ExportJadwalPersonal(View view) {
        String fileName = "Export Jadwal Personal.pdf";
        Call<ResponseBody> downloadAbsen = apiInterface.downloadJadwalPersonal(id_user);
        downloadAbsen.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if(response.isSuccessful()){
                    boolean suc = writeResponseBodyToDisk(response.body(),fileName);
                    if(suc){
                        Toast.makeText(JadwalPersonal.this, "Rekapan berhasil di download, file tersimpan di folder Downloads", Toast.LENGTH_SHORT).show();

                    }else{
                        Toast.makeText(JadwalPersonal.this, "Gagal simpan PDF", Toast.LENGTH_SHORT).show();
                    }

                }else{
                    Toast.makeText(JadwalPersonal.this, "error", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Toast.makeText(JadwalPersonal.this, ""+t.getMessage(), Toast.LENGTH_SHORT).show();
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