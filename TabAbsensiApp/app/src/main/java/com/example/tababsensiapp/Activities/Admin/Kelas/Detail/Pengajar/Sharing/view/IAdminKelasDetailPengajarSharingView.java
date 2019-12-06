package com.example.tababsensiapp.Activities.Admin.Kelas.Detail.Pengajar.Sharing.view;

import com.example.tababsensiapp.Models.Pengajar;

import java.util.ArrayList;

public interface IAdminKelasDetailPengajarSharingView {
    void initActionBar();

    void onSetupListView(ArrayList<Pengajar> dataModelArrayList);

    void onSucceessMessage(String message);

    void onErrorMessage(String message);
}
