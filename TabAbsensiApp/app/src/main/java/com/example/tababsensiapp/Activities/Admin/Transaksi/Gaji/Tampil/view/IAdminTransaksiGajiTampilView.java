package com.example.tababsensiapp.Activities.Admin.Transaksi.Gaji.Tampil.view;

import com.example.tababsensiapp.Models.Pertemuan;

import java.util.ArrayList;

public interface IAdminTransaksiGajiTampilView {
    void initActionBar();

    void onSetupListView(ArrayList<Pertemuan> dataModelArrayList);

    void onSuccessMessage(String message);

    void onErrorMessage(String message);
}
