package com.its.bigstars.Activities.Data.Kelas.Edit.presenter;

public interface IDataKelasEditPresenter {
    void onLoadDataListWaliMurid();

    void onUpdate(String id_pengajar,
                  String id_mata_pelajaran,
                  String hari,
                  String jam_mulai,
                  String jam_berakhir,
                  String harga_fee,
                  String harga_spp);
}
