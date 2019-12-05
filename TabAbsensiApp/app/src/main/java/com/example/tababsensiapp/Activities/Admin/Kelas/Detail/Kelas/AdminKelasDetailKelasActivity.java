package com.example.tababsensiapp.Activities.Admin.Kelas.Detail.Kelas;

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
import android.widget.TextView;
import android.widget.Toast;

import com.example.tababsensiapp.Activities.Admin.Kelas.Detail.Kelas.presenter.AdminKelasDetailKelasPresenter;
import com.example.tababsensiapp.Activities.Admin.Kelas.Detail.Kelas.presenter.IAdminKelasDetailKelasPresenter;
import com.example.tababsensiapp.Activities.Admin.Kelas.Detail.Kelas.view.IAdminKelasDetailKelasView;
import com.example.tababsensiapp.Activities.Admin.Kelas.Detail.Murid.Detail.AdminKelasDetailMuridDetailActivity;
import com.example.tababsensiapp.Activities.Admin.Kelas.Detail.Murid.Tampil.AdminKelasDetailMuridTampilActivity;
import com.example.tababsensiapp.Adapters.AdapterDaftarKelasMurid;
import com.example.tababsensiapp.Models.Murid;
import com.example.tababsensiapp.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.squareup.picasso.MemoryPolicy;
import com.squareup.picasso.NetworkPolicy;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import es.dmoral.toasty.Toasty;

public class AdminKelasDetailKelasActivity extends AppCompatActivity implements View.OnClickListener, IAdminKelasDetailKelasView {

    public static final String EXTRA_ID_KELAS_P = "EXTRA_ID_KELAS_P";
    public static final String EXTRA_HARI = "EXTRA_HARI";
    public static final String EXTRA_JAM_MULAI = "EXTRA_JAM_MULAI";
    public static final String EXTRA_JAM_BERAKHIR = "EXTRA_JAM_BERAKHIR";
    public static final String EXTRA_HARGA_FEE = "EXTRA_HARGA_FEE";
    public static final String EXTRA_ID_MATA_PELAJARAN = "EXTRA_ID_MATA_PELAJARAN";
    public static final String EXTRA_NAMA_PELAJARAN = "EXTRA_NAMA_PELAJARAN";
    public static final String EXTRA_ID_PENGAJAR = "EXTRA_ID_PENGAJAR";
    public static final String EXTRA_NAMA_PENGAJAR = "EXTRA_NAMA_PENGAJAR";
    public static final String EXTRA_ID_SHARING = "EXTRA_ID_SHARING";
    public static final String EXTRA_NAMA_SHARING = "EXTRA_NAMA_SHARING";

    String id_kelas_p, hari, jam_mulai, jam_berakhir, harga_fee, nama_pelajaran, nama_sharing = "";
    String id_mata_pelajaran, id_pengajar, nama_pengajar, id_sharing = "";

    IAdminKelasDetailKelasPresenter adminKelasDetailKelasPresenter;

    private AdapterDaftarKelasMurid adapterDaftarKelasMurid;
    private RecyclerView recyclerView;

    Toolbar toolbar;

    private SwipeRefreshLayout swipeRefreshLayout;
    FloatingActionButton fab;

    TextView tvNamaPelajaran, tvNamaPengajar, tvHari, tvJam, tvHargaFee, tvStatus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_kelas_detail_kelas);

        recyclerView = findViewById(R.id.recycle_view);

        tvNamaPelajaran = findViewById(R.id.tv_nama_pelajaran);
        tvNamaPengajar = findViewById(R.id.tv_nama_pengajar);
        tvHari = findViewById(R.id.tv_hari);
        tvJam = findViewById(R.id.tv_jam);
        tvHargaFee = findViewById(R.id.tv_harga_fee);
        tvStatus = findViewById(R.id.tv_status);

        id_kelas_p = getIntent().getStringExtra(EXTRA_ID_KELAS_P);
        hari = getIntent().getStringExtra(EXTRA_HARI);
        jam_mulai = getIntent().getStringExtra(EXTRA_JAM_MULAI);
        jam_berakhir = getIntent().getStringExtra(EXTRA_JAM_BERAKHIR);
        harga_fee = getIntent().getStringExtra(EXTRA_HARGA_FEE);
        nama_pelajaran = getIntent().getStringExtra(EXTRA_NAMA_PELAJARAN);
        nama_sharing = getIntent().getStringExtra(EXTRA_NAMA_SHARING);

        id_mata_pelajaran = getIntent().getStringExtra(EXTRA_ID_MATA_PELAJARAN);
        id_pengajar = getIntent().getStringExtra(EXTRA_ID_PENGAJAR);
        nama_pengajar = getIntent().getStringExtra(EXTRA_NAMA_PENGAJAR);
        id_sharing = getIntent().getStringExtra(EXTRA_ID_SHARING);

        adminKelasDetailKelasPresenter = new AdminKelasDetailKelasPresenter(this, this);
        adminKelasDetailKelasPresenter.onLoadSemuaData(id_kelas_p);

        toolbar = findViewById(R.id.toolbar);
        initActionBar();

        fab = findViewById(R.id.fab);
        swipeRefreshLayout = findViewById(R.id.swipe_refresh_layout);

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                // Your code to make your refresh action
                adminKelasDetailKelasPresenter.onLoadSemuaData(id_kelas_p);

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
            Intent intent = new Intent(getApplicationContext(), AdminKelasDetailMuridTampilActivity.class);
            intent.putExtra(AdminKelasDetailMuridTampilActivity.EXTRA_ID_KELAS_P, id_kelas_p);
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
    public void setNilaiDefault() {
        tvNamaPelajaran.setText(nama_pelajaran);
        tvNamaPengajar.setText(nama_pengajar);
        tvHargaFee.setText(harga_fee);
        tvHari.setText(hari);
        tvJam.setText(jam_mulai + " - " + jam_berakhir);

        if (!id_sharing.equals("null")) {
            tvStatus.setText("Status : Dibagikan(" + nama_sharing + ")");
        }
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
    public void onSetupListView(ArrayList<Murid> dataModelArrayList) {
        adapterDaftarKelasMurid = new AdapterDaftarKelasMurid(this, dataModelArrayList);
        GridLayoutManager layoutManager = new GridLayoutManager(this, 1, GridLayoutManager.VERTICAL, false);
        recyclerView.setAdapter(adapterDaftarKelasMurid);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setNestedScrollingEnabled(true);
        adapterDaftarKelasMurid.setOnItemClickListener(new AdapterDaftarKelasMurid.ClickListener() {
            @Override
            public void onClick(View view, int position) {
                Intent intent = new Intent(getApplicationContext(), AdminKelasDetailMuridDetailActivity.class);
                intent.putExtra(AdminKelasDetailMuridDetailActivity.EXTRA_ID_MURID, dataModelArrayList.get(position).getId_murid());
                intent.putExtra(AdminKelasDetailMuridDetailActivity.EXTRA_ID_WALI_MURID, dataModelArrayList.get(position).getId_wali_murid());
                intent.putExtra(AdminKelasDetailMuridDetailActivity.EXTRA_NAMA, dataModelArrayList.get(position).getNama());
                intent.putExtra(AdminKelasDetailMuridDetailActivity.EXTRA_NAMA_WALI_MURID, dataModelArrayList.get(position).getNama_wali_murid());
                intent.putExtra(AdminKelasDetailMuridDetailActivity.EXTRA_ALAMAT, dataModelArrayList.get(position).getAlamat());
                intent.putExtra(AdminKelasDetailMuridDetailActivity.EXTRA_FOTO, dataModelArrayList.get(position).getFoto());
                startActivity(intent);

            }
        });
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
        adminKelasDetailKelasPresenter.onLoadSemuaData(id_kelas_p);
    }
}
