package com.example.tababsenapp.Fitur.HalamanFormMataPelajaran.Edit.view;

public interface IFormEditMataPelajaranView {
    void initActionBar();

    void setNilaiDefault(String nama);

    void onSucceessMessage(String message);

    void onErrorMessage(String message);

    void showDialogUpdate();

    void showDialogDelete();

    void backPressed();
}
