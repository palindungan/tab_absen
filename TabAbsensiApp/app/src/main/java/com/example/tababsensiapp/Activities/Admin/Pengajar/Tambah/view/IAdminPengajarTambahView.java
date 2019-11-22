package com.example.tababsensiapp.Activities.Admin.Pengajar.Tambah.view;

public interface IAdminPengajarTambahView {
    void initActionBar();

    void onSubmitSuccess(String message);

    void onSubmitError(String message);

    void showDialog();

    void backPressed();
}
