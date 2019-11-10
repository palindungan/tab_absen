package com.example.tababsenapp.Fitur.HalamanFormWaliMurid.Edit.presenter;

public interface IFormEditWaliMuridPresenter {
    void inisiasiAwal(String id_wali_murid);

    void onUpdateWaliMurid(String id_wali_murid, String nama, String username, String password, String konfirmasi_password, String alamat, String no_hp);

    void hapusAkun(String id);
}
