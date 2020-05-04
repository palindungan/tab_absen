package com.its.bigstars.Activities.Data.Pengajar.List.view;

import com.its.bigstars.Models.Pengajar;

import java.util.ArrayList;

public interface IDataPengajarListView {
    void onSetupListView(ArrayList<Pengajar> dataModelArrayList);

    void showDialogDelete(String kode, String nama);
}
