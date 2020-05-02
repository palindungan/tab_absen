package com.its.bigstars.Activities.Data.Pengajar.Add.Presenter;

import android.graphics.Bitmap;

public interface IDataPengajarAddPresenter {
    void onSubmit(String nama, String username, String password, String alamat, String no_hp, String foto);

    String getStringImage(Bitmap bitmap);
}
