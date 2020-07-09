package com.example.tababsensiapp.Activities.Pengajar.Absensi.NextStep.view;

public interface IPengajarAbsensiNextStepView {
    void initActionBar();

    void onSuccessMessage(String message);

    void onErrorMessage(String message);

    void setNilaiDefault();

    void backPressed();

    void showDialogBerakhir();

    void getLocation();

    void requestPermission();

    boolean checkPermission();

    void getDeviceLocation();

    void showSettingForLocation();

    void getLastLocation();

    void getFinalLocation();

    void keHalamanLain(String id_pertemuan);
}
