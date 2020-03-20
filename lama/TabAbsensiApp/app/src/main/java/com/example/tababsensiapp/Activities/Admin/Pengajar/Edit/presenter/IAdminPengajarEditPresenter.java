package com.example.tababsensiapp.Activities.Admin.Pengajar.Edit.presenter;

import android.graphics.Bitmap;

public interface IAdminPengajarEditPresenter {
    void inisiasiAwal(String id_pengajar);

    void onUpdate(String id_pengajar, String nama, String username, String password, String alamat, String no_hp, String foto);

    String getStringImage(Bitmap bitmap);

    void hapusAkun(String id);
}
