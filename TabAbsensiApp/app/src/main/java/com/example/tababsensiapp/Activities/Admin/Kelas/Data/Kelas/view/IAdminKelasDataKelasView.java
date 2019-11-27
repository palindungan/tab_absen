package com.example.tababsensiapp.Activities.Admin.Kelas.Data.Kelas.view;

public interface IAdminKelasDataKelasView {
    void initActionBar();

    void setNilaiDefault(String id_mata_pelajaran,String hari, String jam_mulai, String jam_berakhir, String harga_fee, String id_sharing, String nama_sharing);

    void onSuccessMessage(String message);

    void onErrorMessage(String message);

    void showDialogUpdate();

    void showDialogDelete();

    void backPressed();
}
