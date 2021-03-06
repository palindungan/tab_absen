package com.example.tababsensiapp.Activities.Admin.Kelas.Detail.Kelas.view;

import com.example.tababsensiapp.Models.Murid;

import java.util.ArrayList;

public interface IAdminKelasDetailKelasView {
    void initActionBar();

    void setNilaiDefault(String nama_pelajaran,String nama_pengajar,String harga_fee,String harga_spp,String hari,String jam_mulai,String jam_berakhir,String id_sharing ,String nama_sharing);

    void onSuccessMessage(String message);

    void onErrorMessage(String message);

    void backPressed();

    void onSetupListView(ArrayList<Murid> dataModelArrayList);

    void sembunyikanObject();

    void showDialogMulai();

    void getLocation();

    void requestPermission();

    boolean checkPermission();

    void getDeviceLocation();

    void showSettingForLocation();

    void getLastLocation();

    void getFinalLocation();

    void keHalamanAbsensi(String id_pertemuan);
}
