package com.example.tababsensiapp.Activities.Pengajar.AbsensiPertemuan.view;

import android.location.Location;

import java.util.HashMap;

public interface IPengajarAbsensiPertemuanView {
    void initActionBar();

    void onSuccessMessage(String message);

    void onErrorMessage(String message);

    // String nama_pengajar, String nama_mata_pelajaran, String hari, String jam_mulai, String jam_berakhir, String harga, String hari_mulai_x, String jam_mulai_x, String lan, String lon
    void setNilaiDefault(HashMap<String, String> data);

    void getLocation();

    void requestPermission();

    boolean checkPermission();

    void getDeviceLocation();

    void showSettingForLocation();

    void getLastLocation();

    void getFinalLocation();

    void updateUi(Location loc);
}
