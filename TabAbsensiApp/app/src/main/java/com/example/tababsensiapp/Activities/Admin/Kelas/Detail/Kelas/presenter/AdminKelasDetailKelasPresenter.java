package com.example.tababsensiapp.Activities.Admin.Kelas.Detail.Kelas.presenter;

import android.content.Context;

import com.example.tababsensiapp.Activities.Admin.Kelas.Detail.Kelas.view.IAdminKelasDetailKelasView;
import com.example.tababsensiapp.Controllers.BaseUrl;
import com.example.tababsensiapp.Models.Murid;

import java.util.ArrayList;

public class AdminKelasDetailKelasPresenter implements IAdminKelasDetailKelasPresenter {

    Context context;
    IAdminKelasDetailKelasView adminKelasDetailKelasView;

    BaseUrl baseUrl;

    ArrayList<Murid> dataModelArrayList;

    public AdminKelasDetailKelasPresenter(Context context, IAdminKelasDetailKelasView adminKelasDetailKelasView) {
        this.context = context;
        this.adminKelasDetailKelasView = adminKelasDetailKelasView;

        baseUrl = new BaseUrl();
    }

    @Override
    public void inisiasiAwal(String id_kelas_p) {

    }

    @Override
    public void hapusAkun(String id) {

    }

    @Override
    public void onLoadSemuaData() {

    }
}
