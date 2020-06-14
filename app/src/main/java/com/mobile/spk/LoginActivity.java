package com.mobile.spk;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.google.android.material.button.MaterialButton;
import com.mobile.spk.admin.HomeAdmin;
import com.mobile.spk.anggota.HomeAnggota;
import com.mobile.spk.danru.HomeDanru;

public class LoginActivity extends AppCompatActivity {
    private EditText username;
    private MaterialButton btnLogin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        username = findViewById(R.id.in_username);
        btnLogin = findViewById(R.id.btnLogin);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(username.getText().toString().equals("user")){
                    startActivity(new Intent(LoginActivity.this, HomeAnggota.class));
                } else if(username.getText().toString().equals("danru")){
                    Intent in = new Intent(LoginActivity.this, HomeDanru.class);
                    in.putExtra("username", "Danru");
                    startActivity(in);
                }else if(username.getText().toString().equals("admin")){
//                    startActivity(new Intent(LoginActivity.this, HomeAdmin.class));
                    Intent in = new Intent(LoginActivity.this, HomeAdmin.class);
                    in.putExtra("username", "Admin");
                    startActivity(in);
                }
            }
        });


    }
}