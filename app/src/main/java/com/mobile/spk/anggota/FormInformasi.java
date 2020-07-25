package com.mobile.spk.anggota;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;
import com.mobile.spk.FilePath;
import com.mobile.spk.HomeActivity;
import com.mobile.spk.InformasiActivity;
import com.mobile.spk.R;
import com.mobile.spk.api.ApiClient;
import com.mobile.spk.api.ApiInterface;
import com.mobile.spk.utils.SessionManager;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Locale;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FormInformasi extends AppCompatActivity {
    String path_file,getSize;
    File file;
    private boolean fileFoto = false;
    EditText edtTgl,edtSumber,edtJudul,edtDetail,edtFile;
    private DatePickerDialog datePickerDialog;
    private SimpleDateFormat simpleDateFormat;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form_informasi);

        SessionManager sessionManager = new SessionManager(this);
        HashMap<String, String> user = sessionManager.getUserDetail();
        simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.US);
        edtTgl = (EditText) findViewById(R.id.in_tgl_informasi);
        edtSumber = (EditText) findViewById(R.id.in_sumber_informasi);
        String namas = user.get(SessionManager.NAMA);
        edtSumber.setText(namas);
        edtJudul = (EditText) findViewById(R.id.in_judul_informasi);
        edtDetail = (EditText) findViewById(R.id.in_detail_informasi);
        edtFile = (EditText) findViewById(R.id.in_file_informasi);
        MaterialButton btnSubmit = (MaterialButton) findViewById(R.id.btn_submit_informasi);
        edtTgl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDateDialog(edtTgl);
            }
        });

        edtFile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_OPEN_DOCUMENT);
                intent.setType("*/*");
                startActivityForResult(intent, 42);
            }
        });
        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String mTgl = edtTgl.getText().toString();
                String mJudul = edtJudul.getText().toString();
                String mSumber = edtSumber.getText().toString();
                String mDetail = edtDetail.getText().toString();
                String mFile = edtFile.getText().toString();
                if(mTgl.equals("")||mJudul.equals("")||mSumber.equals("")||mDetail.equals("")){
                    Toast.makeText(FormInformasi.this, "Semua field wajib di isi", Toast.LENGTH_SHORT).show();
                }else{
                    if(fileFoto){
                        int size = (int) getFolderSizeLabel(file);
                        if(size > 5){
                            Toast.makeText(FormInformasi.this, "File yang dipilih melebihi batasan maksimal 10 mb", Toast.LENGTH_SHORT).show();
                        }else{
                            submitInformasi(mTgl,mJudul,mSumber,mDetail,file);
                        }
                    }else{
                        submitInformasiNoFile(mTgl,mJudul,mSumber,mDetail);
                    }

                }
            }
        });

        initToolbar();
    }

    private void submitInformasiNoFile(String mTgl, String mJudul, String mSumber, String mDetail) {
        ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Loading...");
        progressDialog.show();
        ApiInterface apiInterface = ApiClient.getApiClient().create(ApiInterface.class);
        Call<ResponseBody> submitInfoNoFile = apiInterface.submitInformasinoFile(mTgl,mSumber,mJudul, mDetail);
        submitInfoNoFile.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if(response.isSuccessful()){
                    progressDialog.dismiss();
                    try {
                        JSONObject o = new JSONObject(response.body().string());
                        if(o.getString("status").equals("1")){
                            Toast.makeText(FormInformasi.this, "Laporan berhasil di buat", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(FormInformasi.this, InformasiActivity.class));
                        }else {
                            Toast.makeText(FormInformasi.this, "Terjadi kesalahan", Toast.LENGTH_SHORT).show();
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
                progressDialog.dismiss();
                Toast.makeText(FormInformasi.this, ""+t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void submitInformasi(String mTgl, String mJudul, String mSumber, String mDetail, File mFile) {
        ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Loading...");
        progressDialog.show();
        ApiInterface apiInterface = ApiClient.getApiClient().create(ApiInterface.class);
        RequestBody resBody = RequestBody.create(MediaType.parse("multipart/form-file"), mFile);
        RequestBody rTgl = RequestBody.create(MediaType.parse("text/plain"), mTgl);
        RequestBody rJudul = RequestBody.create(MediaType.parse("text/plain"), mJudul);
        RequestBody rSumber = RequestBody.create(MediaType.parse("text/plain"), mSumber);
        RequestBody rDetail = RequestBody.create(MediaType.parse("text/plain"), mDetail);
        MultipartBody.Part partFile = MultipartBody.Part.createFormData("file", mFile.getName(), resBody);
        Call<ResponseBody> submitFile = apiInterface.submitInformasi(rTgl,rSumber,rJudul,rDetail,partFile);
        submitFile.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if(response.isSuccessful()){
                    progressDialog.dismiss();
                    try {
                        JSONObject o = new JSONObject(response.body().string());
                        if(o.getString("status").equals("1")){
                            Toast.makeText(FormInformasi.this, "Laporan berhasil di buat", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(FormInformasi.this, InformasiActivity.class));
                        }else {
                            Toast.makeText(FormInformasi.this, "Terjadi kesalahan", Toast.LENGTH_SHORT).show();
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
                progressDialog.dismiss();
                Toast.makeText(FormInformasi.this, ""+t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {

            case 42:
                if (requestCode == 42 && resultCode == Activity.RESULT_OK) {
                    Uri uri = data.getData();
                    path_file = FilePath.getFilePath(FormInformasi.this, uri);
                    file = new File(path_file);
                    fileFoto = true;
                    int size = (int) getFolderSizeLabel(file);
                    if(size > 10){
                        Toast.makeText(this, "File tidak boleh lebih dari 10 Mb", Toast.LENGTH_SHORT).show();
                    }
                    edtFile.setText(file.getName());


                }
                break;

        }
    }
    public static long getFolderSizeLabel(File file) {
        long size = getFolderSize(file) / 1024; // Get size and convert bytes into Kb.
        if (size >= 1024) {
            return (size / 1024);
        } else {
            return 1;
        }
    }
    public static long getFolderSize(File file) {
        long size = 0;
        if (file.isDirectory()) {
            for (File child : file.listFiles()) {
                size += getFolderSize(child);
            }
        } else {
            size = file.length();
        }
        return size;
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