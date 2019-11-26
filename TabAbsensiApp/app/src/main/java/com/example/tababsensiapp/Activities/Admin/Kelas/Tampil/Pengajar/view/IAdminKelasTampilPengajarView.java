package com.example.tababsensiapp.Activities.Admin.Kelas.Tampil.Pengajar.view;

import com.example.tababsensiapp.Models.Pengajar;

import java.util.ArrayList;

public interface IAdminKelasTampilPengajarView {
    void initActionBar();

    void onSetupListView(ArrayList<Pengajar> dataModelArrayList);

    void onSucceessMessage(String message);

    void onErrorMessage(String message);
}
