package com.its.bigstars.Activities._Main;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.its.bigstars.Activities._Login.LoginActivity;
import com.its.bigstars.R;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

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
        } else if (v.getId() == R.id.btn_login_pengajar) {
            hakAkses = "pengajar";
        } else if (v.getId() == R.id.btn_login_wali_murid) {
            hakAkses = "wali_murid";
        }

        if (!hakAkses.equals("")) {
            intent = new Intent(getApplicationContext(), LoginActivity.class);
            intent.putExtra(LoginActivity.EXTRA_HAK_AKSES, hakAkses);
            startActivity(intent);
        }
    }
}
