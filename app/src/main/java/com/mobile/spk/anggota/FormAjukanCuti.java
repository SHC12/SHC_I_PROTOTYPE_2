package com.mobile.spk.anggota;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;
import com.mobile.spk.CutiActivity;
import com.mobile.spk.HomeActivity;
import com.mobile.spk.R;
import com.mobile.spk.api.ApiClient;
import com.mobile.spk.api.ApiInterface;
import com.mobile.spk.utils.SessionManager;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Locale;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FormAjukanCuti extends AppCompatActivity {
    private DatePickerDialog datePickerDialog;
    private SimpleDateFormat simpleDateFormat;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_ajukan_cuti);
        simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd",Locale.US);
        getView();

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

    private void getView() {
        SessionManager sessionManager = new SessionManager(this);
        HashMap<String, String> user = sessionManager.getUserDetail();
        String namas = user.get(SessionManager.NAMA);
        String jabatans = user.get(SessionManager.JABATAN);
        String idUser= user.get(SessionManager.ID);
        String mitra= user.get(SessionManager.MITRA);
        EditText tanggal = (EditText) findViewById(R.id.in_tgl_cuti);
        EditText nama = (EditText) findViewById(R.id.in_nama_lengkap_cuti);
        EditText jabatan = (EditText) findViewById(R.id.in_jabatan_cuti);
        EditText lokasi = (EditText) findViewById(R.id.in_lokasi_kerja);
        AutoCompleteTextView lamaCuti = (AutoCompleteTextView) findViewById(R.id.in_lama_cuti);
        EditText alasan = (EditText) findViewById(R.id.in_alasan_cuti);
        EditText mulai = (EditText) findViewById(R.id.in_mulai_cuti);
        EditText selesai = (EditText) findViewById(R.id.in_selesai_cuti);
        EditText kembali = (EditText) findViewById(R.id.in_kembali_kerja);
        EditText no_kontrak = (EditText) findViewById(R.id.in_kontak);
        EditText keterangan = (EditText) findViewById(R.id.in_keterangan_tambahan);
        MaterialButton btnAjukanCuti = (MaterialButton) findViewById(R.id.btnAjukan);

        nama.setText(namas);
        jabatan.setText(jabatans);
        lokasi.setText(mitra);
        ArrayList<String> lc = new ArrayList<>();
        for(int i=0; i<10;i++){
            lc.add(""+i);
        }
        getSpinner(lamaCuti,lc);
        mulai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDateDialog(mulai);
            }
        });
        selesai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDateDialog(selesai);
            }
        });
        kembali.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDateDialog(kembali);
            }
        });
        tanggal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDateDialog(tanggal);
            }
        });

        btnAjukanCuti.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String mTanggal = tanggal.getText().toString().trim();
                String mNama = nama.getText().toString().trim();
                String mJabatan = jabatan.getText().toString().trim();
                String mLokasi = lokasi.getText().toString().trim();
                String mLama = lamaCuti.getText().toString().trim();
                String mAlasan = alasan.getText().toString().trim();
                String mMulai = mulai.getText().toString().trim();
                String mSelesai = selesai.getText().toString().trim();
                String mKembali = kembali.getText().toString().trim();
                String mNoKontrak = no_kontrak.getText().toString().trim();
                String mKet = keterangan.getText().toString().trim();
                if(mTanggal.equals("")||mNama.equals("")||mJabatan.equals("")||mLokasi.equals("")||mLama.equals("")||mAlasan.equals("")||mMulai.equals("")||mSelesai.equals("")||mKembali.equals("")||mNoKontrak.equals("")||mKet.equals("")){
                    Toast.makeText(FormAjukanCuti.this, "Isi semua field untuk mengajukan cuti", Toast.LENGTH_SHORT).show();
                }else{
                    ajukanCuti(idUser,mTanggal,mLama,mAlasan,mMulai,mSelesai,mKembali,mNoKontrak,mKet);
                }
            }
        });
    }

    private void ajukanCuti(String id,String mTanggal ,String mLama, String mAlasan, String mMulai, String mSelesai, String mKembali, String mNoKontrak, String mKet) {
        ApiInterface apiInterface = ApiClient.getApiClient().create(ApiInterface.class);
        Call<ResponseBody> ajukanCuti = apiInterface.pengajuanCuti(id,mTanggal,mLama,mAlasan,mMulai,mSelesai,mKembali,mNoKontrak,mKet);
        ajukanCuti.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if(response.isSuccessful()){
                    try {
                        JSONObject o = new JSONObject(response.body().string());
                        if(o.getString("status").equals("1")){
                            Toast.makeText(FormAjukanCuti.this, "Pengajuan Cuti Berhasil", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(FormAjukanCuti.this, CutiActivity.class));
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
                Toast.makeText(FormAjukanCuti.this, ""+t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void getSpinner(AutoCompleteTextView target, ArrayList<String> item) {
        ArrayAdapter<String> adapter = new ArrayAdapter<>(getApplicationContext(), R.layout.spinner_list_item, item);
        target.setAdapter(adapter);
    }
    private void showDateDialog(final EditText edt_target) {

        Calendar newCalendar = Calendar.getInstance();

        datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {


                Calendar newDate = Calendar.getInstance();
                newDate.set(year, monthOfYear, dayOfMonth);

                edt_target.setText(simpleDateFormat.format(newDate.getTime()));
            }

        }, newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH));

        datePickerDialog.show();
    }
}