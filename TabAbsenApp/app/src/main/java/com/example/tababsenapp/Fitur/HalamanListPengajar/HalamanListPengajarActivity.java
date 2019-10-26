package com.example.tababsenapp.Fitur.HalamanListPengajar;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.tababsenapp.Fitur.HalamanListPengajar.presenter.IListPengajarPresenter;
import com.example.tababsenapp.Fitur.HalamanListPengajar.presenter.ListPengajarPresenter;
import com.example.tababsenapp.Fitur.HalamanListPengajar.view.IListPengajarView;
import com.example.tababsenapp.R;

public class HalamanListPengajarActivity extends AppCompatActivity implements IListPengajarView {

    IListPengajarPresenter listPengajarPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_halaman_list_pengajar);

        listPengajarPresenter = new ListPengajarPresenter(this,this);
        listPengajarPresenter.onLoadSemuaListPengajar();
    }
}
