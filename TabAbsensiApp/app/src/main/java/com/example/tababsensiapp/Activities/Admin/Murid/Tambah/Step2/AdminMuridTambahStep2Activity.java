package com.example.tababsensiapp.Activities.Admin.Murid.Tambah.Step2;

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
import android.widget.Toast;

import com.example.tababsensiapp.Activities.Admin.Murid.Tambah.Step2.presenter.AdminMuridTambahStep2Presenter;
import com.example.tababsensiapp.Activities.Admin.Murid.Tambah.Step2.view.IAdminMuridTambahStep2View;
import com.example.tababsensiapp.Activities.Admin.Murid.Tampil.AdminMuridTampilActivity;
import com.example.tababsensiapp.Adapters.AdapterDaftarWaliMurid;
import com.example.tababsensiapp.Models.WaliMurid;
import com.example.tababsensiapp.R;

import java.util.ArrayList;

import es.dmoral.toasty.Toasty;

public class AdminMuridTambahStep2Activity extends AppCompatActivity implements View.OnClickListener , IAdminMuridTambahStep2View {

    public static final String EXTRA_NAMA = "EXTRA_NAMA";
    public static final String EXTRA_FOTO = "EXTRA_FOTO";

    AdminMuridTambahStep2Presenter adminMuridTambahStep2Presenter;

    private AdapterDaftarWaliMurid adapterDaftarWaliMurid;
    private RecyclerView recyclerView;

    Toolbar toolbar;

    private SwipeRefreshLayout swipeRefreshLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_murid_tambah_step2);

        onInfoMessage("Pilih Wali Murid !");

        adminMuridTambahStep2Presenter = new AdminMuridTambahStep2Presenter(this, this);
        adminMuridTambahStep2Presenter.onLoadSemuaData();

        recyclerView = findViewById(R.id.recycle_view);

        toolbar = findViewById(R.id.toolbar);
        initActionBar();

        swipeRefreshLayout = findViewById(R.id.swipe_refresh_layout);

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                // Your code to make your refresh action
                adminMuridTambahStep2Presenter.onLoadSemuaData();

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
                showDialog(dataModelArrayList.get(position).getId_wali_murid(), dataModelArrayList.get(position).getNama(), dataModelArrayList.get(position).getAlamat());
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
    public void onInfoMessage(String message) {
        Toasty.info(this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showDialog(String id_wali_murid, String nama_wali_murid, String alamat) {
        String nama = getIntent().getStringExtra(EXTRA_NAMA);
        String foto = getIntent().getStringExtra(EXTRA_FOTO);

        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                this);
        alertDialogBuilder.setTitle("Ingin Menambah Data Murid Baru ?");
        alertDialogBuilder
                .setMessage("Nama Murid : " + nama + "\n" + "Nama Wali Murid : " + nama_wali_murid + "\n" + "Alamat  : " + alamat)
                .setPositiveButton("Ya", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        try {

                            adminMuridTambahStep2Presenter.onSendData(id_wali_murid, nama, foto);

                        } catch (Exception e) {
                            onErrorMessage("Terjadi Kesalahan Submit " + e.toString());
                        }

                    }
                })
                .setNegativeButton("Tidak", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });

        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }

    @Override
    public void finish() {
        Intent intent = new Intent(getApplicationContext(), AdminMuridTampilActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
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
        adminMuridTambahStep2Presenter.onLoadSemuaData();
    }
}
