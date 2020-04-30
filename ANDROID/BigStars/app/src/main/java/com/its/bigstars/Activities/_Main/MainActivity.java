package com.its.bigstars.Activities._Main;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.its.bigstars.Activities._Main.view.IMainView;
import com.its.bigstars.Controllers.ToastMessage;
import com.its.bigstars.R;

import es.dmoral.toasty.Toasty;

public class MainActivity extends AppCompatActivity implements View.OnClickListener, IMainView {

    ToastMessage toastMessage;

    Button btnLoginAdmin, btnLoginPengajar, btnLoginWaliMurid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toastMessage = new ToastMessage(this);

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

        if (!hakAkses.equals("")){
            toastMessage.onSuccessMessage(hakAkses);
        }

//        intent = new Intent(MainActivity.this, LoginActivity.class);
//        intent.putExtra(LoginActivity.EXTRA_HAK_AKSES, hakAkses);
//        startActivity(intent);
    }
}
