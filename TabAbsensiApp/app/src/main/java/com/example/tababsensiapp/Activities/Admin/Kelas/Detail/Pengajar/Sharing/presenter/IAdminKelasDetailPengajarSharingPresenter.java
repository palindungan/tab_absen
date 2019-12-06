package com.example.tababsensiapp.Activities.Admin.Kelas.Detail.Pengajar.Sharing.presenter;

public interface IAdminKelasDetailPengajarSharingPresenter {
    void onLoadSemuaData();

    void onUpdate(String id_kelas_p, String id_sharing, String nama_sharing);
}
