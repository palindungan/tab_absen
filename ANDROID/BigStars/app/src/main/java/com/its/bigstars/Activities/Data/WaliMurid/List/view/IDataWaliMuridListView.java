package com.its.bigstars.Activities.Data.WaliMurid.List.view;

import com.its.bigstars.Models.WaliMurid;

import java.util.ArrayList;

public interface IDataWaliMuridListView {
    void onSetupListView(ArrayList<WaliMurid> dataModelArrayList);

    void showDialogDelete(String kode, String nama);
}
