package com.mobile.spk.admin;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;
import com.mobile.spk.HomeActivity;
import com.mobile.spk.R;
import com.mobile.spk.api.ApiClient;
import com.mobile.spk.api.ApiInterface;
import com.mobile.spk.model.DataAnggota;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetailDataAnggota extends AppCompatActivity {
    private ApiInterface apiInterface;
    public static final String DETAIL_ANGGOTA = "detail_anggota" ;
    private String id,tgl,no,nama,jabatan,mitra,nope,email,username,password,status;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_data_anggota);

        apiInterface = ApiClient.getApiClient().create(ApiInterface.class);
        DataAnggota dataAnggota = getIntent().getParcelableExtra(DETAIL_ANGGOTA);
        id = dataAnggota.getId();
        tgl = dataAnggota.getTgl();
        no = dataAnggota.getNoAngota();
        nama = dataAnggota.getNama();
        jabatan = dataAnggota.getJabatan();
        mitra = dataAnggota.getMitra();
        nope = dataAnggota.getNoHp();
        email = dataAnggota.getEmail();
        username = dataAnggota.getUsername();
        password = dataAnggota.getPassword();
        status = dataAnggota.getStatus();
        getDetailAnggota(id,tgl,no,nama,jabatan,mitra,nope,email,username,password,status);
        initToolbar();
    }

    private void getDetailAnggota(String id,String tgl, String no, String nama, String jabatan, String mitra, String nope, String email, String username, String password, String status) {
        LinearLayout viewDetailAnggota = (LinearLayout) findViewById(R.id.view_detail_anggota);
        LinearLayout viewUpdateAnggota = (LinearLayout) findViewById(R.id.view_update_anggota);
        TextView dTgl = (TextView) findViewById(R.id.tanggalRegistrasiDetailAnggota);
        TextView dNoAnggota = (TextView) findViewById(R.id.noAnggotaDetailAnggota);
        TextView dNama = (TextView) findViewById(R.id.namaDetailAnggota);
        TextView dJabatan = (TextView) findViewById(R.id.jabatanDetailAnggota);
        TextView dMitra = (TextView) findViewById(R.id.mitraDetailAnggota);
        TextView dNope = (TextView) findViewById(R.id.noHandphoneDetailAngggota);
        TextView dEmail = (TextView) findViewById(R.id.emailDetailAnggota);
        TextView dUsername = (TextView) findViewById(R.id.usernameDetailAnggota);
        TextView dPassword = (TextView) findViewById(R.id.passwordDetailAnggota);
        TextView dStatus = (TextView) findViewById(R.id.statusDetailAnggota);
        MaterialButton btnHapus = (MaterialButton) findViewById(R.id.btnHapusAnggota);
        MaterialButton btnUpdate = (MaterialButton) findViewById(R.id.btnUpdateAnggota);

        dTgl.setText(tgl);
        dNoAnggota.setText(no);
        dNama.setText(nama);
        dJabatan.setText(jabatan);
        dMitra.setText(mitra);
        dNope.setText(nope);
        dEmail.setText(email);
        dUsername.setText(username);
        dPassword.setText(password);
        dStatus.setText(status);

        btnHapus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
             androidx.appcompat.app.AlertDialog.Builder builder = new androidx.appcompat.app.AlertDialog.Builder(DetailDataAnggota.this);
                builder.setCancelable(true);
                builder.setTitle("Hapus Anggota");
                builder.setMessage("Anda yakin ingin menghapus anggota ini ?");
                builder.setNegativeButton("Tidak", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
                builder.setPositiveButton("Hapus", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        hapusUser(id);
                    }
                });
                builder.show();
            }
        });
        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewDetailAnggota.setVisibility(View.GONE);
                viewUpdateAnggota.setVisibility(View.VISIBLE);
                updateUser(id,no,nama,jabatan,mitra,nope,email,username,password,status);
            }
        });

    }

    private void updateUser(String id,String no, String nama, String jabatan, String mitra, String nope, String email, String username, String password, String status) {
    String[] listJabatan = {"Anggota","PKD","Danru"};
    String[] listStatus = {"Daftar","Aktif","Non Aktif"};
    EditText uNomorAnggota = (EditText) findViewById(R.id.in_no_anggota_update_anggota);
    EditText uNamaAnggota = (EditText) findViewById(R.id.in_nama_lengkap_update_anggota);
    AutoCompleteTextView uJabatan = (AutoCompleteTextView) findViewById(R.id.in_jabatan_update_anggota);
    EditText uMitra = (EditText) findViewById(R.id.in_mitra_update_anggota);
    EditText uNope = (EditText) findViewById(R.id.in_no_handphone_update_anggota);
    EditText uEmail = (EditText) findViewById(R.id.in_email_update_anggota);
    EditText uUsername = (EditText) findViewById(R.id.in_username_update_anggota);
    EditText uPassword = (EditText) findViewById(R.id.in_password_update_anggota);
    AutoCompleteTextView uStatus = (AutoCompleteTextView) findViewById(R.id.in_status_update_anggota);
    MaterialButton btnUpdateData = (MaterialButton) findViewById(R.id.btnUpdate);
    uNomorAnggota.setText(no);
    uNamaAnggota.setText(nama);
    uJabatan.setText(jabatan);
    uMitra.setText(mitra);
    uNope.setText(nope);
    uEmail.setText(email);
    uUsername.setText(username);
    uPassword.setText(password);
    uStatus.setText(status);
    getSpinner(uJabatan,listJabatan);
    getSpinner(uStatus,listStatus);

    btnUpdateData.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            String nNoAnggota = uNomorAnggota.getText().toString();
            String nNama = uNamaAnggota.getText().toString();
            String nJabatan = uJabatan.getText().toString();
            String nMitra = uMitra.getText().toString();
            String nNope = uNope.getText().toString();
            String nEmail = uEmail.getText().toString();
            String nUsername = uUsername.getText().toString();
            String nPassword = uPassword.getText().toString();
            String nStatus = uStatus.getText().toString();
            if(nNoAnggota.equals("")||nNama.equals("")||nJabatan.equals("")||nMitra.equals("")||nNope.equals("")||nEmail.equals("")||nUsername.equals("")||nPassword.equals("")||nStatus.equals("")){
                Toast.makeText(DetailDataAnggota.this, "Field tidak boleh ada yang kosong", Toast.LENGTH_SHORT).show();
            }else{
                updateDataAnggota(id,nNoAnggota,nNama,nJabatan,nMitra,nNope,nEmail,nUsername,nPassword,nStatus);
            }
            
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
    private void updateDataAnggota(String id, String nNoAnggota, String nNama, String nJabatan, String nMitra, String nNope, String nEmail, String nUsername, String nPassword, String nStatus) {
        Call<ResponseBody> updateDataUser = apiInterface.updateUser(id,nNoAnggota,nNama,nJabatan,nMitra,nNope,nEmail,nUsername,nPassword,nStatus);
        updateDataUser.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if(response.isSuccessful()){
                    try {
                        JSONObject o = new JSONObject(response.body().string());
                        if(o.getString("status").equals("1")){
                            Toast.makeText(DetailDataAnggota.this, "Data user berhasil di update", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(DetailDataAnggota.this,DataAnggotaActivity.class));
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

    private void getSpinner(AutoCompleteTextView target, String[] item) {
        ArrayAdapter<String> adapter = new ArrayAdapter<>(getApplicationContext(), R.layout.spinner_list_item, item);
        target.setAdapter(adapter);
    }
    private void hapusUser(String id) {
        Call<ResponseBody> hapusUser = apiInterface.hapus_user(id);
        hapusUser.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if(response.isSuccessful()){
                    try {
                        JSONObject o = new JSONObject(response.body().string());
                        if(o.getString("status").equals("1")){
                            Toast.makeText(DetailDataAnggota.this, "User berhasil di hapus", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(DetailDataAnggota.this,DataAnggotaActivity.class));
                        }else{
                            Toast.makeText(DetailDataAnggota.this, "Terjadi Kesalahan", Toast.LENGTH_SHORT).show();
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
                Toast.makeText(DetailDataAnggota.this, ""+t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}