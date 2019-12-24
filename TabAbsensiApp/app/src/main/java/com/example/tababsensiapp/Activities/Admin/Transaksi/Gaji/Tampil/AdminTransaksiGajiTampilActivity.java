package com.example.tababsensiapp.Activities.Admin.Transaksi.Gaji.Tampil;

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

import com.example.tababsensiapp.Activities.Admin.Transaksi.Gaji.Tampil.presenter.AdminTransaksiGajiTampilPresenter;
import com.example.tababsensiapp.Activities.Admin.Transaksi.Gaji.Tampil.presenter.IAdminTransaksiGajiTampilPresenter;
import com.example.tababsensiapp.Activities.Admin.Transaksi.Gaji.Tampil.view.IAdminTransaksiGajiTampilView;
import com.example.tababsensiapp.Activities.Pengajar.Absensi.Pertemuan.PengajarAbsensiPertemuanActivity;
import com.example.tababsensiapp.Adapters.AdapterPengajarDaftarKelasAktif;
import com.example.tababsensiapp.Controllers.SessionManager;
import com.example.tababsensiapp.Models.Pertemuan;
import com.example.tababsensiapp.R;

import java.util.ArrayList;

import es.dmoral.toasty.Toasty;

public class AdminTransaksiGajiTampilActivity extends AppCompatActivity implements View.OnClickListener, IAdminTransaksiGajiTampilView {

    SessionManager sessionManager;
    String id_pengajar;

    IAdminTransaksiGajiTampilPresenter adminTransaksiGajiTampilPresenter;

    private AdapterPengajarDaftarKelasAktif adapterPengajarDaftarKelasAktif;
    private RecyclerView recyclerView;

    Toolbar toolbar;

    private SwipeRefreshLayout swipeRefreshLayout;

    public static final String EXTRA_ID_PENGAJAR = "EXTRA_ID_PENGAJAR";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_transaksi_gaji);

        id_pengajar = getIntent().getStringExtra(EXTRA_ID_PENGAJAR);

        adminTransaksiGajiTampilPresenter = new AdminTransaksiGajiTampilPresenter(this,this);
        adminTransaksiGajiTampilPresenter.inisiasiAwal(id_pengajar);

        recyclerView = findViewById(R.id.recycle_view);

        toolbar = findViewById(R.id.toolbar);
        initActionBar();

        swipeRefreshLayout = findViewById(R.id.swipe_refresh_layout);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                // Your code to make your refresh action
                adminTransaksiGajiTampilPresenter.inisiasiAwal(id_pengajar);

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
    public void onSetupListView(ArrayList<Pertemuan> dataModelArrayList) {
        adapterPengajarDaftarKelasAktif = new AdapterPengajarDaftarKelasAktif(this, dataModelArrayList);
        GridLayoutManager layoutManager = new GridLayoutManager(this, 1, GridLayoutManager.VERTICAL, false);
        recyclerView.setAdapter(adapterPengajarDaftarKelasAktif);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setNestedScrollingEnabled(true);
        adapterPengajarDaftarKelasAktif.notifyDataSetChanged();

        adapterPengajarDaftarKelasAktif.setOnItemClickListener(new AdapterPengajarDaftarKelasAktif.ClickListener() {
            @Override
            public void onClick(View view, int position) {
                Intent intent = new Intent(getApplicationContext(), PengajarAbsensiPertemuanActivity.class);
                String id_pertemuan = dataModelArrayList.get(position).getId_pertemuan();
                intent.putExtra(PengajarAbsensiPertemuanActivity.EXTRA_ID_PERTEMUAN, id_pertemuan);
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
        adminTransaksiGajiTampilPresenter.inisiasiAwal(id_pengajar);
    }
}
