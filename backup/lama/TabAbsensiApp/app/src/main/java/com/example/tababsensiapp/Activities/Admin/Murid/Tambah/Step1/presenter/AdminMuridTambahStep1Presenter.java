package com.example.tababsensiapp.Activities.Admin.Murid.Tambah.Step1.presenter;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.Base64;

import com.example.tababsensiapp.Activities.Admin.Murid.Tambah.Step1.view.IAdminMuridTambahStep1View;

import java.io.ByteArrayOutputStream;

public class AdminMuridTambahStep1Presenter implements IAdminMuridTambahStep1Presenter {

    Context context;
    IAdminMuridTambahStep1View adminMuridTambahStep1View;

    public AdminMuridTambahStep1Presenter(Context context, IAdminMuridTambahStep1View adminMuridTambahStep1View) {
        this.context = context;
        this.adminMuridTambahStep1View = adminMuridTambahStep1View;
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
