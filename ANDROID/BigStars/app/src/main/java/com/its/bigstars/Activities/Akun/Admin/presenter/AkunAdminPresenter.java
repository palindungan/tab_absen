package com.its.bigstars.Activities.Akun.Admin.presenter;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.Base64;

import com.its.bigstars.Activities.Akun.Admin.view.IAkunAdminView;

import java.io.ByteArrayOutputStream;

public class AkunAdminPresenter implements IAkunAdminPresenter {
    Context context;
    IAkunAdminView akunAdminView;

    public AkunAdminPresenter(Context context, IAkunAdminView akunAdminView) {
        this.context = context;
        this.akunAdminView = akunAdminView;
    }

    @Override
    public void inisiasiAwal(String id_admin) {

    }

    @Override
    public void onUpdate(String id_admin, String nama, String username, String password, String foto) {

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
