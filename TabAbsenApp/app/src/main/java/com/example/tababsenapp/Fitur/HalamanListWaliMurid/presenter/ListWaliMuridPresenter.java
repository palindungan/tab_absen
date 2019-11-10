package com.example.tababsenapp.Fitur.HalamanListWaliMurid.presenter;

import android.content.Context;

import com.example.tababsenapp.Controllers.SessionManager;
import com.example.tababsenapp.Fitur.HalamanListWaliMurid.view.IListWaliMuridView;
import com.example.tababsenapp.Model.waliMurid.WaliMurid;

import java.util.ArrayList;

public class ListWaliMuridPresenter implements IListWaliMuridPresenter {

    IListWaliMuridView listWaliMuridView;
    Context context;

    SessionManager sessionManager;

    ArrayList<WaliMurid> dataModelArrayList;

    String base_url;

    public ListWaliMuridPresenter(IListWaliMuridView listWaliMuridView, Context context) {
        this.listWaliMuridView = listWaliMuridView;
        this.context = context;

        sessionManager = new SessionManager(context);
        base_url = sessionManager.getBaseUrl();
    }

    @Override
    public void onLoadSemuaListWaliMurid() {

    }
}
