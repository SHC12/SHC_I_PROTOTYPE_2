package com.mobile.spk;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MenuLoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_login);
    }

    public void toAjukan(View view) {
        startActivity(new Intent(MenuLoginActivity.this, RegisterActivity.class));
    }

    public void toLogin(View view) {
        startActivity(new Intent(MenuLoginActivity.this, LoginActivity.class));
    }

    public void toInfo(View view) {
        startActivity(new Intent(MenuLoginActivity.this, InfoAplikasiActivity.class));
    }
}