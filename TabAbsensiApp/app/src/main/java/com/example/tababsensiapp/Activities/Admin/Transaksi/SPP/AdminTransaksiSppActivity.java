package com.example.tababsensiapp.Activities.Admin.Transaksi.SPP;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.example.tababsensiapp.Activities.Admin.Transaksi.SPP.presenter.IAdminTransaksiSppPresenter;
import com.example.tababsensiapp.R;

public class AdminTransaksiSppActivity extends AppCompatActivity implements View.OnClickListener, IAdminTransaksiSppPresenter {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_transaksi_spp);
    }

    @Override
    public void onClick(View v) {
        
    }
}
