package com.example.tababsenapp.Fitur.HalamanFormPengajar.Tambah.presenter;

import android.content.Context;

import com.example.tababsenapp.Fitur.HalamanFormPengajar.Tambah.view.IFormTambahPengajarView;

public class FormTambahPengajarPresenter implements IFormTambahPengajarPresenter {

    IFormTambahPengajarView formTambahPengajarView;
    Context context;

    public FormTambahPengajarPresenter(IFormTambahPengajarView formTambahPengajarView, Context context) {
        this.formTambahPengajarView = formTambahPengajarView;
        this.context = context;
    }

    @Override
    public void onSubmitPengajar() {

    }
}
