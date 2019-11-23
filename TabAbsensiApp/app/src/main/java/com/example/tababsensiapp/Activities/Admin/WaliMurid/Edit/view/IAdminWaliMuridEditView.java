package com.example.tababsensiapp.Activities.Admin.WaliMurid.Edit.view;

public interface IAdminWaliMuridEditView {
    void initActionBar();

    void setNilaiDefault(String nama, String username, String alamat, String no_hp);

    void onSucceessMessage(String message);

    void onErrorMessage(String message);

    void showDialogUpdate();

    void showDialogDelete();

    void backPressed();
}
