package com.example.tababsensiapp.Activities.Admin.WaliMurid.Tambah.view;

public interface IAdminWaliMuridTambahView {
    void initActionBar();

    void onSubmitSuccess(String message);

    void onSubmitError(String message);

    void showDialog();

    void backPressed();
}
