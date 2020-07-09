package com.example.tababsenapp.Fitur.HalamanListMurid.view;

import com.example.tababsenapp.Model.murid.Murid;

import java.util.ArrayList;

public interface IListMuridView {
    void initActionBar();

    void onSetupListView(ArrayList<Murid> dataModelArrayList);

    void onSucceessMessage(String message);

    void onErrorMessage(String message);
}
