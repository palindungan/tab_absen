package com.example.tababsensiapp.Activities.Admin.Kelas.Detail.Murid.Tampil.view;

import com.example.tababsensiapp.Models.Murid;

import java.util.ArrayList;

public interface IAdminKelasDetailMuridTampilView {
    void initActionBar();

    void onSetupListView(ArrayList<Murid> dataModelArrayList);

    void onSuccessMessage(String message);

    void onErrorMessage(String message);
}
