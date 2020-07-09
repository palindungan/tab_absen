package com.example.tababsensiapp.Activities.Admin.Kelas.Detail.Murid.Tampil;

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

import com.example.tababsensiapp.Activities.Admin.Kelas.Detail.Murid.Tampil.presenter.AdminKelasDetailMuridTampilPresenter;
import com.example.tababsensiapp.Activities.Admin.Kelas.Detail.Murid.Tampil.presenter.IAdminKelasDetailMuridTampilPresenter;
import com.example.tababsensiapp.Activities.Admin.Kelas.Detail.Murid.Tampil.view.IAdminKelasDetailMuridTampilView;
import com.example.tababsensiapp.Adapters.AdapterDaftarMurid;
import com.example.tababsensiapp.Models.Murid;
import com.example.tababsensiapp.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

import es.dmoral.toasty.Toasty;

public class AdminKelasDetailMuridTampilActivity extends AppCompatActivity implements View.OnClickListener, IAdminKelasDetailMuridTampilView {

    public static String EXTRA_ID_KELAS_P = "EXTRA_ID_KELAS_P";
    String id_kelas_p = "";

    IAdminKelasDetailMuridTampilPresenter adminKelasDetailMuridTampilPresenter;

    private AdapterDaftarMurid adapterDaftarMurid;
    private RecyclerView recyclerView;

    Toolbar toolbar;

    private SwipeRefreshLayout swipeRefreshLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_kelas_detail_murid_tampil);

        adminKelasDetailMuridTampilPresenter = new AdminKelasDetailMuridTampilPresenter(this, this);
        adminKelasDetailMuridTampilPresenter.onLoadSemuaData();

        recyclerView = findViewById(R.id.recycle_view);

        toolbar = findViewById(R.id.toolbar);
        initActionBar();

        swipeRefreshLayout = findViewById(R.id.swipe_refresh_layout);

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                // Your code to make your refresh action
                adminKelasDetailMuridTampilPresenter.onLoadSemuaData();

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
    public void onClick(View v) {

    }

    @Override
    public void initActionBar() {
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
    }

    @Override
    public void onSetupListView(ArrayList<Murid> dataModelArrayList) {
        adapterDaftarMurid = new AdapterDaftarMurid(this, dataModelArrayList);
        GridLayoutManager layoutManager = new GridLayoutManager(this, 1, GridLayoutManager.VERTICAL, false);
        recyclerView.setAdapter(adapterDaftarMurid);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setNestedScrollingEnabled(true);
        adapterDaftarMurid.setOnItemClickListener(new AdapterDaftarMurid.ClickListener() {
            @Override
            public void onClick(View view, int position) {
                id_kelas_p = getIntent().getStringExtra(EXTRA_ID_KELAS_P);
                adminKelasDetailMuridTampilPresenter.onSubmit(dataModelArrayList.get(position).getId_murid(), id_kelas_p);
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
    public void backPressed() {
        onBackPressed();
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
        adminKelasDetailMuridTampilPresenter.onLoadSemuaData();
    }
}
