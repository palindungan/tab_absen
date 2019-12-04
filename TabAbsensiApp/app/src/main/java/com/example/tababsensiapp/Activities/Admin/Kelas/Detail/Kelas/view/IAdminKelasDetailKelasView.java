package com.example.tababsensiapp.Activities.Admin.Kelas.Detail.Kelas.view;

import com.example.tababsensiapp.Models.Murid;

import java.util.ArrayList;

public interface IAdminKelasDetailKelasView {
    void initActionBar();

    void setNilaiDefault();

    void onSuccessMessage(String message);

    void onErrorMessage(String message);

    void backPressed();

    void onSetupListView(ArrayList<Murid> dataModelArrayList);
}
