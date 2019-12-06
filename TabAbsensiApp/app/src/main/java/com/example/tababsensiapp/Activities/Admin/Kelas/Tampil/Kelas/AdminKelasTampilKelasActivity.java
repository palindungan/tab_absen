package com.example.tababsensiapp.Activities.Admin.Kelas.Tampil.Kelas;

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
import android.widget.EditText;
import android.widget.Toast;

import com.example.tababsensiapp.Activities.Admin.Kelas.Detail.Kelas.AdminKelasDetailKelasActivity;
import com.example.tababsensiapp.Activities.Admin.Kelas.Tambah.AdminKelasTambahActivity;
import com.example.tababsensiapp.Activities.Admin.Kelas.Tampil.Kelas.presenter.AdminKelasTampilKelasPresenter;
import com.example.tababsensiapp.Activities.Admin.Kelas.Tampil.Kelas.presenter.IAdminKelasTampilKelasPresenter;
import com.example.tababsensiapp.Activities.Admin.Kelas.Tampil.Kelas.view.IAdminKelasTampilKelasView;
import com.example.tababsensiapp.Adapters.AdapterDaftarKelas;
import com.example.tababsensiapp.Models.Kelas;
import com.example.tababsensiapp.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

import es.dmoral.toasty.Toasty;

public class AdminKelasTampilKelasActivity extends AppCompatActivity implements View.OnClickListener, IAdminKelasTampilKelasView {

    IAdminKelasTampilKelasPresenter adminKelasTampilKelasPresenter;

    private AdapterDaftarKelas adapterDaftarKelas;
    private RecyclerView recyclerView;

    Toolbar toolbar;

    private SwipeRefreshLayout swipeRefreshLayout;
    FloatingActionButton fab;

    public static final String EXTRA_ID_PENGAJAR = "EXTRA_ID_PENGAJAR";
    String id_pengajar = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_kelas_tampil_kelas);

        adminKelasTampilKelasPresenter = new AdminKelasTampilKelasPresenter(this, this);
        id_pengajar = getIntent().getStringExtra(EXTRA_ID_PENGAJAR);
        adminKelasTampilKelasPresenter.inisiasiAwal(id_pengajar);

        recyclerView = findViewById(R.id.recycle_view);

        toolbar = findViewById(R.id.toolbar);
        initActionBar();

        fab = findViewById(R.id.fab);
        swipeRefreshLayout = findViewById(R.id.swipe_refresh_layout);

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                // Your code to make your refresh action
                adminKelasTampilKelasPresenter.inisiasiAwal(id_pengajar);

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

        fab.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.fab) {
            Intent intent = new Intent(getApplicationContext(), AdminKelasTambahActivity.class);
            intent.putExtra(AdminKelasTambahActivity.EXTRA_ID_PENGAJAR, id_pengajar);
            startActivity(intent);
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
    public void onSetupListView(ArrayList<Kelas> dataModelArrayList) {
        adapterDaftarKelas = new AdapterDaftarKelas(this, dataModelArrayList);
        GridLayoutManager layoutManager = new GridLayoutManager(this, 1, GridLayoutManager.VERTICAL, false);
        recyclerView.setAdapter(adapterDaftarKelas);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setNestedScrollingEnabled(true);
        adapterDaftarKelas.notifyDataSetChanged();

        adapterDaftarKelas.setOnItemClickListener(new AdapterDaftarKelas.ClickListener() {
            @Override
            public void onClick(View view, int position) {
                Intent intent = new Intent(getApplicationContext(), AdminKelasDetailKelasActivity.class);
                intent.putExtra(AdminKelasDetailKelasActivity.EXTRA_ID_KELAS_P, dataModelArrayList.get(position).getId_kelas_p());
                intent.putExtra(AdminKelasDetailKelasActivity.EXTRA_ID_MATA_PELAJARAN, dataModelArrayList.get(position).getId_mata_pelajaran());
                intent.putExtra(AdminKelasDetailKelasActivity.EXTRA_ID_PENGAJAR, dataModelArrayList.get(position).getId_pengajar());
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
    public void backPressed() {
        onResume();
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
        adminKelasTampilKelasPresenter.inisiasiAwal(id_pengajar);
    }
}
