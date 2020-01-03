package com.example.tababsensiapp.Activities.Admin.Kelas.Edit.presenter;

public interface IAdminKelasEditHargaPresenter {
    void inisiasiAwal(String id_kelas_p);

    void onUpdate(String id_kelas_p, String harga_fee, String harga_spp);
}
