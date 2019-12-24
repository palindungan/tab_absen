package com.example.tababsensiapp.Activities.Admin.Transaksi.Gaji.presenter;

import android.content.Context;

import com.example.tababsensiapp.Activities.Admin.Transaksi.Gaji.view.IAdminTransaksiGajiView;
import com.example.tababsensiapp.Controllers.BaseUrl;

public class AdminTransaksiGajiPresenter implements IAdminTransaksiGajiPresenter {

    IAdminTransaksiGajiView adminTransaksiGajiView;
    Context context;

    BaseUrl baseUrl;

    public AdminTransaksiGajiPresenter(IAdminTransaksiGajiView adminTransaksiGajiView, Context context) {
        this.adminTransaksiGajiView = adminTransaksiGajiView;
        this.context = context;

        baseUrl = new BaseUrl();
    }
}
