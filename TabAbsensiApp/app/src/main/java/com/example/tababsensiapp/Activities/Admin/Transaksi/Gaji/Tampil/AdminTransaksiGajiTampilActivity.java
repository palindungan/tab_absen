package com.example.tababsensiapp.Activities.Admin.Transaksi.Gaji.Tampil;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.example.tababsensiapp.Activities.Admin.Transaksi.Gaji.Tampil.view.IAdminTransaksiGajiTampilView;
import com.example.tababsensiapp.R;

public class AdminTransaksiGajiTampilActivity extends AppCompatActivity implements View.OnClickListener, IAdminTransaksiGajiTampilView {

    public final static String EXTRA_ID_PENGAJAR = "EXTRA_ID_PENGAJAR";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_transaksi_gaji);
    }

    @Override
    public void onClick(View v) {

    }
}
