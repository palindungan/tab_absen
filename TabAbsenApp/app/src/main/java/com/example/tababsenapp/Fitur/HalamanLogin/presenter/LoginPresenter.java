package com.example.tababsenapp.Fitur.HalamanLogin.presenter;

import com.example.tababsenapp.Fitur.HalamanLogin.view.ILoginView;

public class LoginPresenter implements ILoginPresenter {

    ILoginView loginView;

    public LoginPresenter(ILoginView loginView) {
        this.loginView = loginView;
    }

    @Override
    public void onLogin(String username, String Password) {
        String base_url = "http://localhost/tab_absen/web/api/";
        String URL_LOGIN = base_url+"login/"; // url http request
    }
}
