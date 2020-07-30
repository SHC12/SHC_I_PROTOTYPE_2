package com.mobile.spk;

import androidx.appcompat.app.AppCompatActivity;
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
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;
import com.mobile.spk.adapter.TableAdapterAbsenUser;
import com.mobile.spk.anggota.FormAbsensi;
import com.mobile.spk.anggota.JadwalActivityAnggota;
import com.mobile.spk.api.ApiClient;
import com.mobile.spk.api.ApiInterface;
import com.mobile.spk.danru.JadwalHariIniActivity;
import com.mobile.spk.model.AbsenUser;
import com.mobile.spk.model.Petugas;
import com.mobile.spk.utils.SessionManager;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

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

public class RiwayatActivity extends AppCompatActivity {

    private SessionManager sessionManager;
    private List<AbsenUser> listJadwal;
    private RecyclerView rvRiwayatAbsenUser;
    private ApiInterface apiInterface;
    private ProgressDialog progressDialog;
    String getID_user;
    String namaSearch;
    private List<Petugas> dataList = new ArrayList<>();
    private ArrayList<String> petugasList = new ArrayList<>();
    private TableAdapterAbsenUser adapterAbsenUser;
    private TableAdapterAbsenUser.RecyclerViewClickListener listener;
    String idUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_riwayat);
        sessionManager = new SessionManager(this);
        apiInterface = ApiClient.getApiClient().create(ApiInterface.class);
        progressDialog = new ProgressDialog(this);
        LinearLayout viewAbsen = findViewById(R.id.view_riwayat_absen_petugas);

        HashMap<String, String> user = sessionManager.getUserDetail();
        idUser = user.get(SessionManager.ID);
        viewAbsen.setVisibility(View.VISIBLE);
        rvRiwayatAbsenUser = findViewById(R.id.rv_riwayat_absen_user);
        TextView namaPetugas = (TextView) findViewById(R.id.subtitleNamaRiwayat);
        namaPetugas.setText(user.get(SessionManager.NAMA));

        LinearLayout viewAbsenDanru = findViewById(R.id.riwayatAbsenDanru);
        if(user.get(SessionManager.LEVEL).equals("1")){
            viewAbsenDanru.setVisibility(View.GONE);
        }
        rvRiwayatAbsenUser = findViewById(R.id.rv_jadwal_absen_user);
        viewAbsen.setVisibility(View.VISIBLE);
        AutoCompleteTextView petugas = (AutoCompleteTextView) findViewById(R.id.in_absen_pilih_petugas);
        petugas.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                getID_user = dataList.get(position).getId_user();
                namaSearch = dataList.get(position).getNama_lengkap();

            }
        });

        MaterialButton btnCheckRiwayat =  (MaterialButton) findViewById(R.id.btnRiwayatAbsenPetugas);
        btnCheckRiwayat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getRiwayatAbsen(getID_user);
                namaPetugas.setText(namaSearch);

            }
        });
        getPetugas(petugas);
        getRiwayatAbsen(idUser);


    }

    private void getRiwayatAbsen(String id) {
        listJadwal = new ArrayList<>();
        rvRiwayatAbsenUser = findViewById(R.id.rv_riwayat_absen_user);
        listener = new TableAdapterAbsenUser.RecyclerViewClickListener() {
            @Override
            public void onRowClick(View view, int position) {
                Intent i = new Intent(RiwayatActivity.this, DetailAbsen.class);
                i.putExtra(DetailAbsen.DETAIL_JADWAL, listJadwal.get(position - 1));
                startActivity(i);
            }
        };
        adapterAbsenUser = new TableAdapterAbsenUser(getApplicationContext(), listJadwal, listener);
        rvRiwayatAbsenUser.setLayoutManager(new LinearLayoutManager(this));
        rvRiwayatAbsenUser.setHasFixedSize(true);
        rvRiwayatAbsenUser.setAdapter(adapterAbsenUser);

        Call<List<AbsenUser>> getRiwayatAbsen = apiInterface.riwayatAbsen(id);
        getRiwayatAbsen.enqueue(new Callback<List<AbsenUser>>() {
            @Override
            public void onResponse(Call<List<AbsenUser>> call, Response<List<AbsenUser>> response) {
                listJadwal = response.body();
                adapterAbsenUser = new TableAdapterAbsenUser(getApplicationContext(), listJadwal, listener);
                rvRiwayatAbsenUser.setAdapter(adapterAbsenUser);
                adapterAbsenUser.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<List<AbsenUser>> call, Throwable t) {
                Toast.makeText(RiwayatActivity.this, ""+t.getMessage(), Toast.LENGTH_SHORT).show();
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

    public void ExportRiwayatAbsen(View view) {

        ExportAbsenRiwayat(idUser);
    }

    public void ExportAbsenRiwayat(String id_user) {
        String fileName = "Export Riwayat Absen Petugas.pdf";
        Call<ResponseBody> downloadAbsen = apiInterface.downloadRiwayatAbsenPetugas(id_user);
        downloadAbsen.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if(response.isSuccessful()){
                    boolean suc = writeResponseBodyToDisk(response.body(),fileName);
                    if(suc){
                        Toast.makeText(RiwayatActivity.this, "Rekapan berhasil di download, file tersimpan di folder Downloads", Toast.LENGTH_SHORT).show();

                    }else{
                        Toast.makeText(RiwayatActivity.this, "Gagal simpan PDF", Toast.LENGTH_SHORT).show();
                    }

                }else{
                    Toast.makeText(RiwayatActivity.this, "error", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Toast.makeText(RiwayatActivity.this, ""+t.getMessage(), Toast.LENGTH_SHORT).show();
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