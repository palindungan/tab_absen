package com.example.tababsensiapp.Activities.Pengajar.Kelas.TampilSemua;

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

import com.example.tababsensiapp.Activities.Pengajar.AbsensiPertemuan.PengajarAbsensiPertemuanActivity;
import com.example.tababsensiapp.Activities.Pengajar.Kelas.TampilSemua.presenter.IPengajarKelasTampilSemuaPresenter;
import com.example.tababsensiapp.Activities.Pengajar.Kelas.TampilSemua.presenter.PengajarKelasTampilSemuaPresenter;
import com.example.tababsensiapp.Activities.Pengajar.Kelas.TampilSemua.view.IPengajarKelasTampilSemuaView;
import com.example.tababsensiapp.Adapters.AdapterDaftarKelas;
import com.example.tababsensiapp.Adapters.AdapterPengajarDaftarKelas;
import com.example.tababsensiapp.Controllers.SessionManager;
import com.example.tababsensiapp.Models.Kelas;
import com.example.tababsensiapp.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.HashMap;

import es.dmoral.toasty.Toasty;

public class PengajarKelasTampilSemuaActivity extends AppCompatActivity implements View.OnClickListener, IPengajarKelasTampilSemuaView {

    IPengajarKelasTampilSemuaPresenter pengajarKelasTampilSemuaPresenter;

    private AdapterPengajarDaftarKelas adapterPengajarDaftarKelas;
    private RecyclerView recyclerView;

    Toolbar toolbar;

    private SwipeRefreshLayout swipeRefreshLayout;
    FloatingActionButton fab;

    SessionManager sessionManager;
    String id_pengajar = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pengajar_kelas_tampil_semua);

        sessionManager = new SessionManager(this);

        pengajarKelasTampilSemuaPresenter = new PengajarKelasTampilSemuaPresenter(this, this);
        HashMap<String, String> user = sessionManager.getDataUser();
        id_pengajar = user.get(sessionManager.ID_USER);
        pengajarKelasTampilSemuaPresenter.inisiasiAwal(id_pengajar);

        recyclerView = findViewById(R.id.recycle_view);

        toolbar = findViewById(R.id.toolbar);
        initActionBar();

        swipeRefreshLayout = findViewById(R.id.swipe_refresh_layout);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                // Your code to make your refresh action
                pengajarKelasTampilSemuaPresenter.inisiasiAwal(id_pengajar);

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
    public void onSetupListView(ArrayList<Kelas> dataModelArrayList) {
        adapterPengajarDaftarKelas = new AdapterPengajarDaftarKelas(this, dataModelArrayList);
        GridLayoutManager layoutManager = new GridLayoutManager(this, 1, GridLayoutManager.VERTICAL, false);
        recyclerView.setAdapter(adapterPengajarDaftarKelas);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setNestedScrollingEnabled(true);
        adapterPengajarDaftarKelas.notifyDataSetChanged();

        adapterPengajarDaftarKelas.setOnItemClickListener(new AdapterPengajarDaftarKelas.ClickListener() {
            @Override
            public void onClick(View view, int position) {
//                Intent intent = new Intent(getApplicationContext(), AdminKelasDetailKelasActivity.class);
//                intent.putExtra(AdminKelasDetailKelasActivity.EXTRA_ID_KELAS_P, dataModelArrayList.get(position).getId_kelas_p());
//                intent.putExtra(AdminKelasDetailKelasActivity.EXTRA_ID_MATA_PELAJARAN, dataModelArrayList.get(position).getId_mata_pelajaran());
//                intent.putExtra(AdminKelasDetailKelasActivity.EXTRA_ID_PENGAJAR, dataModelArrayList.get(position).getId_pengajar());
//                startActivity(intent);

                Intent intent = new Intent(getApplicationContext(), PengajarAbsensiPertemuanActivity.class);
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
        pengajarKelasTampilSemuaPresenter.inisiasiAwal(id_pengajar);
    }
}
