package com.its.bigstars.Activities.Akun.Admin.presenter;

import android.graphics.Bitmap;

public interface IAkunAdminPresenter {
    void onUpdate(String id_admin, String nama, String username, String password, String foto);

    String getStringImage(Bitmap bitmap);
}