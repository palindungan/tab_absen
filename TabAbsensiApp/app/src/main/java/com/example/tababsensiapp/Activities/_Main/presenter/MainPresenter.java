package com.example.tababsensiapp.Activities._Main.presenter;

import android.content.Context;

import com.example.tababsensiapp.Activities._Main.view.IMainView;
import com.example.tababsensiapp.Controllers.BaseUrl;
import com.example.tababsensiapp.Controllers.SessionManager;

public class MainPresenter implements IMainPresenter {

    Context context;
    IMainView mainView;

    SessionManager sessionManager;
    BaseUrl baseUrl;

    String URL_STRING;

    public MainPresenter(Context context, IMainView mainView) {
        this.context = context;
        this.mainView = mainView;

        sessionManager = new SessionManager(context);
        baseUrl = new BaseUrl();

        URL_STRING = baseUrl.getUrlData();
    }

    @Override
    public void onLogin(String username, String password) {

    }
}
