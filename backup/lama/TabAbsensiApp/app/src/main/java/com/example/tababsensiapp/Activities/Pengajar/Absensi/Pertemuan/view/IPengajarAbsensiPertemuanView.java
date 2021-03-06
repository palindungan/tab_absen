package com.example.tababsensiapp.Activities.Pengajar.Absensi.Pertemuan.view;

import java.util.HashMap;

public interface IPengajarAbsensiPertemuanView {
    void initActionBar();

    void onSuccessMessage(String message);

    void onErrorMessage(String message);

    // String nama_pengajar, String nama_mata_pelajaran, String hari, String jam_mulai, String jam_berakhir, String harga, String hari_mulai_x, String jam_mulai_x, String lan, String lon
    void setNilaiDefault(HashMap<String, String> data);

    void showDialogDelete();

    void showDialogValidasi();

    void backPressed();
}
