package com.example.tababsensiapp.Activities.Admin.Transaksi.Gaji.Tampil.presenter;

public interface IAdminTransaksiGajiTampilPresenter {
    void inisiasiAwal(String id_pengajar);

    void onBayar(String id_pengajar, String id_admin, String total_pertemuan, String total_harga_fee);
}
