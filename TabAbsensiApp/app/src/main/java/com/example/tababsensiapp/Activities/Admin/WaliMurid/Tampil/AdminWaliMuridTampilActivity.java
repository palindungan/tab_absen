package com.example.tababsensiapp.Activities.Admin.WaliMurid.Tampil;

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

import com.example.tababsensiapp.Activities.Admin.Transaksi.SPP.Tampil.AdminTransaksiSppTampilActivity;
import com.example.tababsensiapp.Activities.Admin.WaliMurid.Edit.AdminWaliMuridEditActivity;
import com.example.tababsensiapp.Activities.Admin.WaliMurid.Tambah.AdminWaliMuridTambahActivity;
import com.example.tababsensiapp.Activities.Admin.WaliMurid.Tampil.presenter.AdminWaliMuridTampilPresenter;
import com.example.tababsensiapp.Activities.Admin.WaliMurid.Tampil.presenter.IAdminWaliMuridTampilPresenter;
import com.example.tababsensiapp.Activities.Admin.WaliMurid.Tampil.view.IAdminWaliMuridTampilView;
import com.example.tababsensiapp.Adapters.AdapterDaftarWaliMurid;
import com.example.tababsensiapp.Models.WaliMurid;
import com.example.tababsensiapp.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

import es.dmoral.toasty.Toasty;

public class AdminWaliMuridTampilActivity extends AppCompatActivity implements View.OnClickListener , IAdminWaliMuridTampilView {

    public final static String EXTRA_STATUS_ACTIVITY = "EXTRA_STATUS_ACTIVITY";
    String status_activity = "";

    IAdminWaliMuridTampilPresenter adminWaliMuridTampilPresenter;

    private AdapterDaftarWaliMurid adapterDaftarWaliMurid;
    private RecyclerView recyclerView;

    Toolbar toolbar;

    private SwipeRefreshLayout swipeRefreshLayout;
    FloatingActionButton fab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_wali_murid_tampil);

        status_activity = getIntent().getStringExtra(EXTRA_STATUS_ACTIVITY);

        adminWaliMuridTampilPresenter = new AdminWaliMuridTampilPresenter(this, this);
        adminWaliMuridTampilPresenter.onLoadSemuaData();

        recyclerView = findViewById(R.id.recycle_view);

        toolbar = findViewById(R.id.toolbar);
        initActionBar();

        fab = findViewById(R.id.fab);
        swipeRefreshLayout = findViewById(R.id.swipe_refresh_layout);

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                // Your code to make your refresh action
                adminWaliMuridTampilPresenter.onLoadSemuaData();

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
            startActivity(new Intent(getApplicationContext(), AdminWaliMuridTambahActivity.class));
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

                if (status_activity.equals("to_edit_wali_murid")) {
                    Intent intent = new Intent(getApplicationContext(), AdminWaliMuridEditActivity.class);
                    intent.putExtra(AdminWaliMuridEditActivity.EXTRA_ID_WALI_MURID, dataModelArrayList.get(position).getId_wali_murid());
                    startActivity(intent);
                } else if (status_activity.equals("to_transaksi_spp")) {
                    Intent intent = new Intent(getApplicationContext(), AdminTransaksiSppTampilActivity.class);
                    intent.putExtra(AdminTransaksiSppTampilActivity.EXTRA_ID_WALI_MURID, dataModelArrayList.get(position).getId_wali_murid());
                    intent.putExtra(AdminTransaksiSppTampilActivity.EXTRA_ID_BAYAR_SPP, "kosong");
                    startActivity(intent);
                } else if (status_activity.equals("to_riwayat_spp")) {
//                    Intent intent = new Intent(getApplicationContext(), AdminTransaksiRiwayatGajiTampilActivity.class);
//                    intent.putExtra(AdminTransaksiRiwayatGajiTampilActivity.EXTRA_ID_PENGAJAR, dataModelArrayList.get(position).getId_pengajar());
//                    startActivity(intent);
                }
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
        adminWaliMuridTampilPresenter.onLoadSemuaData();
    }
}
