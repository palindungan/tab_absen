package com.example.tababsensiapp.Activities.Admin.Murid.Edit.Step1.presenter;

import android.graphics.Bitmap;

public interface IAdminMuridEditStep1Presenter {
    void inisiasiAwal(String id_murid);

//    void onUpdateData(String id_murid, String id_wali_murid, String nama, String foto);

    String getStringImage(Bitmap bitmap);

    void hapusAkun(String id);
}
