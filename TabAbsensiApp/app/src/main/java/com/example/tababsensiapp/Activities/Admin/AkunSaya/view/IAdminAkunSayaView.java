package com.example.tababsensiapp.Activities.Admin.AkunSaya.view;

public interface IAdminAkunSayaView {
    void initActionBar();

    void onSubmitSuccess(String message);

    void onSubmitError(String message);

    void showDialog();

    void backPressed();
}
