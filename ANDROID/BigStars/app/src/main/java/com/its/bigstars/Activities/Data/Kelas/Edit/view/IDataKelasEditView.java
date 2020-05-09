package com.its.bigstars.Activities.Data.Kelas.Edit.view;

import com.its.bigstars.Models.Kelas;

import java.util.ArrayList;

public interface IDataKelasEditView {
    void onSetupListView(ArrayList<Kelas> dataModelArrayList);

    void backPressed();
}
