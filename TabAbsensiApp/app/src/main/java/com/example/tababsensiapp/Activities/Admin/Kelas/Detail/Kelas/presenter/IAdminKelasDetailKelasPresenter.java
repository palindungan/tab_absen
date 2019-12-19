package com.example.tababsensiapp.Activities.Admin.Kelas.Detail.Kelas.presenter;

public interface IAdminKelasDetailKelasPresenter {
    void hapusAkun(String id_detail_kelas_p);

    void onLoadSemuaData(String id);

    void onLoadSemuaDataKelas(String id);

    void onDeleteSharing(String id);

    void onMulaiPertemuan(String id_pengajar, String id_kelas_p, String lokasi_mulai_la, String lokasi_mulai_lo, String hari_kelas);
}
