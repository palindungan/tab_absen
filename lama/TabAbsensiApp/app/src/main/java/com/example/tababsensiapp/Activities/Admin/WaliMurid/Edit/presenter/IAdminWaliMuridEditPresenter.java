package com.example.tababsensiapp.Activities.Admin.WaliMurid.Edit.presenter;

public interface IAdminWaliMuridEditPresenter {
    void inisiasiAwal(String id_wali_murid);

    void onUpdate(String id_wali_murid, String nama, String username, String password, String alamat, String no_hp);

    void hapusAkun(String id);
}
