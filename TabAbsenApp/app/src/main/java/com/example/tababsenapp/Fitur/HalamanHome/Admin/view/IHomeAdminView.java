package com.example.tababsenapp.Fitur.HalamanHome.Admin.view;

public interface IHomeAdminView {
    void setCountData(String pengajar, String murid, String waliMurid, String mataPelajaran, String kelas, String kelasAktif);

    void onSucceessMessage(String message);

    void onErrorMessage(String message);

    void showDialog();
}
