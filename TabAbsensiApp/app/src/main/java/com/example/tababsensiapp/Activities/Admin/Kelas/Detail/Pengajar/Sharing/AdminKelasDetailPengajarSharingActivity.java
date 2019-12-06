package com.example.tababsensiapp.Activities.Admin.Kelas.Detail.Pengajar.Sharing;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.tababsensiapp.Activities.Admin.Kelas.Detail.Pengajar.Sharing.presenter.AdminKelasDetailPengajarSharingPresenter;
import com.example.tababsensiapp.Activities.Admin.Kelas.Detail.Pengajar.Sharing.presenter.IAdminKelasDetailPengajarSharingPresenter;
import com.example.tababsensiapp.Activities.Admin.Kelas.Detail.Pengajar.Sharing.view.IAdminKelasDetailPengajarSharingView;
import com.example.tababsensiapp.Adapters.AdapterDaftarPengajar;
import com.example.tababsensiapp.Models.Pengajar;
import com.example.tababsensiapp.R;

import java.util.ArrayList;

import es.dmoral.toasty.Toasty;

public class AdminKelasDetailPengajarSharingActivity extends AppCompatActivity implements View.OnClickListener, IAdminKelasDetailPengajarSharingView {

    IAdminKelasDetailPengajarSharingPresenter adminKelasDetailPengajarSharingPresenter;

    private AdapterDaftarPengajar adapterDaftarPengajar;
    private RecyclerView recyclerView;

    Toolbar toolbar;

    private SwipeRefreshLayout swipeRefreshLayout;

    public static String EXTRA_ID_KELAS_P = "EXTRA_ID_KELAS_P";
    String id_kelas_p = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_kelas_detail_pengajar_sharing);

        adminKelasDetailPengajarSharingPresenter = new AdminKelasDetailPengajarSharingPresenter(this, this);
        adminKelasDetailPengajarSharingPresenter.onLoadSemuaData();

        id_kelas_p = getIntent().getStringExtra(EXTRA_ID_KELAS_P);

        recyclerView = findViewById(R.id.recycle_view);

        toolbar = findViewById(R.id.toolbar);
        initActionBar();

        swipeRefreshLayout = findViewById(R.id.swipe_refresh_layout);

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                // Your code to make your refresh action
                adminKelasDetailPengajarSharingPresenter.onLoadSemuaData();

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
                String id_sharing = dataModelArrayList.get(position).getId_pengajar();
                String nama_sharing = dataModelArrayList.get(position).getNama();
                showDialogUpdate(id_kelas_p, id_sharing, nama_sharing);
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
    public void showDialogUpdate(String id_kelas_p, String id_sharing, String nama_sharing) {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                this);
        alertDialogBuilder.setTitle("Ingin Membagikan Kelas Kepada " + nama_sharing + " ?");
        alertDialogBuilder
                .setMessage("Klik Ya untuk melakukan update !")
                .setPositiveButton("Ya", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {

                        try {

                            adminKelasDetailPengajarSharingPresenter.onUpdate(id_kelas_p, id_sharing, nama_sharing);

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
        adminKelasDetailPengajarSharingPresenter.onLoadSemuaData();
    }
}
