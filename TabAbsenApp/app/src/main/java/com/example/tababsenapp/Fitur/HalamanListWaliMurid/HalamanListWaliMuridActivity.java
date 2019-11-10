package com.example.tababsenapp.Fitur.HalamanListWaliMurid;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.tababsenapp.Adapters.AdapterDaftarWaliMurid;
import com.example.tababsenapp.Fitur.HalamanFormWaliMurid.Tambah.HalamanFormTambahWaliMuridActivity;
import com.example.tababsenapp.Fitur.HalamanListWaliMurid.presenter.IListWaliMuridPresenter;
import com.example.tababsenapp.Fitur.HalamanListWaliMurid.presenter.ListWaliMuridPresenter;
import com.example.tababsenapp.Fitur.HalamanListWaliMurid.view.IListWaliMuridView;
import com.example.tababsenapp.Model.waliMurid.WaliMurid;
import com.example.tababsenapp.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

import es.dmoral.toasty.Toasty;

public class HalamanListWaliMuridActivity extends AppCompatActivity implements IListWaliMuridView {

    IListWaliMuridPresenter listWaliMuridPresenter;

    private AdapterDaftarWaliMurid adapterDaftarWaliMurid;
    private RecyclerView recyclerView;

    Toolbar toolbar;

    String EXTRA_ID_WALI_MURID = "EXTRA_ID_WALI_MURID";

    private SwipeRefreshLayout swipeRefreshLayout;
    FloatingActionButton fab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_halaman_list_wali_murid);

        listWaliMuridPresenter = new ListWaliMuridPresenter(this, this);
        listWaliMuridPresenter.onLoadSemuaListWaliMurid();

        recyclerView = findViewById(R.id.recycle_list_wali_murid);

        toolbar = findViewById(R.id.toolbar);
        initActionBar();

        fab = findViewById(R.id.fab);
        swipeRefreshLayout = findViewById(R.id.swipe_refresh_layout);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HalamanListWaliMuridActivity.this, HalamanFormTambahWaliMuridActivity.class));
            }
        });

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                // Your code to make your refresh action
                listWaliMuridPresenter.onLoadSemuaListWaliMurid();

                // CallYourRefreshingMethod();
                final Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        if (swipeRefreshLayout.isRefreshing()) {
                            swipeRefreshLayout.setRefreshing(false);
                        }
                    }
                }, 1000);
            }
        });
    }

    @Override
    public void initActionBar() {
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
    }

    @Override
    public void onSetupListView(ArrayList<WaliMurid> dataModelArrayList) {

        adapterDaftarWaliMurid = new AdapterDaftarWaliMurid(this, dataModelArrayList);
        GridLayoutManager layoutManager = new GridLayoutManager(this, 1, GridLayoutManager.VERTICAL, false);
        recyclerView.setAdapter(adapterDaftarWaliMurid);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setNestedScrollingEnabled(true);
        adapterDaftarWaliMurid.notifyDataSetChanged();

        adapterDaftarWaliMurid.setOnItemClickListener(new AdapterDaftarWaliMurid.ClickListener() {
            @Override
            public void onClick(View view, int position) {
//                Intent intent = new Intent(HalamanListPengajarActivity.this, HalamanFormEditPengajarActivity.class);
//                intent.putExtra(EXTRA_ID_PENGAJAR, dataModelArrayList.get(position).getId_pengajar());
//                startActivity(intent);
            }
        });
    }

    @Override
    public void onSuccessMessage(String message) {
        Toasty.success(this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onErrorMessage(String message) {
        Toasty.error(this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {

            case android.R.id.home:
                onBackPressed();
                break;
        }
        return true;
    }

    @Override
    protected void onResume() {
        super.onResume();
        listWaliMuridPresenter.onLoadSemuaListWaliMurid();
    }
}
