package com.its.bigstars.Activities.Data.Kelas.List.view;

import com.its.bigstars.Models.Kelas;

import java.util.ArrayList;

public interface IDataKelasListView {
    void onSetupListView(ArrayList<Kelas> dataModelArrayList);

    void showDialogDelete(String kode, String nama);

    void onRefreshDataList();
}
