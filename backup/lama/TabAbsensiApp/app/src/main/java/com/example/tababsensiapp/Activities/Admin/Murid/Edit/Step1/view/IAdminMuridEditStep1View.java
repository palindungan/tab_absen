package com.example.tababsensiapp.Activities.Admin.Murid.Edit.Step1.view;

public interface IAdminMuridEditStep1View {
    void initActionBar();

    void setNilaiDefault(String nama, String id_wali_murid, String nama_wali_murid, String alamat, String foto);

    void onSuccessMessage(String message);

    void onErrorMessage(String message);

//    void showDialogUbah();
//
//    void showDialogUpdate();

    void showDialogDelete();

    void backPressed();
}
