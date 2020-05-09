package com.its.bigstars.Activities.Data.Kelas.Edit.view;

import com.its.bigstars.Models.MataPelajaran;

import java.util.ArrayList;

public interface IDataKelasEditView {
    void onSetupListView(ArrayList<MataPelajaran> dataModelArrayList);

    void backPressed();
}
