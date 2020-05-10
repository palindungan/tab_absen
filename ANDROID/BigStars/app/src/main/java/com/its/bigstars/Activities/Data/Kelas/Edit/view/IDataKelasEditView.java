package com.its.bigstars.Activities.Data.Kelas.Edit.view;

import com.its.bigstars.Models.MataPelajaran;
import com.its.bigstars.Models.Murid;

import java.util.ArrayList;

public interface IDataKelasEditView {
    void onSetupListView(ArrayList<MataPelajaran> dataModelArrayList);

    void backPressed();

    void onSetupListViewMurid(ArrayList<Murid> dataModelArrayList);

    void showDialogDeleteMurid(String kode, String nama);

    void onRefreshDataListMurid();
}
