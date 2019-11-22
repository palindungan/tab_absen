package com.example.tababsensiapp.Activities.Admin.MataPelajaran.Tambah.view;

public interface IAdminMataPelajaranTambahView {
    void initActionBar();

    void onSubmitSuccess(String message);

    void onSubmitError(String message);

    void showDialog();

    void backPressed();
}
