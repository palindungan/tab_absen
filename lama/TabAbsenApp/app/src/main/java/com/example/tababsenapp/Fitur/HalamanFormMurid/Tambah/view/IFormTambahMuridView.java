package com.example.tababsenapp.Fitur.HalamanFormMurid.Tambah.view;

public interface IFormTambahMuridView {
    void initActionBar();

    void onSubmitSuccess(String message);

    void onSubmitError(String message);

    void clickNext(String nama, String foto);
}
