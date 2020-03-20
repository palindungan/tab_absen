package com.example.tababsensiapp.Activities.Pengajar.Home.presenter;

import android.content.Context;

import com.example.tababsensiapp.Activities.Pengajar.Home.view.IPengajarHomeView;
import com.example.tababsensiapp.Controllers.BaseUrl;
import com.example.tababsensiapp.Controllers.SessionManager;

public class PengajarHomePresenter implements IPengajarHomePresenter {

    Context context;
    IPengajarHomeView pengajarHomeView;

    SessionManager sessionManager;
    BaseUrl baseUrl;

    public PengajarHomePresenter(Context context, IPengajarHomeView pengajarHomeView) {
        this.context = context;
        this.pengajarHomeView = pengajarHomeView;

        sessionManager = new SessionManager(context);
        baseUrl = new BaseUrl();
    }
}
