package com.example.tababsenapp.Fitur.HalamanFormMurid.Tambah.presenter;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.Base64;

import com.example.tababsenapp.Controllers.SessionManager;
import com.example.tababsenapp.Fitur.HalamanFormMurid.Tambah.view.IFormTambahMuridView;

import java.io.ByteArrayOutputStream;

public class FormTambahMuridPresenter implements IFormTambahMuridPresenter {
    IFormTambahMuridView formTambahMuridView;
    Context context;

    SessionManager sessionManager;
    String base_url;


    public FormTambahMuridPresenter(IFormTambahMuridView formTambahMuridView, Context context) {
        this.formTambahMuridView = formTambahMuridView;
        this.context = context;

        sessionManager = new SessionManager(context);
        base_url = sessionManager.getBaseUrl();
    }

    @Override
    public void onClickNext(String nama, String foto) {
        if (nama.isEmpty()) {
            formTambahMuridView.onSubmitError("Nama Tidak Boleh Kosong !");
        } else {

            formTambahMuridView.clickNext(nama,foto);

        }
    }

    @Override
    public String getStringImage(Bitmap bitmap) {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 80, byteArrayOutputStream);

        byte[] imageByteArray = byteArrayOutputStream.toByteArray();
        String encodedImage = Base64.encodeToString(imageByteArray, Base64.DEFAULT);

        return encodedImage;
    }
}
