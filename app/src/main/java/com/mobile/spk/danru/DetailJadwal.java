package com.mobile.spk.danru;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;
import com.mobile.spk.HomeActivity;
import com.mobile.spk.R;
import com.mobile.spk.api.ApiClient;
import com.mobile.spk.api.ApiInterface;
import com.mobile.spk.model.Cuti;
import com.mobile.spk.model.Jadwal;
import com.mobile.spk.utils.SessionManager;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.HashMap;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailJadwal extends AppCompatActivity {
    public static final String DETAIL_JADWAL = "detail_jadwal" ;
    TextView kode_jadwal, nama, tanggal, lokasi, shift, status_absen, keterangan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_jadwal);

        Jadwal detail = getIntent().getParcelableExtra(DETAIL_JADWAL);
        kode_jadwal = findViewById(R.id.tx_kode_jadwal);
        tanggal = findViewById(R.id.tanggalDetailJadwal);
        nama = findViewById(R.id.namaDetailJadwal);
        lokasi = findViewById(R.id.lokasiJadwal);
        shift = findViewById(R.id.shiftJadwal);
        status_absen = findViewById(R.id.absenJadwal);
        keterangan = findViewById(R.id.keteranganJadwal);


        kode_jadwal.setText("Kode Jadwal : "+ detail.getKodeJadwal());
        tanggal.setText(detail.getTanggal());
        nama.setText(detail.getNamaPetugas());
        lokasi.setText(detail.getLokasi());
        shift.setText(detail.getShift());
        status_absen.setText(detail.getStatusAbsen());
        keterangan.setText(detail.getKeterangan());

        MaterialButton btnHapus = (MaterialButton) findViewById(R.id.btnHapusJadwal);
        MaterialButton btnUpdate = (MaterialButton) findViewById(R.id.btnUpdateJadwal);
        SessionManager sessionManager = new SessionManager(this);
        HashMap<String, String> user = sessionManager.getUserDetail();
        if(user.get(SessionManager.LEVEL).equals("1")){
            btnHapus.setVisibility(View.GONE);
            btnUpdate.setVisibility(View.GONE);
        }
        String[] id = detail.getKodeJadwal().split("JD");
        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(DetailJadwal.this,UpdateJadwal.class);
                i.putExtra("kode",detail.getKodeJadwal());
                i.putExtra("nama",detail.getNamaPetugas());
                i.putExtra("tanggal",detail.getTanggal());
                i.putExtra("lokasi",detail.getLokasi());
                i.putExtra("shift",detail.getShift());
                startActivity(i);
            }
        });
        btnHapus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                hapusJadwal(id[1].trim());
            }
        });
        initToolbar();
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

    private void hapusJadwal(String s) {
        ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.show();
        progressDialog.setMessage("Loading...");
        ApiInterface apiInterface = ApiClient.getApiClient().create(ApiInterface.class);
        Call<ResponseBody> hapusJadwal = apiInterface.hapusJadwal(s);
        hapusJadwal.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                progressDialog.dismiss();
                if(response.isSuccessful()){
                    try {
                        JSONObject o = new JSONObject(response.body().string());
                        if(o.getString("status").equals("1")){
                            Toast.makeText(DetailJadwal.this, "Jadwal berhasil di hapus", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(DetailJadwal.this, JadwalHariIniActivity.class));
                        }else{
                            Toast.makeText(DetailJadwal.this, "Terjadi kesalahan internal", Toast.LENGTH_SHORT).show();
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }else{
                    Toast.makeText(DetailJadwal.this, "Koneksi bermasalah", Toast.LENGTH_SHORT).show();

                }

            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Toast.makeText(DetailJadwal.this, t.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });
    }
}