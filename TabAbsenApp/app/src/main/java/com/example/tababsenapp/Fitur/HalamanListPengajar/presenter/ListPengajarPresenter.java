package com.example.tababsenapp.Fitur.HalamanListPengajar.presenter;

import android.content.Context;

import com.example.tababsenapp.Controllers.SessionManager;
import com.example.tababsenapp.Fitur.HalamanListPengajar.view.IListPengajarView;

public class ListPengajarPresenter implements IListPengajarPresenter {

    IListPengajarView listPengajarView;
    Context context;

    SessionManager sessionManager;

    public ListPengajarPresenter(IListPengajarView listPengajarView, Context context) {
        this.listPengajarView = listPengajarView;
        this.context = context;
    }

    @Override
    public void onLoadSemuaListPengajar() {

    }
}
