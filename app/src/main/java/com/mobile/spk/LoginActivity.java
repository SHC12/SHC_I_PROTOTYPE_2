package com.mobile.spk;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;
import com.mobile.spk.api.ApiClient;
import com.mobile.spk.api.ApiInterface;
import com.mobile.spk.utils.SessionManager;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {
    private EditText username,password;
    private MaterialButton btnLogin;
    private SessionManager sessionManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        sessionManager = new SessionManager(this);
        username = findViewById(R.id.in_username);
        password = findViewById(R.id.in_password);
        btnLogin = findViewById(R.id.btnLogin);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String mUsername = username.getText().toString().trim();
                String mPassword = password.getText().toString().trim();
                if(mUsername.equals("")||mPassword.equals("")){
                    Toast.makeText(LoginActivity.this, "Semua field wajib di isi", Toast.LENGTH_SHORT).show();
                }else{
                    login(mUsername,mPassword);

                }
            }
        });


    }

    private void login(String mUsername, String mPassword) {
        ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Login...");
        progressDialog.show();
        ApiInterface apiInterface = ApiClient.getApiClient().create(ApiInterface.class);
        Call<ResponseBody> login = apiInterface.login(mUsername,mPassword);
        login.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                progressDialog.dismiss();
                if(response.isSuccessful()){
                    try {
                        JSONObject  o = new JSONObject(response.body().string());
                        if(o.getString("status").equals("1")){
                            if(o.getString("status_user").equals("0")){
                                Toast.makeText(LoginActivity.this, "Akun anda belum aktif", Toast.LENGTH_SHORT).show();
                            }else if(o.getString("status_user").equals("1")){
                                String id = o.getString("id_user");
                                String nama = o.getString("nama_lengkap");
                                String username = o.getString("username");
                                String password = o.getString("password");
                                String jabatan = o.getString("jabatan");
                                String level = o.getString("level");
                                String mitra = o.getString("mitra");
                                sessionManager.createSession(id,username,nama,level,password,jabatan,mitra);
                                startActivity(new Intent(LoginActivity.this,HomeActivity.class));
                            }else{
                                Toast.makeText(LoginActivity.this, "Akun anda telah di non aktif kan", Toast.LENGTH_SHORT).show();
                            }
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }else{
                    Toast.makeText(LoginActivity.this, "Koneksi Bermasalahan", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(LoginActivity.this, ""+t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }


}