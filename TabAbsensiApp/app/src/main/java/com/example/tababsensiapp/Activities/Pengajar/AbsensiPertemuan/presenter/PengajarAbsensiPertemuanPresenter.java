package com.example.tababsensiapp.Activities.Pengajar.AbsensiPertemuan.presenter;

import android.content.Context;

import com.example.tababsensiapp.Activities.Pengajar.AbsensiPertemuan.view.IPengajarAbsensiPertemuanView;
import com.example.tababsensiapp.Controllers.BaseUrl;
import com.example.tababsensiapp.Controllers.SessionManager;

public class PengajarAbsensiPertemuanPresenter implements IPengajarAbsensiPertemuanPresenter {

    Context context;
    IPengajarAbsensiPertemuanView pengajarAbsensiPertemuanView;

    SessionManager sessionManager;
    BaseUrl baseUrl;

    public PengajarAbsensiPertemuanPresenter(Context context, IPengajarAbsensiPertemuanView pengajarAbsensiPertemuanView) {
        this.context = context;
        this.pengajarAbsensiPertemuanView = pengajarAbsensiPertemuanView;

        sessionManager = new SessionManager(context);
        baseUrl = new BaseUrl();
    }
}
