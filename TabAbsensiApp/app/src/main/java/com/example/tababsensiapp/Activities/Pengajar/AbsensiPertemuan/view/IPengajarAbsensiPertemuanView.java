package com.example.tababsensiapp.Activities.Pengajar.AbsensiPertemuan.view;

import android.location.Location;

public interface IPengajarAbsensiPertemuanView {
    void initActionBar();

    void onSuccessMessage(String message);

    void onErrorMessage(String message);

    void getLocation();

    void requestPermission();

    boolean checkPermission();

    void getDeviceLocation();

    void showSettingForLocation();

    void getLastLocation();

    void getFinalLocation();

    void updateUi(Location loc);
}
