package com.example.tababsenapp.Fitur.HalamanFormPengajar.Tambah.view;

import android.view.MenuItem;

public interface IFormTambahPengajarView {
    void initActionBar();

    void onSubmitSuccess(String message);

    void onSubmitError(String message);

    void showDialog();

    void backPressed();
}
