package com.example.tababsenapp.Fitur.HalamanUtama;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.tababsenapp.Fitur.HalamanLogin.HalamanLoginActivity;
import com.example.tababsenapp.Fitur.HalamanUtama.presenter.IUtamaPresenter;
import com.example.tababsenapp.Fitur.HalamanUtama.presenter.UtamaPresenter;
import com.example.tababsenapp.Fitur.HalamanUtama.view.IUtamaView;
import com.example.tababsenapp.R;

public class MainActivity extends AppCompatActivity implements IUtamaView {

    Button btnMasukWaliMurid, btnMasukPengajar, btnMasukAdmin;
    String EXTRA_HAK_AKSES = "EXTRA_HAK_AKSES";

    IUtamaPresenter utamaPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnMasukWaliMurid = findViewById(R.id.btn_masuk_wali_murid);
        btnMasukPengajar = findViewById(R.id.btn_masuk_pengajar);
        btnMasukAdmin = findViewById(R.id.btn_masuk_admin);

        utamaPresenter = new UtamaPresenter(this);

        btnMasukWaliMurid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String hakAkses = "wali_murid";

                utamaPresenter.onMasukLogin(hakAkses);
            }
        });

        btnMasukPengajar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String hakAkses = "pengajar";

                utamaPresenter.onMasukLogin(hakAkses);
            }
        });

        btnMasukAdmin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String hakAkses = "admin";

                utamaPresenter.onMasukLogin(hakAkses);
            }
        });
    }

    @Override
    public void onPindahHalaman(String hakAkses) {
        Intent intent = new Intent(MainActivity.this, HalamanLoginActivity.class);
        intent.putExtra(EXTRA_HAK_AKSES, hakAkses);
        startActivity(intent);
    }
}
