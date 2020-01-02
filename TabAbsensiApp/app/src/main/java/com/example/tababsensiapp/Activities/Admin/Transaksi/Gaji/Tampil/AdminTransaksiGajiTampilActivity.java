package com.example.tababsensiapp.Activities.Admin.Transaksi.Gaji.Tampil;

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

import com.example.tababsensiapp.Activities.Admin.Transaksi.Gaji.Tampil.presenter.AdminTransaksiGajiTampilPresenter;
import com.example.tababsensiapp.Activities.Admin.Transaksi.Gaji.Tampil.presenter.IAdminTransaksiGajiTampilPresenter;
import com.example.tababsensiapp.Activities.Admin.Transaksi.Gaji.Tampil.view.IAdminTransaksiGajiTampilView;
import com.example.tababsensiapp.Activities.Pengajar.Absensi.Pertemuan.PengajarAbsensiPertemuanActivity;
import com.example.tababsensiapp.Adapters.AdapterPengajarDaftarKelasAktif;
import com.example.tababsensiapp.Controllers.SessionManager;
import com.example.tababsensiapp.Models.Pertemuan;
import com.example.tababsensiapp.R;

import java.util.ArrayList;
import java.util.HashMap;

import es.dmoral.toasty.Toasty;

public class AdminTransaksiGajiTampilActivity extends AppCompatActivity implements View.OnClickListener, IAdminTransaksiGajiTampilView {

    public static final String EXTRA_ID_PENGGAJIAN = "EXTRA_ID_PENGGAJIAN";
    String id_penggajian = "";

    SessionManager sessionManager;

    IAdminTransaksiGajiTampilPresenter adminTransaksiGajiTampilPresenter;

    private AdapterPengajarDaftarKelasAktif adapterPengajarDaftarKelasAktif;
    private RecyclerView recyclerView;

    Toolbar toolbar;

    private SwipeRefreshLayout swipeRefreshLayout;

    public static final String EXTRA_ID_PENGAJAR = "EXTRA_ID_PENGAJAR";
    String id_pengajar;

    TextView tvNamaPengajar, tvTotalPertemuan, tvTotalHargaFee;
    Button btnBayarFee;
    LinearLayout layoutKet;

    String id_admin, total_pertemuan, total_harga_fee;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_transaksi_gaji_tampil);

        sessionManager = new SessionManager(this);
        HashMap<String, String> user = sessionManager.getDataUser();
        id_admin = user.get(sessionManager.ID_USER);


        tvNamaPengajar = findViewById(R.id.tv_nama_pengajar);
        tvTotalPertemuan = findViewById(R.id.tv_total_pertemuan);
        tvTotalHargaFee = findViewById(R.id.tv_total_harga_fee);

        btnBayarFee = findViewById(R.id.btn_bayar_fee);
        layoutKet = findViewById(R.id.layout_ket);

        id_pengajar = getIntent().getStringExtra(EXTRA_ID_PENGAJAR);
        id_penggajian = getIntent().getStringExtra(EXTRA_ID_PENGGAJIAN);

        adminTransaksiGajiTampilPresenter = new AdminTransaksiGajiTampilPresenter(this, this);
        adminTransaksiGajiTampilPresenter.inisiasiAwal(id_pengajar, id_penggajian);

        recyclerView = findViewById(R.id.recycle_view);

        toolbar = findViewById(R.id.toolbar);
        initActionBar();

        swipeRefreshLayout = findViewById(R.id.swipe_refresh_layout);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                // Your code to make your refresh action
                adminTransaksiGajiTampilPresenter.inisiasiAwal(id_pengajar, id_penggajian);

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

        btnBayarFee.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btn_bayar_fee) {
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
    public void onSetupListView(ArrayList<Pertemuan> dataModelArrayList, String nama_pengajar, String total_pertemuan, String harga_fee) {

        tvNamaPengajar.setText("Nama : " + nama_pengajar);
        tvTotalPertemuan.setText("Total Pertemuan : " + total_pertemuan);
        tvTotalHargaFee.setText("Total Fee : " + harga_fee);

        this.total_pertemuan = total_pertemuan;
        total_harga_fee = harga_fee;

        if (dataModelArrayList.size() == 0) {
            btnBayarFee.setVisibility(View.GONE);
            layoutKet.setVisibility(View.GONE);
        }

        if (!id_penggajian.equals("kosong")) {
            btnBayarFee.setVisibility(View.GONE);
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
                            adminTransaksiGajiTampilPresenter.onBayar(id_pengajar, id_admin, total_pertemuan, total_harga_fee);
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
        adminTransaksiGajiTampilPresenter.inisiasiAwal(id_pengajar, id_penggajian);
    }
}
