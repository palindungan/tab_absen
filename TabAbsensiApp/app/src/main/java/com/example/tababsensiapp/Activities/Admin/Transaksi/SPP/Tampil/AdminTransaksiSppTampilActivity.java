package com.example.tababsensiapp.Activities.Admin.Transaksi.SPP.Tampil;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tababsensiapp.Activities.Admin.Transaksi.SPP.Tampil.presenter.AdminTransaksiSppTampilPresenter;
import com.example.tababsensiapp.Activities.Admin.Transaksi.SPP.Tampil.presenter.IAdminTransaksiSppTampilPresenter;
import com.example.tababsensiapp.Activities.Admin.Transaksi.SPP.Tampil.view.IAdminTransaksiSppTampilView;
import com.example.tababsensiapp.Activities.Pengajar.Absensi.Pertemuan.PengajarAbsensiPertemuanActivity;
import com.example.tababsensiapp.Adapters.AdapterPengajarDaftarKelasAktif;
import com.example.tababsensiapp.Controllers.SessionManager;
import com.example.tababsensiapp.Models.Pertemuan;
import com.example.tababsensiapp.R;

import java.util.ArrayList;
import java.util.HashMap;

import es.dmoral.toasty.Toasty;

public class AdminTransaksiSppTampilActivity extends AppCompatActivity implements View.OnClickListener, IAdminTransaksiSppTampilView {

    public static final String EXTRA_ID_BAYAR_SPP = "EXTRA_ID_BAYAR_SPP";
    String id_bayar_spp = "";
    public static final String EXTRA_ID_WALI_MURID = "EXTRA_ID_WALI_MURID";
    String id_wali_murid = "";

    SessionManager sessionManager;

    IAdminTransaksiSppTampilPresenter adminTransaksiSppTampilPresenter;

    private AdapterPengajarDaftarKelasAktif adapterPengajarDaftarKelasAktif;
    private RecyclerView recyclerView;

    Toolbar toolbar;

    private SwipeRefreshLayout swipeRefreshLayout;

    TextView tvNamaPengajar, tvTotalPertemuan, tvTotalSpp;
    Button btnBayarSpp;
    LinearLayout layoutKet;

    String id_admin, total_pertemuan, total_spp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_transaksi_spp_tampil);

        sessionManager = new SessionManager(this);
        HashMap<String, String> user = sessionManager.getDataUser();
        id_admin = user.get(sessionManager.ID_USER);


        tvNamaPengajar = findViewById(R.id.tv_nama_pengajar);
        tvTotalPertemuan = findViewById(R.id.tv_total_pertemuan);
        tvTotalSpp = findViewById(R.id.tv_total_spp);

        btnBayarSpp = findViewById(R.id.btn_bayar_spp);
        layoutKet = findViewById(R.id.layout_ket);

        id_wali_murid = getIntent().getStringExtra(EXTRA_ID_WALI_MURID);
        id_bayar_spp = getIntent().getStringExtra(EXTRA_ID_BAYAR_SPP);

        adminTransaksiSppTampilPresenter = new AdminTransaksiSppTampilPresenter(this, this);
        adminTransaksiSppTampilPresenter.inisiasiAwal(id_wali_murid, id_bayar_spp);

        recyclerView = findViewById(R.id.recycle_view);

        toolbar = findViewById(R.id.toolbar);
        initActionBar();

        swipeRefreshLayout = findViewById(R.id.swipe_refresh_layout);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                // Your code to make your refresh action
                adminTransaksiSppTampilPresenter.inisiasiAwal(id_wali_murid, id_bayar_spp);

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

        btnBayarSpp.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btn_bayar_spp) {
            showDialogTransaksi();
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
    public void onSetupListView(ArrayList<Pertemuan> dataModelArrayList, String nama_pengajar, String total_pertemuan, String total_spp) {
        tvNamaPengajar.setText("Nama : " + nama_pengajar);
        tvTotalPertemuan.setText("Total Pertemuan : " + total_pertemuan);
        tvTotalSpp.setText("Total Spp : " + total_spp);

        this.total_pertemuan = total_pertemuan;
        this.total_spp = total_spp;

        if (dataModelArrayList.size() == 0) {
            btnBayarSpp.setVisibility(View.GONE);
            layoutKet.setVisibility(View.GONE);
        }

        if (!id_bayar_spp.equals("kosong")) {
            btnBayarSpp.setVisibility(View.GONE);
//            layoutKet.setVisibility(View.GONE);
        }

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
    public void showDialogTransaksi() {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                this);
        alertDialogBuilder.setTitle("Yakin Melakukan Pembayaran Fee ?");
        alertDialogBuilder
                .setMessage("Klik bayar untuk pembayaran !")
                .setPositiveButton("Bayar", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {

                        try {
                            adminTransaksiSppTampilPresenter.onBayar(id_wali_murid, id_admin, total_pertemuan, total_spp);
                        } catch (Exception e) {
                            onErrorMessage("Terjadi Kesalahan Transaksi " + e.toString());
                        }

                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });

        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
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
        adminTransaksiSppTampilPresenter.inisiasiAwal(id_wali_murid, id_bayar_spp);
    }
}
