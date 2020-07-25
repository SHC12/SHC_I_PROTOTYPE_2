package com.mobile.spk.anggota;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;
import com.mobile.spk.R;
import com.mobile.spk.api.ApiClient;
import com.mobile.spk.api.ApiInterface;
import com.mobile.spk.model.AbsenUser;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FormAbsensi extends AppCompatActivity {
    public static final String DETAIL_JADWAL = "detail_jadwal" ;
    private String[] status_absen = {"Hadir","Tidak Hadir","Cuti"};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_absensi);
        EditText eKode = (EditText) findViewById(R.id.in_kode_absen);
        EditText eNamaPetugas = (EditText) findViewById(R.id.in_nama_absen);
        EditText eTanggal = (EditText) findViewById(R.id.in_tgl_absen);
        EditText eLokasi = (EditText) findViewById(R.id.in_lokasi_absen);
        EditText eStatusShift = (EditText) findViewById(R.id.in_shift_absen);
        AutoCompleteTextView eAbsen = (AutoCompleteTextView) findViewById(R.id.in_status_absen);
        EditText eKeterangan = (EditText) findViewById(R.id.in_keterangan_absen);
        AbsenUser absen = getIntent().getParcelableExtra(DETAIL_JADWAL);
        MaterialButton submitAbsen = (MaterialButton) findViewById(R.id.btn_submit_absen);
        eKode.setText(absen.getKode());
        eNamaPetugas.setText(absen.getNamaPetugas());
        eLokasi.setText(absen.getLokasi());
        eTanggal.setText(absen.getTanggal());
        eStatusShift.setText(absen.getStatus_shift());
        getSpinner(eAbsen, status_absen);

        submitAbsen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String kode = eKode.getText().toString().trim();
                String sAbsen = eAbsen.getText().toString().trim();

                String keterangan = eKeterangan.getText().toString().trim();

                if(kode.equals("") ||sAbsen.equals("")||keterangan.equals("")){
                    Toast.makeText(FormAbsensi.this, "Semua field wajib di isi", Toast.LENGTH_SHORT).show();
                }else{
                    if(sAbsen.equals("Hadir")){
                        int  kodeAbsen = 1;
                        submitAbsenUser(kode,kodeAbsen,keterangan);

                    }else if(sAbsen.equals("Tidak Hadir")){
                        int  kodeAbsen = 2;
                        submitAbsenUser(kode,kodeAbsen,keterangan);

                    }else {
                        int  kodeAbsen = 3;
                        submitAbsenUser(kode,kodeAbsen,keterangan);

                    }
                }

            }
        });

    }

    private void submitAbsenUser(String kode, int sAbsen, String keterangan) {
        ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.show();
        progressDialog.setMessage("Loading...");
        ApiInterface apiInterface = ApiClient.getApiClient().create(ApiInterface.class);
        Call<ResponseBody> submitAsebn = apiInterface.submitAbsen(kode,sAbsen,keterangan);
        submitAsebn.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                progressDialog.dismiss();
                if(response.isSuccessful()){
                    try {
                        JSONObject o = new JSONObject(response.body().string());
                        if(o.getString("status").equals("1")){
                            Toast.makeText(FormAbsensi.this, "Absen berhasil di submit", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(FormAbsensi.this, JadwalActivityAnggota.class).putExtra("triggerAbsen","riwayat"));
                        }else{
                            Toast.makeText(FormAbsensi.this, "Terjadi kesalahan internal", Toast.LENGTH_SHORT).show();
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }else{
                    Toast.makeText(FormAbsensi.this, "Koneksi bermasalah", Toast.LENGTH_SHORT).show();

                }

            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Toast.makeText(FormAbsensi.this, t.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });
    }

    private void getSpinner(AutoCompleteTextView target, String[] item) {
        ArrayAdapter<String> adapter= new ArrayAdapter<>(getApplicationContext(),R.layout.spinner_list_item,item);
        target.setAdapter(adapter);
    }
}