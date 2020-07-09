package com.example.tababsensiapp.Activities.Admin.WaliMurid.Tampil.view;

import com.example.tababsensiapp.Models.WaliMurid;

import java.util.ArrayList;

public interface IAdminWaliMuridTampilView {
    void initActionBar();

    void onSetupListView(ArrayList<WaliMurid> dataModelArrayList);

    void onSuccessMessage(String message);

    void onErrorMessage(String message);
}
