package com.example.tababsensiapp.Activities.Admin.Kelas.Data.Kelas;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.example.tababsensiapp.Activities.Admin.Kelas.Data.Kelas.view.IAdminKelasDataKelasView;
import com.example.tababsensiapp.R;

public class AdminKelasDataKelasActivity extends AppCompatActivity implements View.OnClickListener, IAdminKelasDataKelasView {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_kelas_data_kelas);
    }

    @Override
    public void onClick(View v) {

    }

    @Override
    public void initActionBar() {

    }

    @Override
    public void setNilaiDefault(String id_mata_pelajaran, String hari, String jam_mulai, String jam_berakhir, String harga_fee, String id_sharing, String nama_sharing) {

    }

    @Override
    public void onSuccessMessage(String message) {

    }

    @Override
    public void onErrorMessage(String message) {

    }

    @Override
    public void showDialogUpdate() {

    }

    @Override
    public void showDialogDelete() {

    }

    @Override
    public void backPressed() {

    }
}
