package com.its.bigstars.Activities.Absensi.Detail1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.its.bigstars.Activities.Absensi.Detail1.presenter.IAbsensiDetail1Presenter;
import com.its.bigstars.Activities.Absensi.Detail1.view.IAbsensiDetail1View;
import com.its.bigstars.R;

public class AbsensiDetail1Activity extends AppCompatActivity implements View.OnClickListener, IAbsensiDetail1View {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_absensi_detail1);
    }

    @Override
    public void onClick(View v) {

    }
}
