package com.example.tababsenapp.Fitur.HalamanFormPengajar.Edit.view;

public interface IFormEditPengajarView {
    void initActionBar();

    void setNilaiDefault(String nama, String username, String alamat, String no_hp, String foto);

    void onSucceessMessage(String message);

    void onErrorMessage(String message);

    void showDialogUpdate();

    void showDialogDelete();

    void backPressed();
}
