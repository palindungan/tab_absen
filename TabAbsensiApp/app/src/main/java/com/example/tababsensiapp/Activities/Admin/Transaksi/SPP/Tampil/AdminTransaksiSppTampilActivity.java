package com.example.tababsensiapp.Activities.Admin.Transaksi.SPP.Tampil;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.example.tababsensiapp.Activities.Admin.Transaksi.SPP.Tampil.presenter.IAdminTransaksiSppTampilPresenter;
import com.example.tababsensiapp.R;

public class AdminTransaksiSppTampilActivity extends AppCompatActivity implements View.OnClickListener, IAdminTransaksiSppTampilPresenter {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_transaksi_spp);
    }

    @Override
    public void onClick(View v) {

    }
}
