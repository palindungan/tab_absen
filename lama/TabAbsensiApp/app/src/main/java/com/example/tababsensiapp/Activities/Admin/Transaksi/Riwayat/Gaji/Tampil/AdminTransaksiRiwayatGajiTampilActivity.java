package com.example.tababsensiapp.Activities.Admin.Transaksi.Riwayat.Gaji.Tampil;

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

import com.example.tababsensiapp.Activities.Admin.Transaksi.Gaji.Tampil.AdminTransaksiGajiTampilActivity;
import com.example.tababsensiapp.Activities.Admin.Transaksi.Riwayat.Gaji.Tampil.presenter.AdminTransaksiRiwayatGajiTampilPresenter;
import com.example.tababsensiapp.Activities.Admin.Transaksi.Riwayat.Gaji.Tampil.presenter.IAdminTransaksiRiwayatGajiTampilPresenter;
import com.example.tababsensiapp.Activities.Admin.Transaksi.Riwayat.Gaji.Tampil.view.IAdminTransaksiRiwayatGajiTampilView;
import com.example.tababsensiapp.Adapters.AdapterDaftarPenggajian;
import com.example.tababsensiapp.Models.Penggajian;
import com.example.tababsensiapp.R;

import java.util.ArrayList;

import es.dmoral.toasty.Toasty;

public class AdminTransaksiRiwayatGajiTampilActivity extends AppCompatActivity implements View.OnClickListener, IAdminTransaksiRiwayatGajiTampilView {

    public static final String EXTRA_ID_PENGAJAR = "EXTRA_ID_PENGAJAR";
    String id_pengajar = "";

    IAdminTransaksiRiwayatGajiTampilPresenter adminTransaksiRiwayatGajiTampilPresenter;
    private AdapterDaftarPenggajian adapterDaftarPenggajian;
    private RecyclerView recyclerView;
    Toolbar toolbar;
    private SwipeRefreshLayout swipeRefreshLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_transaksi_riwayat_gaji_tampil);
        id_pengajar = getIntent().getStringExtra(EXTRA_ID_PENGAJAR);

        adminTransaksiRiwayatGajiTampilPresenter = new AdminTransaksiRiwayatGajiTampilPresenter(this, this);
        adminTransaksiRiwayatGajiTampilPresenter.onLoadSemuaData(id_pengajar);

        recyclerView = findViewById(R.id.recycle_view);

        toolbar = findViewById(R.id.toolbar);
        initActionBar();

        swipeRefreshLayout = findViewById(R.id.swipe_refresh_layout);

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                // Your code to make your refresh action
                adminTransaksiRiwayatGajiTampilPresenter.onLoadSemuaData(id_pengajar);

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
    public void onSetupListView(ArrayList<Penggajian> dataModelArrayList) {
        adapterDaftarPenggajian = new AdapterDaftarPenggajian(this, dataModelArrayList);
        GridLayoutManager layoutManager = new GridLayoutManager(this, 1, GridLayoutManager.VERTICAL, false);
        recyclerView.setAdapter(adapterDaftarPenggajian);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setNestedScrollingEnabled(true);
        adapterDaftarPenggajian.notifyDataSetChanged();

        adapterDaftarPenggajian.setOnItemClickListener(new AdapterDaftarPenggajian.ClickListener() {
            @Override
            public void onClick(View view, int position) {
                Intent intent = new Intent(getApplicationContext(), AdminTransaksiGajiTampilActivity.class);
                intent.putExtra(AdminTransaksiGajiTampilActivity.EXTRA_ID_PENGAJAR, dataModelArrayList.get(position).getId_pengajar());
                intent.putExtra(AdminTransaksiGajiTampilActivity.EXTRA_ID_PENGGAJIAN, dataModelArrayList.get(position).getId_penggajian());
                startActivity(intent);
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
        adminTransaksiRiwayatGajiTampilPresenter.onLoadSemuaData(id_pengajar);
    }
}
