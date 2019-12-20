package com.example.tababsensiapp.Activities.Pengajar.Absensi.NextStep.presenter;

import android.content.Context;

import com.example.tababsensiapp.Activities.Pengajar.Absensi.NextStep.view.IPengajarAbsensiNextStepView;
import com.example.tababsensiapp.Controllers.BaseUrl;

public class PengajarAbsensiNextStepPresenter implements IPengajarAbsensiNextStepPresenter {

    Context context;
    IPengajarAbsensiNextStepView pengajarAbsensiNextStepView;

    BaseUrl baseUrl;

    public PengajarAbsensiNextStepPresenter(Context context, IPengajarAbsensiNextStepView pengajarAbsensiNextStepView) {
        this.context = context;
        this.pengajarAbsensiNextStepView = pengajarAbsensiNextStepView;

        baseUrl = new BaseUrl();
    }


    @Override
    public void onAkhiriPertemuan(String id_pertemuan, String deskripsi, String lokasi_berakhir_la, String lokasi_berakhir_lo) {

    }
}
