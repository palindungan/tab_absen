package com.example.tababsensiapp.Activities.Pengajar.Kelas.TampilSemua;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.example.tababsensiapp.Activities.Pengajar.Kelas.TampilSemua.view.IPengajarKelasTampilSemuaView;
import com.example.tababsensiapp.Models.Kelas;
import com.example.tababsensiapp.R;

import java.util.ArrayList;

public class PengajarKelasTampilSemuaActivity extends AppCompatActivity implements View.OnClickListener, IPengajarKelasTampilSemuaView {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pengajar_kelas_tampil_semua);
    }

    @Override
    public void onClick(View v) {

    }

    @Override
    public void initActionBar() {

    }

    @Override
    public void onSetupListView(ArrayList<Kelas> dataModelArrayList) {

    }

    @Override
    public void onSuccessMessage(String message) {

    }

    @Override
    public void onErrorMessage(String message) {

    }
}
