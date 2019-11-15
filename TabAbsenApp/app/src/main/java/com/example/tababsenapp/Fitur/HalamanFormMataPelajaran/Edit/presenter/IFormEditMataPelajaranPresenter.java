package com.example.tababsenapp.Fitur.HalamanFormMataPelajaran.Edit.presenter;

public interface IFormEditMataPelajaranPresenter {
    void inisiasiAwal(String id_mata_pelajaran);

    void onUpdateWaliMurid(String id_mata_pelajaran, String nama);

    void hapusAkun(String id);
}
