package com.example.tababsensiapp.Activities.Admin.Pengajar.Tampil;

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

import com.example.tababsensiapp.Activities.Admin.Pengajar.Edit.AdminPengajarEditActivity;
import com.example.tababsensiapp.Activities.Admin.Pengajar.Tambah.AdminPengajarTambahActivity;
import com.example.tababsensiapp.Activities.Admin.Pengajar.Tampil.presenter.AdminPengajarTampilPresenter;
import com.example.tababsensiapp.Activities.Admin.Pengajar.Tampil.presenter.IAdminPengajarTampilPresenter;
import com.example.tababsensiapp.Activities.Admin.Pengajar.Tampil.view.IAdminPengajarTampilView;
import com.example.tababsensiapp.Activities.Admin.Transaksi.Gaji.Tampil.AdminTransaksiGajiTampilActivity;
import com.example.tababsensiapp.Activities.Admin.Transaksi.Riwayat.Gaji.Tampil.AdminTransaksiRiwayatGajiTampilActivity;
import com.example.tababsensiapp.Activities.Pengajar.Riwayat.Absen.PengajarRiwayatAbsenActivity;
import com.example.tababsensiapp.Adapters.AdapterDaftarPengajar;
import com.example.tababsensiapp.Models.Pengajar;
import com.example.tababsensiapp.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

import es.dmoral.toasty.Toasty;

public class AdminPengajarTampilActivity extends AppCompatActivity implements View.OnClickListener, IAdminPengajarTampilView {

    IAdminPengajarTampilPresenter adminPengajarTampilPresenter;

    private AdapterDaftarPengajar adapterDaftarPengajar;
    private RecyclerView recyclerView;

    Toolbar toolbar;

    private SwipeRefreshLayout swipeRefreshLayout;
    FloatingActionButton fab;

    public final static String EXTRA_STATUS_ACTIVITY = "EXTRA_STATUS_ACTIVITY";
    String status_activity = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_pengajar_tampil);

        status_activity = getIntent().getStringExtra(EXTRA_STATUS_ACTIVITY);

        adminPengajarTampilPresenter = new AdminPengajarTampilPresenter(this, this);
        adminPengajarTampilPresenter.onLoadSemuaData();

        recyclerView = findViewById(R.id.recycle_view);

        toolbar = findViewById(R.id.toolbar);
        initActionBar();

        fab = findViewById(R.id.fab);
        swipeRefreshLayout = findViewById(R.id.swipe_refresh_layout);

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                // Your code to make your refresh action
                adminPengajarTampilPresenter.onLoadSemuaData();

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

        if (!status_activity.equals("to_edit_pengajar")) {
            fab.hide();
        }

        fab.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.fab) {
            startActivity(new Intent(getApplicationContext(), AdminPengajarTambahActivity.class));
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
    public void onSetupListView(ArrayList<Pengajar> dataModelArrayList) {
        adapterDaftarPengajar = new AdapterDaftarPengajar(this, dataModelArrayList);
        GridLayoutManager layoutManager = new GridLayoutManager(this, 1, GridLayoutManager.VERTICAL, false);
        recyclerView.setAdapter(adapterDaftarPengajar);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setNestedScrollingEnabled(true);
        adapterDaftarPengajar.notifyDataSetChanged();

        adapterDaftarPengajar.setOnItemClickListener(new AdapterDaftarPengajar.ClickListener() {
            @Override
            public void onClick(View view, int position) {

                if (status_activity.equals("to_edit_pengajar")) {
                    Intent intent = new Intent(getApplicationContext(), AdminPengajarEditActivity.class);
                    intent.putExtra(AdminPengajarEditActivity.EXTRA_ID_PENGAJAR, dataModelArrayList.get(position).getId_pengajar());
                    startActivity(intent);
                } else if (status_activity.equals("to_transaksi_gaji")) {
                    Intent intent = new Intent(getApplicationContext(), AdminTransaksiGajiTampilActivity.class);
                    intent.putExtra(AdminTransaksiGajiTampilActivity.EXTRA_ID_PENGAJAR, dataModelArrayList.get(position).getId_pengajar());
                    intent.putExtra(AdminTransaksiGajiTampilActivity.EXTRA_ID_PENGGAJIAN, "kosong");
                    startActivity(intent);
                } else if (status_activity.equals("to_riwayat_gaji")) {
                    Intent intent = new Intent(getApplicationContext(), AdminTransaksiRiwayatGajiTampilActivity.class);
                    intent.putExtra(AdminTransaksiRiwayatGajiTampilActivity.EXTRA_ID_PENGAJAR, dataModelArrayList.get(position).getId_pengajar());
                    startActivity(intent);
                } else if (status_activity.equals("to_monitoring")) {
                    Intent intent = new Intent(getApplicationContext(), PengajarRiwayatAbsenActivity.class);
                    String id_pengajar = dataModelArrayList.get(position).getId_pengajar();
                    intent.putExtra(PengajarRiwayatAbsenActivity.EXTRA_ID_PENGAJAR, id_pengajar);
                    startActivity(intent);
                }

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
        adminPengajarTampilPresenter.onLoadSemuaData();
    }
}
