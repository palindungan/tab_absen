package com.example.tababsensiapp.Activities.Admin.Transaksi.Riwayat.SPP.Tampil;

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
import com.example.tababsensiapp.Activities.Admin.Transaksi.Riwayat.SPP.Tampil.presenter.AdminTransaksiRiwayatSppTampilPresenter;
import com.example.tababsensiapp.Activities.Admin.Transaksi.Riwayat.SPP.Tampil.presenter.IAdminTransaksiRiwayatSppTampilPresenter;
import com.example.tababsensiapp.Activities.Admin.Transaksi.Riwayat.SPP.Tampil.view.IAdminTransaksiRiwayatSppTampilView;
import com.example.tababsensiapp.Activities.Admin.Transaksi.SPP.Tampil.AdminTransaksiSppTampilActivity;
import com.example.tababsensiapp.Adapters.AdapterDaftarBayarSpp;
import com.example.tababsensiapp.Adapters.AdapterDaftarPenggajian;
import com.example.tababsensiapp.Models.BayarSpp;
import com.example.tababsensiapp.Models.Penggajian;
import com.example.tababsensiapp.R;

import java.util.ArrayList;

import es.dmoral.toasty.Toasty;

public class AdminTransaksiRiwayatSppTampilActivity extends AppCompatActivity implements View.OnClickListener, IAdminTransaksiRiwayatSppTampilView {

    public static final String EXTRA_ID_WALI_MURID = "EXTRA_ID_WALI_MURID";
    String id_wali_murid = "";

    IAdminTransaksiRiwayatSppTampilPresenter adminTransaksiRiwayatSppTampilPresenter;
    private AdapterDaftarBayarSpp adapterDaftarBayarSpp;
    private RecyclerView recyclerView;
    Toolbar toolbar;
    private SwipeRefreshLayout swipeRefreshLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_transaksi_riwayat_spp_tampil);

        id_wali_murid = getIntent().getStringExtra(EXTRA_ID_WALI_MURID);

        adminTransaksiRiwayatSppTampilPresenter = new AdminTransaksiRiwayatSppTampilPresenter(this, this);
        adminTransaksiRiwayatSppTampilPresenter.onLoadSemuaData(id_wali_murid);

        recyclerView = findViewById(R.id.recycle_view);

        toolbar = findViewById(R.id.toolbar);
        initActionBar();

        swipeRefreshLayout = findViewById(R.id.swipe_refresh_layout);

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                // Your code to make your refresh action
                adminTransaksiRiwayatSppTampilPresenter.onLoadSemuaData(id_wali_murid);

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
    public void onSetupListView(ArrayList<BayarSpp> dataModelArrayList) {
        adapterDaftarBayarSpp = new AdapterDaftarBayarSpp(this, dataModelArrayList);
        GridLayoutManager layoutManager = new GridLayoutManager(this, 1, GridLayoutManager.VERTICAL, false);
        recyclerView.setAdapter(adapterDaftarBayarSpp);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setNestedScrollingEnabled(true);
        adapterDaftarBayarSpp.notifyDataSetChanged();

        adapterDaftarBayarSpp.setOnItemClickListener(new AdapterDaftarBayarSpp.ClickListener() {
            @Override
            public void onClick(View view, int position) {
                Intent intent = new Intent(getApplicationContext(), AdminTransaksiSppTampilActivity.class);
                intent.putExtra(AdminTransaksiSppTampilActivity.EXTRA_ID_WALI_MURID, dataModelArrayList.get(position).getId_wali_murid());
                intent.putExtra(AdminTransaksiSppTampilActivity.EXTRA_ID_BAYAR_SPP, dataModelArrayList.get(position).getId_bayar_spp());
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
        adminTransaksiRiwayatSppTampilPresenter.onLoadSemuaData(id_wali_murid);
    }
}
