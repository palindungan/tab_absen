package com.example.tababsensiapp.Activities.Pengajar.AbsensiPertemuan;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.example.tababsensiapp.Activities.Pengajar.AbsensiPertemuan.view.IPengajarAbsensiPertemuanView;
import com.example.tababsensiapp.R;

public class PengajarAbsensiPertemuanActivity extends AppCompatActivity implements View.OnClickListener , IPengajarAbsensiPertemuanView {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pengajar_absensi_pertemuan);
    }

    @Override
    public void onClick(View v) {

    }
}
