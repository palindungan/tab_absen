package com.example.tababsensiapp.Activities.Admin.Kelas.Data.Kelas.presenter;

public interface IAdminKelasDataKelasPresenter {
    void inisiasiAwal(String id_kelas_p);

    void onUpdate(String id_kelas_p, String id_pengajar, String id_mata_pelajaran, String hari, String jam_mulai, String jam_berakhir, String harga_fee, String id_sharing, String nama_sharing);

    void hapusAkun(String id);
}
