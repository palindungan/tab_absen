package com.its.bigstars.Activities.Data.WaliMurid.List.presenter;

import android.content.Context;

import com.its.bigstars.Activities.Data.WaliMurid.List.view.IDataWaliMuridListView;

public class DataWaliMuridListPresenter implements IDataWaliMuridListPresenter {

    Context context;
    IDataWaliMuridListView dataWaliMuridListView;

    public DataWaliMuridListPresenter(Context context, IDataWaliMuridListView dataWaliMuridListView) {
        this.context = context;
        this.dataWaliMuridListView = dataWaliMuridListView;
    }

    @Override
    public void onLoadDataList() {

    }

    @Override
    public void onDelete(String id) {

    }
}
