package com.its.bigstars.Activities.Akun.Admin.presenter;

import android.content.Context;
import android.graphics.Bitmap;

import com.its.bigstars.Activities.Akun.Admin.view.IAkunAdminView;

public class AkunAdminPresenter implements IAkunAdminPresenter {
    Context context;
    IAkunAdminView akunAdminView;

    public AkunAdminPresenter(Context context, IAkunAdminView akunAdminView) {
        this.context = context;
        this.akunAdminView = akunAdminView;
    }

    @Override
    public void inisiasiAwal(String id_admin) {

    }

    @Override
    public void onUpdate(String id_admin, String nama, String username, String password, String foto) {

    }

    @Override
    public String getStringImage(Bitmap bitmap) {
        return null;
    }
}
