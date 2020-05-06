package com.its.bigstars.Activities.Data.MataPelajaran.List.view;

import com.its.bigstars.Models.MataPelajaran;

import java.util.ArrayList;

public interface IDataMataPelajaranListView {
    void onSetupListView(ArrayList<MataPelajaran> dataModelArrayList);

    void showDialogDelete(String kode, String nama);
}
