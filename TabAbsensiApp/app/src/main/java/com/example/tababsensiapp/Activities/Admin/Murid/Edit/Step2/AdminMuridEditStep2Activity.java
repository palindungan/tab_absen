package com.example.tababsensiapp.Activities.Admin.Murid.Edit.Step2;

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
import android.widget.Toast;

import com.example.tababsensiapp.Activities.Admin.Murid.Edit.Step2.presenter.AdminMuridEditStep2Presenter;
import com.example.tababsensiapp.Activities.Admin.Murid.Edit.Step2.presenter.IAdminMuridEditStep2Presenter;
import com.example.tababsensiapp.Activities.Admin.Murid.Edit.Step2.view.IAdminMuridEditStep2View;
import com.example.tababsensiapp.Activities.Admin.Murid.Tampil.AdminMuridTampilActivity;
import com.example.tababsensiapp.Adapters.AdapterDaftarWaliMurid;
import com.example.tababsensiapp.Models.WaliMurid;
import com.example.tababsensiapp.R;

import java.util.ArrayList;

import es.dmoral.toasty.Toasty;

public class AdminMuridEditStep2Activity extends AppCompatActivity implements View.OnClickListener, IAdminMuridEditStep2View {

    public static final String EXTRA_ID_MURID = "EXTRA_ID_MURID";
    public static final String EXTRA_ID_WALI_MURID = "EXTRA_ID_WALI_MURID";
    public static final String EXTRA_NAMA = "EXTRA_NAMA";
    public static final String EXTRA_FOTO = "EXTRA_FOTO";

    IAdminMuridEditStep2Presenter adminMuridEditStep2Presenter;

    private AdapterDaftarWaliMurid adapterDaftarWaliMurid;
    private RecyclerView recyclerView;
    private Button btnFinish;

    Toolbar toolbar;

    private SwipeRefreshLayout swipeRefreshLayout;

    String id_murid, id_wali_murid, nama, foto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_murid_edit_step2);

        id_murid = getIntent().getStringExtra(EXTRA_ID_MURID);
        id_wali_murid = getIntent().getStringExtra(EXTRA_ID_WALI_MURID);
        nama = getIntent().getStringExtra(EXTRA_NAMA);
        foto = getIntent().getStringExtra(EXTRA_FOTO);

        onInfoMessage("Pilih Wali Murid !");

        adminMuridEditStep2Presenter = new AdminMuridEditStep2Presenter(this, this);
        adminMuridEditStep2Presenter.onLoadSemuaData();

        recyclerView = findViewById(R.id.recycle_view);
        btnFinish = findViewById(R.id.btn_finish);

        toolbar = findViewById(R.id.toolbar);
        initActionBar();

        swipeRefreshLayout = findViewById(R.id.swipe_refresh_layout);

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                // Your code to make your refresh action
                adminMuridEditStep2Presenter.onLoadSemuaData();

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

        btnFinish.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btn_finish) {
            showDialogFinish();
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
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                this);
        alertDialogBuilder.setTitle("Ingin Mengupdate Data Murid ?");
        alertDialogBuilder
                .setMessage("Nama Murid : " + nama + "\n" + "Nama Wali Murid : " + nama_wali_murid + "\n" + "Alamat  : " + alamat)
                .setPositiveButton("Ya", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        try {
                            adminMuridEditStep2Presenter.onUpdateData(id_murid, id_wali_murid, nama, foto);
                        } catch (Exception e) {
                            onErrorMessage("Terjadi Kesalahan Update " + e.toString());
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
    public void showDialogFinish() {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                this);
        alertDialogBuilder.setTitle("Ingin Mengupdate dan Menggunakan Data Wali Murid Lama ?");
        alertDialogBuilder
                .setMessage("Klik Ya untuk update Nama Murid : " + nama)
                .setPositiveButton("Ya", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        try {
                            adminMuridEditStep2Presenter.onUpdateData(id_murid, id_wali_murid, nama, foto);
                        } catch (Exception e) {
                            onErrorMessage("Terjadi Kesalahan Update " + e.toString());
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
    public void stepFinish() {
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
        adminMuridEditStep2Presenter.onLoadSemuaData();
    }
}
