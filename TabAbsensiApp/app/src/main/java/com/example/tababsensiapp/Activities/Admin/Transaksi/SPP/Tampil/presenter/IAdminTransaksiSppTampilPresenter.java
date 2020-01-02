package com.example.tababsensiapp.Activities.Admin.Transaksi.SPP.Tampil.presenter;

public interface IAdminTransaksiSppTampilPresenter {
    void inisiasiAwal(String id_murid, String id_bayar_spp);

    void onBayar(String id_murid, String id_admin, String total_pertemuan, String total_spp);

    void onBayarDetail(String id_bayar_spp, String id_pertemuan);
}
