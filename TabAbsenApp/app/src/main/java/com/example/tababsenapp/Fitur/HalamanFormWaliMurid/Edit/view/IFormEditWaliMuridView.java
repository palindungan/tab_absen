package com.example.tababsenapp.Fitur.HalamanFormWaliMurid.Edit.view;

public interface IFormEditWaliMuridView {
    void initActionBar();

    void setNilaiDefault(String nama, String username, String alamat, String no_hp);

    void onSucceessMessage(String message);

    void onErrorMessage(String message);

    void showDialogUpdate();

    void showDialogDelete();

    void backPressed();
}
