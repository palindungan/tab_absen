package com.its.bigstars.Activities.Data.Murid.Edit.presenter;

import android.content.Context;

import com.its.bigstars.Activities.Data.Murid.Edit.view.IDataMuridEditView;

public class DataMuridEditPresenter implements IDataMuridEditPresenter {
    Context context;
    IDataMuridEditView dataMuridEditView;

    public DataMuridEditPresenter(Context context, IDataMuridEditView dataMuridEditView) {
        this.context = context;
        this.dataMuridEditView = dataMuridEditView;
    }

    @Override
    public void onLoadDataListWaliMurid() {

    }
}
