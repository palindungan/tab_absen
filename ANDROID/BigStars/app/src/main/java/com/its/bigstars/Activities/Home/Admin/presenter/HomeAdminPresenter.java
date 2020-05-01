package com.its.bigstars.Activities.Home.Admin.presenter;

import android.content.Context;

import com.its.bigstars.Activities.Home.Admin.view.IHomeAdminView;

public class HomeAdminPresenter implements IHomeAdminPresenter {
    Context context;
    IHomeAdminView homeAdminView;

    public HomeAdminPresenter(Context context, IHomeAdminView homeAdminView) {
        this.context = context;
        this.homeAdminView = homeAdminView;
    }
}
