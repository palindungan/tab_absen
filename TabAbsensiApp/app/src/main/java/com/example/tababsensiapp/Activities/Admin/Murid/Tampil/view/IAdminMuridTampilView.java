package com.example.tababsensiapp.Activities.Admin.Murid.Tampil.view;

import com.example.tababsensiapp.Models.Murid;

import java.util.ArrayList;

public interface IAdminMuridTampilView {
    void initActionBar();

    void onSetupListView(ArrayList<Murid> dataModelArrayList);

    void onSucceessMessage(String message);

    void onErrorMessage(String message);
}
