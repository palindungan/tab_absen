package com.example.tababsenapp.Fitur.HalamanListWaliMurid.view;

import com.example.tababsenapp.Model.waliMurid.WaliMurid;

import java.util.ArrayList;

public interface IListWaliMuridView {
    void initActionBar();

    void onSetupListView(ArrayList<WaliMurid> dataModelArrayList);

    void onSuccessMessage(String message);

    void onErrorMessage(String message);
}
