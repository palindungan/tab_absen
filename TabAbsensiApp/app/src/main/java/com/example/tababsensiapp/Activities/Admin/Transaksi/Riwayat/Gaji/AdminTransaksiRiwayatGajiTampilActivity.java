package com.example.tababsensiapp.Activities.Admin.Transaksi.Riwayat.Gaji;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.example.tababsensiapp.Activities.Admin.Transaksi.Riwayat.Gaji.view.IAdminTransaksiRiwayatGajiTampilView;
import com.example.tababsensiapp.Models.Penggajian;
import com.example.tababsensiapp.R;

import java.util.ArrayList;

public class AdminTransaksiRiwayatGajiTampilActivity extends AppCompatActivity implements View.OnClickListener, IAdminTransaksiRiwayatGajiTampilView {

    public static final String EXTRA_ID_PENGAJAR = "EXTRA_ID_PENGAJAR";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_transaksi_riwayat_gaji_tampil);
    }

    @Override
    public void onClick(View v) {

    }

    @Override
    public void initActionBar() {

    }

    @Override
    public void onSetupListView(ArrayList<Penggajian> dataModelArrayList) {

    }

    @Override
    public void onSucceessMessage(String message) {

    }

    @Override
    public void onErrorMessage(String message) {

    }
}
