package com.example.tababsensiapp.Activities.Admin.Kelas.Detail.Kelas.view;

import com.example.tababsensiapp.Models.Murid;

import java.util.ArrayList;

public interface IAdminKelasDetailKelasView {
    void initActionBar();

    void setNilaiDefault(String nama_pelajaran, String hari, String jam_mulai, String jam_berakhir, String harga_fee);

    void onSuccessMessage(String message);

    void onErrorMessage(String message);

    void showDialogDelete();

    void backPressed();

    void onSetupListView(ArrayList<Murid> dataModelArrayList);
}
