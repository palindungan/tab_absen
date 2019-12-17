package com.example.tababsensiapp.Activities.Pengajar.Kelas.Detail;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.example.tababsensiapp.Activities.Pengajar.Kelas.Detail.view.IPengajarKelasDetailView;
import com.example.tababsensiapp.Models.Murid;
import com.example.tababsensiapp.R;

import java.util.ArrayList;

public class PengajarKelasDetailActivity extends AppCompatActivity implements View.OnClickListener, IPengajarKelasDetailView {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pengajar_kelas_detail);
    }

    @Override
    public void onClick(View v) {
        
    }

    @Override
    public void initActionBar() {

    }

    @Override
    public void setNilaiDefault(String nama_pelajaran, String nama_pengajar, String harga_fee, String hari, String jam_mulai, String jam_berakhir, String id_sharing, String nama_sharing) {

    }

    @Override
    public void onSuccessMessage(String message) {

    }

    @Override
    public void onErrorMessage(String message) {

    }

    @Override
    public void onSetupListView(ArrayList<Murid> dataModelArrayList) {

    }
}
