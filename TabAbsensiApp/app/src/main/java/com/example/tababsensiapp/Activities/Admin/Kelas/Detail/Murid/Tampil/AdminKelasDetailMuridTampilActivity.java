package com.example.tababsensiapp.Activities.Admin.Kelas.Detail.Murid.Tampil;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.example.tababsensiapp.Activities.Admin.Kelas.Detail.Murid.Tampil.view.IAdminKelasDetailMuridTampilView;
import com.example.tababsensiapp.Models.Murid;
import com.example.tababsensiapp.R;

import java.util.ArrayList;

public class AdminKelasDetailMuridTampilActivity extends AppCompatActivity implements View.OnClickListener, IAdminKelasDetailMuridTampilView {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_kelas_detail_murid_tampil);
    }

    @Override
    public void onClick(View v) {

    }

    @Override
    public void initActionBar() {

    }

    @Override
    public void onSetupListView(ArrayList<Murid> dataModelArrayList) {

    }

    @Override
    public void onSuccessMessage(String message) {

    }

    @Override
    public void onErrorMessage(String message) {

    }
}
