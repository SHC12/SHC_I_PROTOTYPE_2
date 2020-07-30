package com.mobile.spk.utils;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

import com.mobile.spk.HomeActivity;
import com.mobile.spk.MenuLoginActivity;

import java.util.HashMap;

public class SessionManager {
    public static final String ID = "ID_USER";
    public static final String USERNAME = "USERNAME";
    public static final String NAMA = "NAMA";
    public static final String LEVEL = "LEVEL";
    public static final String JABATAN = "JABATAN";
    public static final String MITRA = "MITRA";
    public static final String PASSWORD = "PASSWORD";
    private static final String PREF_NAME = "LOGIN";
    private static final String LOGIN = "IS_LOGIN";
    public SharedPreferences.Editor editor;
    public Context context;
    SharedPreferences sharedPreferences;
    int PRIVATE_MODE = 0;

    public SessionManager(Context context) {
        this.context = context;
        sharedPreferences = context.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
        editor = sharedPreferences.edit();
    }

    public void createSession(String id_user, String username, String nama, String level, String password,String jabatan,String mitra) {

        editor.putBoolean(LOGIN, true);
        editor.putString(ID, id_user);
        editor.putString(USERNAME, username);
        editor.putString(LEVEL, level);
        editor.putString(NAMA, nama);
        editor.putString(PASSWORD, password);
        editor.putString(JABATAN, jabatan);
        editor.putString(MITRA, mitra);
        editor.apply();

    }

    public boolean isLoggin() {
        return sharedPreferences.getBoolean(LOGIN, false);
    }

    public void checkLogin() {

        if (!this.isLoggin()) {
            Intent i = new Intent(context, MenuLoginActivity.class);
            context.startActivity(i);
            ((HomeActivity) context).finish();
        }
    }

    public HashMap<String, String> getUserDetail() {

        HashMap<String, String> user = new HashMap<>();
        user.put(ID, sharedPreferences.getString(ID, null));
        user.put(USERNAME, sharedPreferences.getString(USERNAME, null));
        user.put(NAMA, sharedPreferences.getString(NAMA, null));
        user.put(LEVEL, sharedPreferences.getString(LEVEL, null));
        user.put(PASSWORD, sharedPreferences.getString(PASSWORD, null));
        user.put(JABATAN, sharedPreferences.getString(JABATAN, null));
        user.put(MITRA, sharedPreferences.getString(MITRA, null));
        return user;
    }

    public void logout() {

        editor.clear();
        editor.commit();
        Intent i = new Intent(context, MenuLoginActivity.class);
        context.startActivity(i);

    }
}
