package com.example.tababsensiapp.Activities.Admin.Transaksi.Riwayat.SPP.Tampil.view;

import com.example.tababsensiapp.Models.BayarSpp;

import java.util.ArrayList;

public interface IAdminTransaksiRiwayatSppTampilView {
    void initActionBar();

    void onSetupListView(ArrayList<BayarSpp> dataModelArrayList);

    void onSuccessMessage(String message);

    void onErrorMessage(String message);
}
