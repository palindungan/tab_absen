package com.example.tababsensiapp.Activities.Admin.Kelas.Tampil.Kelas;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.example.tababsensiapp.Activities.Admin.Kelas.Tampil.Kelas.view.IAdminKelasTampilKelasView;
import com.example.tababsensiapp.R;

public class AdminKelasTampilKelasActivity extends AppCompatActivity implements View.OnClickListener , IAdminKelasTampilKelasView {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_kelas_tampil_kelas);
    }

    @Override
    public void onClick(View v) {

    }
}
