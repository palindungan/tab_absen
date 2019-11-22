package com.example.tababsensiapp.Activities.Admin.MataPelajaran.Tampil;

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

import com.example.tababsensiapp.Activities.Admin.MataPelajaran.Tampil.presenter.AdminMataPelajaranTampilPresenter;
import com.example.tababsensiapp.Activities.Admin.MataPelajaran.Tampil.presenter.IAdminMataPelajaranTampilPresenter;
import com.example.tababsensiapp.Activities.Admin.MataPelajaran.Tampil.view.IAdminMataPelajaranTampilView;
import com.example.tababsensiapp.Activities.Admin.Pengajar.Tambah.AdminPengajarTambahActivity;
import com.example.tababsensiapp.Adapters.AdapterDaftarMataPelajaran;
import com.example.tababsensiapp.Models.MataPelajaran;
import com.example.tababsensiapp.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

import es.dmoral.toasty.Toasty;

public class AdminMataPelajaranTampilActivity extends AppCompatActivity implements View.OnClickListener , IAdminMataPelajaranTampilView {

    IAdminMataPelajaranTampilPresenter adminMataPelajaranTampilPresenter;

    private AdapterDaftarMataPelajaran adapterDaftarMataPelajaran;
    private RecyclerView recyclerView;

    Toolbar toolbar;

    private SwipeRefreshLayout swipeRefreshLayout;
    FloatingActionButton fab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_mata_pelajaran_tampil);

        adminMataPelajaranTampilPresenter = new AdminMataPelajaranTampilPresenter(this, this);
        adminMataPelajaranTampilPresenter.onLoadSemuaData();

        recyclerView = findViewById(R.id.recycle_view);

        toolbar = findViewById(R.id.toolbar);
        initActionBar();

        swipeRefreshLayout = findViewById(R.id.swipe_refresh_layout);
        fab = findViewById(R.id.fab);

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                // Your code to make your refresh action
                adminMataPelajaranTampilPresenter.onLoadSemuaData();

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

        fab.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.fab) {
            // startActivity(new Intent(getApplicationContext(), AdminPengajarTambahActivity.class));
        }
    }

    @Override
    public void initActionBar() {
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
    }

    @Override
    public void onSetupListView(ArrayList<MataPelajaran> dataModelArrayList) {
        adapterDaftarMataPelajaran = new AdapterDaftarMataPelajaran(this,dataModelArrayList);
        GridLayoutManager layoutManager = new GridLayoutManager(this,1,GridLayoutManager.VERTICAL,false);
        recyclerView.setAdapter(adapterDaftarMataPelajaran);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setNestedScrollingEnabled(true);
        adapterDaftarMataPelajaran.notifyDataSetChanged();

        adapterDaftarMataPelajaran.setOnItemClickListener(new AdapterDaftarMataPelajaran.ClickListener() {
            @Override
            public void onClick(View view, int position) {
//                Intent intent = new Intent(HalamanListMataPelajaranActivity.this, HalamanFormEditMataPelajaranActivity.class);
//                intent.putExtra(EXTRA_ID_MATA_PELAJARAN,dataModelArrayList.get(position).getId_mata_pelajaran());
//                startActivity(intent);
            }
        });
    }

    @Override
    public void onSucceessMessage(String message) {
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
        adminMataPelajaranTampilPresenter.onLoadSemuaData();
    }
}
