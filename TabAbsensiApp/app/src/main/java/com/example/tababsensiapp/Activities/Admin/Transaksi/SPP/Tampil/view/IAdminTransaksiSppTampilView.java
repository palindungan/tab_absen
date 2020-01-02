package com.example.tababsensiapp.Activities.Admin.Transaksi.SPP.Tampil.view;

import com.example.tababsensiapp.Models.BayarSpp;
import com.example.tababsensiapp.Models.Pertemuan;

import java.util.ArrayList;

public interface IAdminTransaksiSppTampilView {

    void initActionBar();

    void onSetupListView(ArrayList<Pertemuan> dataModelArrayList, String nama_pengajar, String total_pertemuan, String total_spp);

    void onSuccessMessage(String message);

    void onErrorMessage(String message);

    void showDialogTransaksi();

    void backPressed();

}
