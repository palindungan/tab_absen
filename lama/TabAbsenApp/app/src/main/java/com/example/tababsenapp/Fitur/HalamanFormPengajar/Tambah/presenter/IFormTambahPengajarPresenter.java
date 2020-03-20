package com.example.tababsenapp.Fitur.HalamanFormPengajar.Tambah.presenter;

import android.graphics.Bitmap;

public interface IFormTambahPengajarPresenter {
    void onSubmitPengajar(String nama, String username, String password, String konfirmasi_password, String alamat, String no_hp, String foto);

    String getStringImage(Bitmap bitmap);
}
