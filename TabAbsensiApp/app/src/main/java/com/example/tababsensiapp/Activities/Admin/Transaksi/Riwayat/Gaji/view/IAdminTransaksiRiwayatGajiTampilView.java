package com.example.tababsensiapp.Activities.Admin.Transaksi.Riwayat.Gaji.view;

import com.example.tababsensiapp.Models.Penggajian;

import java.util.ArrayList;

public interface IAdminTransaksiRiwayatGajiTampilView {
    void initActionBar();

    void onSetupListView(ArrayList<Penggajian> dataModelArrayList);

    void onSucceessMessage(String message);

    void onErrorMessage(String message);
}
