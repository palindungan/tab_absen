package com.example.tababsenapp.Fitur.HalamanFormMurid.Tambah.presenter;

import android.graphics.Bitmap;

public interface IFormTambahMuridPresenter {
    void onClickNext(String nama, String foto);

    String getStringImage(Bitmap bitmap);
}
