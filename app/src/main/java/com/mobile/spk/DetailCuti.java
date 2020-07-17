package com.mobile.spk;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;
import com.mobile.spk.R;
import com.mobile.spk.api.ApiClient;
import com.mobile.spk.api.ApiInterface;
import com.mobile.spk.model.Cuti;
import com.mobile.spk.utils.SessionManager;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.HashMap;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailCuti extends AppCompatActivity {
    public static final String DETAIL_CUTI = "detail_cuti" ;
    String items[] = {"Pengajuan","Disetujui","Ditolak"};
    String resultStatus,idCuti;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_cuti);
        SessionManager sessionManager = new SessionManager(this);
        HashMap<String, String> user = sessionManager.getUserDetail();
        String level = user.get(SessionManager.LEVEL);
        getViewDetail(level);

    }

    private void getViewDetail(String level) {
        TableRow statusAdmin = (TableRow) findViewById(R.id.row_admin);
        TableRow statusUser = (TableRow) findViewById(R.id.row_user);
        MaterialButton btnUpdate = (MaterialButton) findViewById(R.id.btnUpdateStatusUser);

        if(level.equals("1")||level.equals("2")){
            statusUser.setVisibility(View.VISIBLE);
        }else{
            statusAdmin.setVisibility(View.VISIBLE);
            btnUpdate.setVisibility(View.VISIBLE);
        }

        Cuti detail = getIntent().getParcelableExtra(DETAIL_CUTI);
        TextView id = (TextView) findViewById(R.id.tx_id_pengajuan);
        TextView tanggal = (TextView) findViewById(R.id.tanggalDetailCuti);
        TextView nama = (TextView) findViewById(R.id.namaDetailCuti);
        TextView jabatan = (TextView) findViewById(R.id.jabatanDetailCuti);
        TextView lokasi = (TextView) findViewById(R.id.lokasiDetailCuti);
        TextView lama = (TextView) findViewById(R.id.lamaDetailCuti);
        TextView alasan = (TextView) findViewById(R.id.alasanDetailCuti);
        TextView mulai = (TextView) findViewById(R.id.mulaiDetailCuti);
        TextView selesai = (TextView) findViewById(R.id.selesaiDetailCuti);
        TextView kembali = (TextView) findViewById(R.id.kembaliKerjaDetailCuti);
        TextView kontrak = (TextView) findViewById(R.id.kontakDetailCuti);
        TextView keterangan = (TextView) findViewById(R.id.keteranganDetailCuti);
        TextView status = (TextView) findViewById(R.id.status_detail_cuti);
        Spinner spStatus = (Spinner) findViewById(R.id.spinnerStatusDetailCuti);
        id.setText("ID Pengajuan : "+detail.getId_cuti());
        idCuti = detail.getId_cuti_ori();
        tanggal.setText(detail.getTanggal());
        nama.setText(detail.getNama_lengkap());
        jabatan.setText(detail.getJabatan());
        lokasi.setText(detail.getLokasi_kerja());
        lama.setText(detail.getLama_cuti());
        alasan.setText(detail.getAlasan_cuti());
        mulai.setText(detail.getMulai_cuti());
        selesai.setText(detail.getSelesai_cuti());
        kembali.setText(detail.getKembali_kerja());
        kontrak.setText(detail.getKontak());
        keterangan.setText(detail.getKeterangan());
        if(level.equals("1")|| level.equals("2")){
            status.setText(detail.getStatus());
        }else{
            ArrayAdapter<String> adapter = new ArrayAdapter<>(this,R.layout.support_simple_spinner_dropdown_item,items);
            spStatus.setAdapter(adapter);
            if(detail.getStatus().equals("Pengajuan")){
                spStatus.setSelection(adapter.getPosition("Pengajuan"));
            }else if(detail.getStatus().equals("Disetujui")){
                spStatus.setSelection(adapter.getPosition("Disetujui"));
            }else{
                spStatus.setSelection(adapter.getPosition("Ditolak"));
            }
        }
        spStatus.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(spStatus.getSelectedItem().toString().equals("Pengajuan")){
                  resultStatus = "0";  
                }else if(spStatus.getSelectedItem().toString().equals("Disetujui")){
                    resultStatus = "1";
                }else{
                    resultStatus = "2";
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            
            }
        });
        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(resultStatus.equals("")){
                    Toast.makeText(DetailCuti.this, "Status tidak boleh kosong", Toast.LENGTH_SHORT).show();
                }else{
                    updateStatusCuti(idCuti,resultStatus);
                }
            }
        });
        initToolbar();
    }

    private void updateStatusCuti(String idCuti, String resultStatus) {
        ApiInterface apiInterface = ApiClient.getApiClient().create(ApiInterface.class);
        Call<ResponseBody> updateCuti = apiInterface.updateStatusCuti(idCuti,resultStatus);
        updateCuti.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if(response.isSuccessful()){
                    try {
                        JSONObject o = new JSONObject(response.body().string());
                        if(o.getString("status").equals("1")){
                            Toast.makeText(DetailCuti.this, "Status cuti berhasil di perbaharui", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(DetailCuti.this,CutiActivity.class));
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
}