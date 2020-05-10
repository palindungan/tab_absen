package com.its.bigstars.Activities.Data.Kelas.Edit.view;

import com.its.bigstars.Models.MataPelajaran;
import com.its.bigstars.Models.Murid;

import java.util.ArrayList;

public interface IDataKelasEditView {
    void onSetupListViewPelajaranDialog(ArrayList<MataPelajaran> dataModelArrayList);

    void onSetupListViewMuridDialog(ArrayList<Murid> dataModelArrayList);

    void onSetupListViewMurid(ArrayList<Murid> dataModelArrayList);

    void showDialogDeleteMurid(String id_detail_kelas_p, String nama);

    void backPressed();

    void onRefreshDataListMurid();
}
