package com.its.bigstars.Activities.Data.Pengajar.Edit.presenter;

import android.content.Context;

import com.its.bigstars.Activities.Data.Pengajar.Edit.view.IDataPengajarEditView;

public class DataPengajarEditPresenter implements IDataPengajarEditPresenter {
    Context context;
    IDataPengajarEditView dataPengajarEditView;

    public DataPengajarEditPresenter(Context context, IDataPengajarEditView dataPengajarEditView) {
        this.context = context;
        this.dataPengajarEditView = dataPengajarEditView;
    }

    @Override
    public void onUpdate(String id_pengajar, String nama, String username, String password, String alamat, String no_hp, String foto) {

    }
}
