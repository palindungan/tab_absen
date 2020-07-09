package com.example.tababsensiapp.Activities.Admin.Kelas.Edit.view;

public interface IAdminKelasEditHargaView {
    void initActionBar();

    void setNilaiDefault(String harga_fee, String harga_spp);

    void onSuccessMessage(String message);

    void onErrorMessage(String message);

    void showDialogUpdate();

    void backPressed();
}
