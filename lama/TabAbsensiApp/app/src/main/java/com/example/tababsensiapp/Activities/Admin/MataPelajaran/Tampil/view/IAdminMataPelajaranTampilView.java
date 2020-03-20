package com.example.tababsensiapp.Activities.Admin.MataPelajaran.Tampil.view;

import com.example.tababsensiapp.Models.MataPelajaran;

import java.util.ArrayList;

public interface IAdminMataPelajaranTampilView {
    void initActionBar();

    void onSetupListView(ArrayList<MataPelajaran> dataModelArrayList);

    void onSucceessMessage(String message);

    void onErrorMessage(String message);
}
