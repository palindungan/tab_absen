package com.example.tababsenapp.Fitur.HalamanListWaliMurid;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.os.Bundle;
import android.widget.Toolbar;

import com.example.tababsenapp.Adapters.AdapterDaftarWaliMurid;
import com.example.tababsenapp.Fitur.HalamanListWaliMurid.presenter.IListWaliMuridPresenter;
import com.example.tababsenapp.Fitur.HalamanListWaliMurid.presenter.ListWaliMuridPresenter;
import com.example.tababsenapp.Fitur.HalamanListWaliMurid.view.IListWaliMuridView;
import com.example.tababsenapp.Model.waliMurid.WaliMurid;
import com.example.tababsenapp.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class HalamanListWaliMuridActivity extends AppCompatActivity implements IListWaliMuridView {

    IListWaliMuridPresenter listWaliMuridPresenter;

    private AdapterDaftarWaliMurid adapterDaftarWaliMurid;
    private RecyclerView recyclerView;

    Toolbar toolbar;

    String EXTRA_ID_WALI_MURID = "EXTRA_ID_WALI_MURID";
    private SwipeRefreshLayout swipeRefreshLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_halaman_list_wali_murid);

        listWaliMuridPresenter = new ListWaliMuridPresenter(this,this);
        listWaliMuridPresenter.onLoadSemuaListWaliMurid();

        recyclerView = findViewById(R.id.recycle_list_wali_murid);

        toolbar = findViewById(R.id.toolbar);
        initActionBar();

        FloatingActionButton fab = findViewById(R.id.fab);
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
