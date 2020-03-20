package com.example.tababsenapp.Fitur.HalamanListMatapelajaran.view;

import com.example.tababsenapp.Model.mataPelajaran.MataPelajaran;
import com.example.tababsenapp.Model.waliMurid.WaliMurid;

import java.util.ArrayList;

public interface IListMataPelajaranView {
    void initActionBar();

    void onSetupListView(ArrayList<MataPelajaran> dataModelArrayList);

    void onSucceessMessage(String message);

    void onErrorMessage(String message);
}
