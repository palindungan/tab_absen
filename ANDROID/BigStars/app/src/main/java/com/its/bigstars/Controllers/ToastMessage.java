package com.its.bigstars.Controllers;

import android.content.Context;
import android.widget.Toast;

import es.dmoral.toasty.Toasty;

public class ToastMessage {
    public Context context;

    public ToastMessage(Context context) {
        this.context = context;
    }

    public void onSuccessMessage(String message){
        Toasty.success(context, message, Toast.LENGTH_SHORT).show();
    }

    public void onErrorMessage(String message){
        Toasty.error(context, message, Toast.LENGTH_SHORT).show();
    }
}
