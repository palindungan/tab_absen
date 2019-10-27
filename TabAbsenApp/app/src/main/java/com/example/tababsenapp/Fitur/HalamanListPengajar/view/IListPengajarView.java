package com.example.tababsenapp.Fitur.HalamanListPengajar.view;

import com.example.tababsenapp.Model.pengajar.Pengajar;

import java.util.ArrayList;

public interface IListPengajarView {
    void onSetupListView( ArrayList<Pengajar> dataModelArrayList);
    void onSucceessMessage(String message);
    void onErrorMessage(String message);
}
