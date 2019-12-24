package com.example.tababsensiapp.Activities.Admin.Transaksi.Gaji.Tampil.presenter;

import android.content.Context;

import com.example.tababsensiapp.Activities.Admin.Transaksi.Gaji.Tampil.view.IAdminTransaksiGajiTampilView;
import com.example.tababsensiapp.Controllers.BaseUrl;

public class AdminTransaksiGajiTampilPresenter implements IAdminTransaksiGajiTampilPresenter {

    IAdminTransaksiGajiTampilView adminTransaksiGajiView;
    Context context;

    BaseUrl baseUrl;

    public AdminTransaksiGajiTampilPresenter(IAdminTransaksiGajiTampilView adminTransaksiGajiView, Context context) {
        this.adminTransaksiGajiView = adminTransaksiGajiView;
        this.context = context;

        baseUrl = new BaseUrl();
    }
}
