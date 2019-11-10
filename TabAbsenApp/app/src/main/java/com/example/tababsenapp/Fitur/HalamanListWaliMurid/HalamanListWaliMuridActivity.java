package com.example.tababsenapp.Fitur.HalamanListWaliMurid;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Toolbar;

import com.example.tababsenapp.Fitur.HalamanListWaliMurid.presenter.IListWaliMuridPresenter;
import com.example.tababsenapp.Fitur.HalamanListWaliMurid.view.IListWaliMuridView;
import com.example.tababsenapp.Model.waliMurid.WaliMurid;
import com.example.tababsenapp.R;

import java.util.ArrayList;

public class HalamanListWaliMuridActivity extends AppCompatActivity implements IListWaliMuridView {

    IListWaliMuridPresenter listWaliMuridPresenter;

    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_halaman_list_wali_murid);
    }

    @Override
    public void initActionBar() {

    }

    @Override
    public void onSetupLisView(ArrayList<WaliMurid> dataModelArrayList) {

    }

    @Override
    public void onSuccessMessage() {

    }

    @Override
    public void onErrorMessage() {

    }
}
