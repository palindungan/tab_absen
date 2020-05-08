package com.its.bigstars.Activities.Absensi.Detail2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.its.bigstars.Activities.Absensi.Detail2.view.IAbsensiDetail2PresenterView;
import com.its.bigstars.R;

public class AbsensiDetail2Activity extends AppCompatActivity implements View.OnClickListener, IAbsensiDetail2PresenterView {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_absensi_detail2);
    }

    @Override
    public void onClick(View v) {

    }
}
