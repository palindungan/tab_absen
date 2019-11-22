package com.example.tababsensiapp.Activities.Admin.MataPelajaran.Edit.presenter;

public interface IAdminMataPelajaranEditPresenter {
    void inisiasiAwal(String id_mata_pelajaran);

    void onUpdate(String id_mata_pelajaran, String nama);

    void hapusAkun(String id);
}
