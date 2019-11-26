package com.example.tababsensiapp.Activities.Admin.AkunSaya.view;

public interface IAdminAkunSayaView {
    void initActionBar();

    void setNilaiDefault(String nama, String username, String foto);

    void onSuccessMessage(String message);

    void onErrorMessage(String message);

    void showDialogUpdate();

    void backPressed();
}
