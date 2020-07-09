package com.example.tababsenapp.Fitur.HalamanFormWaliMurid.Tambah.view;

public interface IFormTambahWaliMuridView {
    void initActionBar();

    void onSubmitSuccess(String message);

    void onSubmitError(String message);

    void showDialog();

    void backPressed();
}
