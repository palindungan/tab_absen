package com.its.bigstars.Activities.Data.Kelas.Edit.presenter;

public interface IDataKelasEditPresenter {
    void onUpdate(
            String id_kelas_p,
            String id_pengajar,
            String id_mata_pelajaran,
            String hari,
            String jam_mulai,
            String jam_berakhir,
            String harga_fee,
            String harga_spp);

    void onLoadDataListPelajaran();

    void onLoadDataListMurid(String id_kelas_p);

    void onLoadDataListSemuaMurid();

    void onAddMurid(String id_kelas_p, String id_murid);

    void onDeleteMurid(String id_detail_kelas_p);
}
