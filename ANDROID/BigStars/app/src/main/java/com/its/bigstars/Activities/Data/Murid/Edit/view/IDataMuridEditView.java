package com.its.bigstars.Activities.Data.Murid.Edit.view;

import com.its.bigstars.Models.WaliMurid;

import java.util.ArrayList;

public interface IDataMuridEditView {
    void onSetupListView(ArrayList<WaliMurid> dataModelArrayList);

    void backPressed();
}
