package com.example.tababsenapp.Fitur.HalamanLogin.presenter;

import com.example.tababsenapp.Fitur.HalamanLogin.view.ILoginView;

public class LoginPresenter implements ILoginPresenter {

    ILoginView loginView;

    public LoginPresenter(ILoginView loginView) {
        this.loginView = loginView;
    }

    @Override
    public void onLogin(String username, String Password) {

    }
}
