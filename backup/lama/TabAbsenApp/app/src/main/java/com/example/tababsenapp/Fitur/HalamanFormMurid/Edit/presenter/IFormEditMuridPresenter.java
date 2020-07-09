package com.example.tababsenapp.Fitur.HalamanFormMurid.Edit.presenter;

import android.graphics.Bitmap;

public interface IFormEditMuridPresenter {
    void inisiasiAwal(String id_murid);

    void onUpdateData(String id_murid, String id_wali_murid, String nama, String foto);

    String getStringImage(Bitmap bitmap);

    void hapusAkun(String id);
}
