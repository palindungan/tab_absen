package com.example.tababsensiapp.Activities.Pengajar.Absensi.Pertemuan.presenter;

public interface IPengajarAbsensiPertemuanPresenter {
    void inisiasiAwal(String id_pertemuan);

    void hapusData(String id);

    void onValidasi(String id);

    void onInValidasi(String id);
}
