package com.its.bigstars.Activities.Data.Murid.Edit.presenter;

public interface IDataMuridEditPresenter {
    void onLoadDataListWaliMurid();

    void onUpdate(String id_murid, String id_wali_murid, String nama, String foto);
}
