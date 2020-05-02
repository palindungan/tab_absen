package com.its.bigstars.Controllers;

import android.graphics.Bitmap;
import android.util.Base64;

import java.io.ByteArrayOutputStream;

public class GlobalProcess {

    Bitmap bitmap;
    String stringImage;

    // start of image process
    public void setBitmap(Bitmap bitmap) {
        this.bitmap = bitmap;
    }

    public String getStringImage() {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 80, byteArrayOutputStream);

        byte[] imageByteArray = byteArrayOutputStream.toByteArray();
        String encodedImage = Base64.encodeToString(imageByteArray, Base64.DEFAULT);

        return encodedImage;
    }
    // end of image process
}
