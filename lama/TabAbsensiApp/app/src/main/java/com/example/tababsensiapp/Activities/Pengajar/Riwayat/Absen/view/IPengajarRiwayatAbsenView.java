package com.example.tababsensiapp.Activities.Pengajar.Riwayat.Absen.view;

import com.example.tababsensiapp.Models.Pertemuan;

import java.util.ArrayList;

public interface IPengajarRiwayatAbsenView {
    void initActionBar();

    void onSetupListView(ArrayList<Pertemuan> dataModelArrayList);

    void onSuccessMessage(String message);

    void onErrorMessage(String message);
}
