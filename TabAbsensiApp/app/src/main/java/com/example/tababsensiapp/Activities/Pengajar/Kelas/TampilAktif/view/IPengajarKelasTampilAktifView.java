package com.example.tababsensiapp.Activities.Pengajar.Kelas.TampilAktif.view;

import com.example.tababsensiapp.Models.Pertemuan;

import java.util.ArrayList;

public interface IPengajarKelasTampilAktifView {
    void initActionBar();

    void onSetupListView(ArrayList<Pertemuan> dataModelArrayList);

    void onSuccessMessage(String message);

    void onErrorMessage(String message);
}
