package com.its.bigstars.Activities.Data.Murid.List.presenter;

import android.content.Context;

import com.its.bigstars.Activities.Data.Murid.List.view.IDataMuridListView;

public class DataMuridListPresenter implements IDataMuridListPresenter {

    Context context;
    IDataMuridListView dataMuridListView;

    public DataMuridListPresenter(Context context, IDataMuridListView dataMuridListView) {
        this.context = context;
        this.dataMuridListView = dataMuridListView;
    }
}
