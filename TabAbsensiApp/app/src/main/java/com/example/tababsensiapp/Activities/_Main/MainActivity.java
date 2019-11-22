package com.example.tababsensiapp.Activities._Main;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.tababsensiapp.Activities._Login.LoginActivity;
import com.example.tababsensiapp.Activities._Main.view.IMainView;
import com.example.tababsensiapp.R;

import es.dmoral.toasty.Toasty;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, IMainView {

    Button btnLoginAdmin, btnLoginPengajar, btnLoginWaliMurid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnLoginAdmin = findViewById(R.id.btn_login_admin);
        btnLoginPengajar = findViewById(R.id.btn_login_pengajar);
        btnLoginWaliMurid = findViewById(R.id.btn_login_wali_murid);

        btnLoginAdmin.setOnClickListener(this);
        btnLoginPengajar.setOnClickListener(this);
        btnLoginWaliMurid.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        String hakAkses = "";

        Intent intent;

        if (v.getId() == R.id.btn_login_admin) {
            hakAkses = "admin";
        }
        if (v.getId() == R.id.btn_login_pengajar) {
            hakAkses = "pengajar";
        }
        if (v.getId() == R.id.btn_login_wali_murid) {
            hakAkses = "wali_murid";
        }

        intent = new Intent(MainActivity.this, LoginActivity.class);
        intent.putExtra(LoginActivity.EXTRA_HAK_AKSES, hakAkses);
        startActivity(intent);
    }

    @Override
    public void onSuccessMessage(String message) {
        Toasty.success(this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onErrorMessage(String message) {
        Toasty.error(this, message, Toast.LENGTH_SHORT).show();
    }
}
