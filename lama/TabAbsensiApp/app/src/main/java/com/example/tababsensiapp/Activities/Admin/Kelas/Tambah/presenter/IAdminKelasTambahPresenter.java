package com.example.tababsensiapp.Activities.Admin.Kelas.Tambah.presenter;

public interface IAdminKelasTambahPresenter {
    void onSubmit(String id_pengajar, String id_mata_pelajaran, String hari, String jam_mulai, String jam_berakhir, String harga_fee, String harga_spp);

    void onLoadSemuaData();
}
