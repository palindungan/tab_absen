package com.example.tababsensiapp.Activities.Admin.Pengajar.Tambah.presenter;

import android.graphics.Bitmap;

public interface IAdminPengajarTambahPresenter {
    void onSubmit(String nama, String username, String password, String alamat, String no_hp, String foto);

    String getStringImage(Bitmap bitmap);
}
