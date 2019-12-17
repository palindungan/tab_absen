package com.example.tababsensiapp.Activities.Pengajar.Kelas.Detail.view;

import com.example.tababsensiapp.Models.Murid;

import java.util.ArrayList;

public interface IPengajarKelasDetailView {
    void initActionBar();

    void setNilaiDefault(String nama_pelajaran,String nama_pengajar,String harga_fee,String hari,String jam_mulai,String jam_berakhir,String id_sharing ,String nama_sharing);

    void onSuccessMessage(String message);

    void onErrorMessage(String message);

    void onSetupListView(ArrayList<Murid> dataModelArrayList);
}
