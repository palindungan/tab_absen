package com.example.tababsensiapp.Activities.Admin.Transaksi.Gaji.Tampil.presenter;

public interface IAdminTransaksiGajiTampilPresenter {
    void inisiasiAwal(String id_pengajar,String id_penggajian);

    void onBayar(String id_pengajar, String id_admin, String total_pertemuan, String total_harga_fee);

    void onBayarDetail(String id_penggajian, String id_pertemuan);
}
