package com.example.tababsensiapp.Activities.Admin.MataPelajaran.Edit.view;

public interface IAdminMataPelajaranEditView {
    void initActionBar();

    void setNilaiDefault(String nama);

    void onSuccessMessage(String message);

    void onErrorMessage(String message);

    void showDialogUpdate();

    void showDialogDelete();

    void backPressed();
}
