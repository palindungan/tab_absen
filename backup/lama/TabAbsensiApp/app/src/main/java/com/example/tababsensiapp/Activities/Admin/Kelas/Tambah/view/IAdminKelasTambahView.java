package com.example.tababsensiapp.Activities.Admin.Kelas.Tambah.view;

import com.example.tababsensiapp.Models.MataPelajaran;

import java.util.ArrayList;

public interface IAdminKelasTambahView {
    void initActionBar();

    void onSetupListView(ArrayList<MataPelajaran> dataModelArrayList);

    void onSubmitSuccess(String message);

    void onSubmitError(String message);

    void showDialog();

    void showHoursPicker();

    void showHoursPicker2();

    void backPressed();
}
