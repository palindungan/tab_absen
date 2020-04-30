package com.its.bigstars.Activities._Login.presenter;

import android.content.Context;

import com.its.bigstars.Activities._Login.view.ILoginView;

public class LoginPresenter implements ILoginPresenter {
    Context context;
    ILoginView loginView;

    public LoginPresenter(Context context, ILoginView loginView) {
        this.context = context;
        this.loginView = loginView;
    }
}
