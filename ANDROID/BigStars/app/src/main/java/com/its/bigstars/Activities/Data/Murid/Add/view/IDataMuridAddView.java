package com.its.bigstars.Activities.Data.Murid.Add.view;

import com.its.bigstars.Models.WaliMurid;

import java.util.ArrayList;

public interface IDataMuridAddView {
    void backPressed();

    void onSetupListView(ArrayList<WaliMurid> dataModelArrayList);
}
