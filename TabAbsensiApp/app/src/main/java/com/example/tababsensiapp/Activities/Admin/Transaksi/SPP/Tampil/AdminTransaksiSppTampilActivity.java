package com.example.tababsensiapp.Activities.Admin.Transaksi.SPP.Tampil;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.content.DialogInterface;
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
import com.example.tababsensiapp.Adapters.AdapterPengajarDaftarKelasAktif;
import com.example.tababsensiapp.Controllers.SessionManager;
import com.example.tababsensiapp.Models.BayarSpp;
import com.example.tababsensiapp.Models.Pertemuan;
import com.example.tababsensiapp.R;

import java.util.ArrayList;
import java.util.HashMap;

import es.dmoral.toasty.Toasty;

public class AdminTransaksiSppTampilActivity extends AppCompatActivity implements View.OnClickListener, IAdminTransaksiSppTampilView {

    public static final String EXTRA_ID_BAYAR_SPP = "EXTRA_ID_BAYAR_SPP";
    String id_bayar_spp = "";
    public static final String EXTRA_ID_MURID = "EXTRA_ID_MURID";
    String id_murid = "";

    SessionManager sessionManager;

    IAdminTransaksiSppTampilPresenter adminTransaksiSppTampilPresenter;

    private AdapterPengajarDaftarKelasAktif adapterPengajarDaftarKelasAktif;
    private RecyclerView recyclerView;

    Toolbar toolbar;

    private SwipeRefreshLayout swipeRefreshLayout;

    TextView tvNamaMurid, tvTotalPertemuan, tvTotalSpp;
    Button btnBayarSpp;
    LinearLayout layoutKet;

    String id_admin, total_pertemuan, total_spp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_transaksi_spp);

        sessionManager = new SessionManager(this);
        HashMap<String, String> user = sessionManager.getDataUser();
        id_admin = user.get(sessionManager.ID_USER);


        tvNamaMurid = findViewById(R.id.tv_nama_murid);
        tvTotalPertemuan = findViewById(R.id.tv_total_pertemuan);
        tvTotalSpp = findViewById(R.id.tv_total_spp);

        btnBayarSpp = findViewById(R.id.btn_bayar_spp);
        layoutKet = findViewById(R.id.layout_ket);

        id_murid = getIntent().getStringExtra(EXTRA_ID_MURID);
        id_bayar_spp = getIntent().getStringExtra(EXTRA_ID_BAYAR_SPP);

        adminTransaksiSppTampilPresenter = new AdminTransaksiSppTampilPresenter(this, this);
        adminTransaksiSppTampilPresenter.inisiasiAwal(id_murid,id_bayar_spp);

        recyclerView = findViewById(R.id.recycle_view);

        toolbar = findViewById(R.id.toolbar);
        initActionBar();

        swipeRefreshLayout = findViewById(R.id.swipe_refresh_layout);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                // Your code to make your refresh action
                adminTransaksiSppTampilPresenter.inisiasiAwal(id_murid,id_bayar_spp);

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
                            adminTransaksiSppTampilPresenter.onBayar(id_murid, id_admin, total_pertemuan, total_spp);
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
        adminTransaksiSppTampilPresenter.inisiasiAwal(id_murid,id_bayar_spp);
    }
}
