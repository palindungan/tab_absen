package com.example.tababsenapp.Fitur.HalamanFormMurid.List.presenter;

public interface IFormListWaliMuridPresenter {
    void onLoadSemuaListWaliMurid();

    void onSendData(String id_wali_murid, String nama, String foto);
}
