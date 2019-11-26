package com.example.tababsensiapp.Activities.Admin.AkunSaya.presenter;

import android.graphics.Bitmap;

public interface IAdminAkunSayaPresenter {
    void onSubmit(String nama, String username, String password, String foto);

    String getStringImage(Bitmap bitmap);
}
