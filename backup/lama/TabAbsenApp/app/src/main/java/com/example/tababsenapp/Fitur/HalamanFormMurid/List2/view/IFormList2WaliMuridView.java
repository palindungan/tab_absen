package com.example.tababsenapp.Fitur.HalamanFormMurid.List2.view;

import com.example.tababsenapp.Model.waliMurid.WaliMurid;

import java.util.ArrayList;

public interface IFormList2WaliMuridView {
    void initActionBar();

    void onSetupListView(ArrayList<WaliMurid> dataModelArrayList);

    void onSuccessMessage(String message);

    void onErrorMessage(String message);

    void onInfoMessage(String message);

    void showDialog(String id_wali_murid, String nama_wali_murid, String alamat);

    void stepFinish();
}
