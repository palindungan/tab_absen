package com.example.tababsensiapp.Activities.Admin.Home.View;

public interface IAdminHomeView {
//    void setCountData(String pengajar, String murid, String waliMurid, String mataPelajaran, String kelas, String kelasAktif);

    void showDialog();

    void onSuccessMessage(String message);

    void onErrorMessage(String message);
}
