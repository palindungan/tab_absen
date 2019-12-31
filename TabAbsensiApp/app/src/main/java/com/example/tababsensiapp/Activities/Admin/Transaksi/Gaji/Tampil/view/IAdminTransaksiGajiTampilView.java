package com.example.tababsensiapp.Activities.Admin.Transaksi.Gaji.Tampil.view;

import com.example.tababsensiapp.Models.Pertemuan;

import java.util.ArrayList;

public interface IAdminTransaksiGajiTampilView {
    void initActionBar();

    void onSetupListView(ArrayList<Pertemuan> dataModelArrayList , String nama_pengajar,String total_pertemuan, String harga_fee);

    void onSuccessMessage(String message);

    void onErrorMessage(String message);

    void showDialogTransaksi();
}
