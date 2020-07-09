package com.example.tababsensiapp.Activities.Admin.Murid.Tambah.Step2.presenter;

public interface IAdminMuridTambahStep2Presenter {
    void onLoadSemuaData();

    void onSendData(String id_wali_murid, String nama, String foto);
}
