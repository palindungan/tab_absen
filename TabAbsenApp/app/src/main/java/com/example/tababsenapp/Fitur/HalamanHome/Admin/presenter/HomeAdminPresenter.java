package com.example.tababsenapp.Fitur.HalamanHome.Admin.presenter;

import android.content.Context;

import com.example.tababsenapp.Controllers.SessionManager;
import com.example.tababsenapp.Fitur.HalamanHome.Admin.view.IHomeAdminView;

public class HomeAdminPresenter implements IHomeAdminPresenter {

    IHomeAdminView homeAdminView;
    Context context;

    SessionManager sessionManager;

    public HomeAdminPresenter(IHomeAdminView homeAdminView, Context context) {
        this.homeAdminView = homeAdminView;
        this.context = context;
    }


    @Override
    public int onCountPengajar() {
        return 0;
    }

    @Override
    public int onCountMurid() {
        return 0;
    }

    @Override
    public int onCountWaliMurid() {
        return 0;
    }

    @Override
    public int onCountMataPelajaran() {
        return 0;
    }

    @Override
    public int onCountkelas() {
        return 0;
    }

    @Override
    public int onCountKelasAktif() {
        return 0;
    }

    @Override
    public int onCountNotifikasi() {
        return 0;
    }
}
