package com.mobile.spk;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;
import com.mobile.spk.api.ApiClient;
import com.mobile.spk.api.ApiInterface;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterActivity extends AppCompatActivity {
    private DatePickerDialog datePickerDialog;
    private SimpleDateFormat simpleDateFormat;
    private ArrayList<String> jabatanList = new ArrayList<>();
    private ArrayList<String> mitraList = new ArrayList<>();
    private ApiInterface apiInterface;
    private ProgressDialog progressDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        progressDialog = new ProgressDialog(this);
        apiInterface = ApiClient.getApiClient().create(ApiInterface.class);
        simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.US);
        EditText edtTanggal = (EditText) findViewById(R.id.in_tgl_registrasi);
        EditText edtNomorAnggota = (EditText) findViewById(R.id.in_nomor_anggota_registrasi);
        EditText edtNamaLengkap = (EditText) findViewById(R.id.in_nama_lengkap_register);
        AutoCompleteTextView edtJabatan = (AutoCompleteTextView) findViewById(R.id.in_jabatan_registrasi);
        AutoCompleteTextView edtMitra = (AutoCompleteTextView) findViewById(R.id.in_mitra_registrasi);
        EditText edtNomorHandphone = (EditText) findViewById(R.id.in_nomor_handphone_register);
        EditText edtEmail = (EditText) findViewById(R.id.in_email_registrasi);
        EditText edtUsername = (EditText) findViewById(R.id.in_username_registrasi);
        EditText edtPassword = (EditText) findViewById(R.id.in_password_registrasi);
        MaterialButton btnRegistrasi = (MaterialButton) findViewById(R.id.btnRegistrasi);

        edtJabatan.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                Toast.makeText(RegisterActivity.this, ""+edtJabatan.getText().toString(), Toast.LENGTH_SHORT).show();
            }
        });
        getJabatan(edtJabatan);
        getMitra(edtMitra);
        edtTanggal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDateDialog(edtTanggal);
            }
        });
        btnRegistrasi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            String tanggal = edtTanggal.getText().toString();
            String nomorAnggota = edtNomorAnggota.getText().toString().trim();
            String namaLengkap = edtNamaLengkap.getText().toString();
            String jabatan = edtJabatan.getText().toString().trim();
            String mitra = edtMitra.getText().toString();
            String nomorHandphone = edtNomorHandphone.getText().toString().trim();
            String email = edtEmail.getText().toString().trim();
            String username = edtUsername.getText().toString().trim();
            String password = edtPassword.getText().toString().trim();
            if(tanggal.equals("")||nomorAnggota.equals("")||namaLengkap.equals("")||jabatan.equals("")||mitra.equals("")||nomorHandphone.equals("")||email.equals("")||username.equals("")||password.equals("")){
                Toast.makeText(RegisterActivity.this, "Semua field wajib di isi", Toast.LENGTH_SHORT).show();
            }else{
                registrasi(tanggal,nomorAnggota,namaLengkap,jabatan,mitra,nomorHandphone,email,username,password);
            }
            }
        });

        initToolbar();


    }

    private void initToolbar() {
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(R.drawable.ic_baseline_arrow_back_24);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        getSupportActionBar().setTitle(null);

    }
    private void registrasi(String tanggal, String nomorAnggota, String namaLengkap, String mJabatan, String mitra, String nomorHandphone, String email, String username, String password) {
    Call<ResponseBody> registras = apiInterface.registras(tanggal,nomorAnggota,namaLengkap,mJabatan,mitra,nomorHandphone,email,username,password);
    registras.enqueue(new Callback<ResponseBody>() {
        @Override
        public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
            if(response.isSuccessful()){
                try {
                    JSONObject o = new JSONObject(response.body().string());
                    if(o.getString("status").equals("1")){
                        Toast.makeText(RegisterActivity.this, "Registrasi Berhasil, Anda akan di hubungi oleh admin jika akun anda sudah aktif", Toast.LENGTH_LONG).show();
                        startActivity(new Intent(RegisterActivity.this,MenuLoginActivity.class));
                    }else{
                        Toast.makeText(RegisterActivity.this, "Terjadi kesalahan", Toast.LENGTH_SHORT).show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }else{
                Toast.makeText(RegisterActivity.this, "Koneksi bermasalah", Toast.LENGTH_SHORT).show();
            }
        }

        @Override
        public void onFailure(Call<ResponseBody> call, Throwable t) {
            Toast.makeText(RegisterActivity.this, ""+t.getMessage(), Toast.LENGTH_SHORT).show();
        }
    });



    }

    private void getJabatan(AutoCompleteTextView target){
        progressDialog.show();
        progressDialog.setMessage("Loading...");
        Call<ResponseBody> getJabatan = apiInterface.getJabatan();
        getJabatan.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
            if(response.isSuccessful()){
                progressDialog.dismiss();
                try {
                    JSONObject o = new JSONObject(response.body().string());
                    JSONArray a = o.getJSONArray("jabatan");
                    for(int i = 0; i<a.length();i++){
                    JSONObject ao = a.getJSONObject(i);
                    jabatanList.add(ao.getString("nama_jabatan"));
                    }
                    getSpinner(target,jabatanList);
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
    private void getMitra(AutoCompleteTextView target){
        progressDialog.show();
        progressDialog.setMessage("Loading...");
        Call<ResponseBody> getMitra = apiInterface.getMitra();
        getMitra.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if(response.isSuccessful()){
                    progressDialog.dismiss();
                    try {
                        JSONObject o = new JSONObject(response.body().string());
                        JSONArray mA = o.getJSONArray("mitra");
                        for(int i = 0; i<mA.length();i++){
                            JSONObject s = mA.getJSONObject(i);
                            mitraList.add(s.getString("nama_mitra"));
                        }
                        getSpinner(target,mitraList);
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