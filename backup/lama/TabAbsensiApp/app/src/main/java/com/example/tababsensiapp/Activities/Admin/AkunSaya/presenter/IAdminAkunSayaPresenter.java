package com.example.tababsensiapp.Activities.Admin.AkunSaya.presenter;

import android.graphics.Bitmap;

public interface IAdminAkunSayaPresenter {
    void inisiasiAwal(String id_admin);

    void onUpdate(String id_admin, String nama, String username, String password, String foto);

    String getStringImage(Bitmap bitmap);

}
