package com.example.tababsensiapp.Activities.Admin.Transaksi.Gaji;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.example.tababsensiapp.Activities.Admin.Transaksi.Gaji.view.IAdminTransaksiGajiView;
import com.example.tababsensiapp.R;

public class AdminTransaksiGajiActivity extends AppCompatActivity implements View.OnClickListener, IAdminTransaksiGajiView {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_transaksi_gaji);
    }

    @Override
    public void onClick(View v) {

    }
}
