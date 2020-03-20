package com.example.tababsenapp.Fitur.HalamanFormMurid.Edit.view;

public interface IFormEditMuridView {
    void initActionBar();

    void setNilaiDefault(String nama, String id_wali_murid, String nama_wali_murid, String alamat, String foto);

    void onSucceessMessage(String message);

    void onErrorMessage(String message);

    void showDialogUbah();

    void showDialogUpdate();

    void showDialogDelete();

    void backPressed();
}
