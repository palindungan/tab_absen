package com.its.bigstars.Activities.Data.Kelas.Add.presenter;

public interface IDataKelasAddPresenter {
    void onSubmit(String id_pengajar,
                  String id_mata_pelajaran,
                  String hari,
                  String jam_mulai,
                  String jam_berakhir,
                  String harga_fee,
                  String harga_spp);

    void onLoadDataList();
}
