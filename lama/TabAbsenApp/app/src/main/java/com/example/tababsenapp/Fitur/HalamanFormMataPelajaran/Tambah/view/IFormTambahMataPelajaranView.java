package com.example.tababsenapp.Fitur.HalamanFormMataPelajaran.Tambah.view;

public interface IFormTambahMataPelajaranView {
    void initActionBar();

    void onSubmitSuccess(String message);

    void onSubmitError(String message);

    void showDialog();

    void backPressed();
}
