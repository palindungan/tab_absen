package com.example.tababsensiapp.Activities.Pengajar.Kelas.TampilSemua.view;

import com.example.tababsensiapp.Models.Kelas;

import java.util.ArrayList;

public interface IPengajarKelasTampilSemuaView {
    void initActionBar();

    void onSetupListView(ArrayList<Kelas> dataModelArrayList);

    void onSuccessMessage(String message);

    void onErrorMessage(String message);
}
