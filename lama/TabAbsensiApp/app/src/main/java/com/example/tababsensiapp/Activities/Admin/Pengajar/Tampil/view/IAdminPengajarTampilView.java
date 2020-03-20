package com.example.tababsensiapp.Activities.Admin.Pengajar.Tampil.view;

import com.example.tababsensiapp.Models.Pengajar;

import java.util.ArrayList;

public interface IAdminPengajarTampilView {
    void initActionBar();

    void onSetupListView(ArrayList<Pengajar> dataModelArrayList);

    void onSucceessMessage(String message);

    void onErrorMessage(String message);
}
