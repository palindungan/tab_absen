package com.example.tababsenapp.Fitur.HalamanFormMurid.List.view;

import com.example.tababsenapp.Model.waliMurid.WaliMurid;

import java.util.ArrayList;

public interface IFormListWaliMuridView {
    void initActionBar();

    void onSetupListView(ArrayList<WaliMurid> dataModelArrayList);

    void onSuccessMessage(String message);

    void onErrorMessage(String message);

    void onInfoMessage(String message);

    void showDialog(String id_wali_murid, String nama_wali_murid, String alamat);

    void finish();
}
