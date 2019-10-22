package com.example.tababsenapp.Controllers;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.widget.Toast;

import com.example.tababsenapp.Fitur.HalamanUtama.MainActivity;

import java.util.HashMap;

import es.dmoral.toasty.Toasty;

public class SessionManager {

    public Context context;
    SharedPreferences sharedPreferences;
    public SharedPreferences.Editor editor;
    int PRIVATE_MODE = 0;
    private static final String PREF_NAME = "LOGIN";

    private static final String STATUS_LOGIN = "STATUS_LOGIN";
    private static final String ID_USER = "ID_USER";
    private static final String NAMA = "NAMA";
    private static final String USERNAME = "USERNAME";

    public SessionManager(Context context) {
        this.context = context;

        sharedPreferences = context.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
        editor = sharedPreferences.edit();
    }

    public void setSessionLogin(String id_user, String nama, String username) {
        editor.putBoolean(STATUS_LOGIN, true);
        editor.putString(ID_USER, id_user);
        editor.putString(NAMA, nama);
        editor.putString(USERNAME, username);
        editor.apply();
    }

    public boolean getStatusLogin() {
        return sharedPreferences.getBoolean(STATUS_LOGIN, false);
    }

    public HashMap<String, String> getDataUser() {
        HashMap<String, String> user = new HashMap<>();

        String data_default = "Data Kosong";
        user.put(ID_USER, sharedPreferences.getString(ID_USER, data_default));
        user.put(NAMA, sharedPreferences.getString(NAMA, data_default));
        user.put(USERNAME, sharedPreferences.getString(USERNAME, data_default));

        return user;
    }

    public void checkLogin() {
        if (!this.getStatusLogin()) {
            logout();

            String message = "Pastikan Anda Login Dahulu";
            Toasty.error(context, message, Toast.LENGTH_SHORT).show();
        }
    }

    public void logout() {
        editor.clear();
        editor.commit();

        Intent intent = new Intent(context, MainActivity.class);
        context.startActivity(intent);
    }

    public String getBaseUrl() {
        String base_url = "http://192.168.137.1/tab_absen/web/api/";
        return base_url;
    }
}
