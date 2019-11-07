package com.example.tababsenapp.Fitur.HalamanFormPengajar.Edit.presenter;

import android.graphics.Bitmap;

public interface IFormEditPengajarPresenter {
    void inisiasiAwal(String id_pengajar);

    void onUpdatePengajar(String nama, String username, String password, String konfirmasi_password, String alamat, String no_hp, String foto);

    String getStringImage(Bitmap bitmap);
}
