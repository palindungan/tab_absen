package com.example.tababsensiapp.Activities.Admin.Pengajar.Edit.view;

public interface IAdminPengajarEditView {
    void initActionBar();

    void setNilaiDefault(String nama, String username, String alamat, String no_hp, String foto);

    void onSuccessMessage(String message);

    void onErrorMessage(String message);

    void showDialogUpdate();

    void showDialogDelete();

    void backPressed();
}
