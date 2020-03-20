package com.example.tababsensiapp.Activities.Admin.Murid.Tambah.Step2.view;

import com.example.tababsensiapp.Models.WaliMurid;

import java.util.ArrayList;

public interface IAdminMuridTambahStep2View {
    void initActionBar();

    void onSetupListView(ArrayList<WaliMurid> dataModelArrayList);

    void onSuccessMessage(String message);

    void onErrorMessage(String message);

    void onInfoMessage(String message);

    void showDialog(String id_wali_murid, String nama_wali_murid, String alamat);

    void finish();
}
