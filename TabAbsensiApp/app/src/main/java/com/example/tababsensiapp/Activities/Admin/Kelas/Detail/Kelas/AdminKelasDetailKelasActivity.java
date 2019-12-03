package com.example.tababsensiapp.Activities.Admin.Kelas.Detail.Kelas;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.example.tababsensiapp.Activities.Admin.Kelas.Detail.Kelas.view.IAdminKelasDetailKelasView;
import com.example.tababsensiapp.Models.Murid;
import com.example.tababsensiapp.R;

import java.util.ArrayList;

public class AdminKelasDetailKelasActivity extends AppCompatActivity implements View.OnClickListener , IAdminKelasDetailKelasView {

    public static final String EXTRA_ID_KELAS_P = "EXTRA_ID_KELAS_P";
    public static final String EXTRA_HARI = "EXTRA_HARI";
    public static final String EXTRA_JAM_MULAI = "EXTRA_JAM_MULAI";
    public static final String EXTRA_JAM_BERAKHIR = "EXTRA_JAM_BERAKHIR";
    public static final String EXTRA_HARGA_FEE = "EXTRA_HARGA_FEE";
    public static final String EXTRA_ID_MATA_PELAJARAN = "EXTRA_ID_MATA_PELAJARAN";
    public static final String EXTRA_NAMA_PELAJARAN = "EXTRA_NAMA_PELAJARAN";
    public static final String EXTRA_ID_PENGAJAR = "EXTRA_ID_PENGAJAR";
    public static final String EXTRA_NAMA_PENGAJAR = "EXTRA_NAMA_PENGAJAR";
    public static final String EXTRA_ID_SHARING = "EXTRA_ID_SHARING";
    public static final String EXTRA_NAMA_SHARING = "EXTRA_NAMA_SHARING";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_kelas_detail_kelas);
    }

    @Override
    public void onClick(View v) {

    }

    @Override
    public void initActionBar() {

    }

    @Override
    public void setNilaiDefault(String nama_pelajaran, String hari, String jam_mulai, String jam_berakhir, String harga_fee) {

    }

    @Override
    public void onSuccessMessage(String message) {

    }

    @Override
    public void onErrorMessage(String message) {

    }

    @Override
    public void showDialogDelete() {

    }

    @Override
    public void backPressed() {

    }

    @Override
    public void onSetupListView(ArrayList<Murid> dataModelArrayList) {

    }
}
