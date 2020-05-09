package com.its.bigstars.Activities.Data.Kelas.Add.view;

import com.its.bigstars.Models.MataPelajaran;

import java.util.ArrayList;

public interface IDataKelasAddView {
    void backPressed();

    void onSetupListView(ArrayList<MataPelajaran> dataModelArrayList);
}
