package com.example.tababsensiapp.Activities.Admin.Murid.Edit.Step2.presenter;

public interface IAdminMuridEditStep2Presenter {
    void onLoadSemuaData();

    void onUpdateData(String id_murid, String id_wali_murid, String nama, String foto);
}
