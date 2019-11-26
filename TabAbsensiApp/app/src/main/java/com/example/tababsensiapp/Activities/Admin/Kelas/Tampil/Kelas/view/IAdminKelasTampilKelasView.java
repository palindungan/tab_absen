package com.example.tababsensiapp.Activities.Admin.Kelas.Tampil.Kelas.view;

import com.example.tababsensiapp.Models.Kelas;

import java.util.ArrayList;

public interface IAdminKelasTampilKelasView {
    void initActionBar();

    void onSetupListView(ArrayList<Kelas> dataModelArrayList);

    void onSucceessMessage(String message);

    void onErrorMessage(String message);

    void showDialogDelete();

    void backPressed();
}
