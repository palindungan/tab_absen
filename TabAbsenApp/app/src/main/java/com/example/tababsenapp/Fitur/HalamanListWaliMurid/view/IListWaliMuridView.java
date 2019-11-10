package com.example.tababsenapp.Fitur.HalamanListWaliMurid.view;

import com.example.tababsenapp.Model.waliMurid.WaliMurid;

import java.util.ArrayList;

public interface IListWaliMuridView {
    void initActionBar();

    void onSetupLisView(ArrayList<WaliMurid> dataModelArrayList);

    void onSuccessMessage();

    void onErrorMessage();
}
