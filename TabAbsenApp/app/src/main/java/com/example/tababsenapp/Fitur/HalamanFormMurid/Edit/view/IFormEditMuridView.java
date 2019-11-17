package com.example.tababsenapp.Fitur.HalamanFormMurid.Edit.view;

public interface IFormEditMuridView {
    void initActionBar();

    void setNilaiDefault(String nama,String nama_wali_murid, String alamat, String foto);

    void onSucceessMessage(String message);

    void onErrorMessage(String message);

    void showDialogUpdate();

    void showDialogDelete();

    void backPressed();
}
