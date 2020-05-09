package com.its.bigstars.Activities.Data.Kelas.List;

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

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.its.bigstars.Activities.Data.Kelas.Add.DataKelasAddActivity;
import com.its.bigstars.Activities.Data.Kelas.Edit.DataKelasEditActivity;
import com.its.bigstars.Activities.Data.Kelas.List.presenter.DataKelasListPresenter;
import com.its.bigstars.Activities.Data.Kelas.List.presenter.IDataKelasListPresenter;
import com.its.bigstars.Activities.Data.Kelas.List.view.IDataKelasListView;
import com.its.bigstars.Adapters.AdapterDataKelasList;
import com.its.bigstars.Controllers.SessionManager;
import com.its.bigstars.Controllers.ToastMessage;
import com.its.bigstars.Models.Kelas;
import com.its.bigstars.R;

import java.util.ArrayList;

public class DataKelasListActivity extends AppCompatActivity implements View.OnClickListener, IDataKelasListView {

    IDataKelasListPresenter dataKelasListPresenter;
    ToastMessage toastMessage;
    SessionManager sessionManager;

    private AdapterDataKelasList adapterDataKelasList;

    Toolbar toolbar;
    FloatingActionButton fab;
    private RecyclerView recyclerView;
    private SwipeRefreshLayout swipeRefreshLayout;

    String statusActivity;

    public static final String EXTRA_ID_PENGAJAR = "EXTRA_ID_PENGAJAR";
    String id_pengajar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_kelas_list);

        dataKelasListPresenter = new DataKelasListPresenter(this, this);
        toastMessage = new ToastMessage(this);
        sessionManager = new SessionManager(this);

        toolbar = findViewById(R.id.toolbar);
        fab = findViewById(R.id.fab);
        recyclerView = findViewById(R.id.recycle_view);
        swipeRefreshLayout = findViewById(R.id.swipe_refresh_layout);

        id_pengajar = getIntent().getStringExtra(EXTRA_ID_PENGAJAR);

        initActionBar();
        dataKelasListPresenter.onLoadDataList(id_pengajar);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                // Your code to make your refresh action
                dataKelasListPresenter.onLoadDataList(id_pengajar);

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

        statusActivity = sessionManager.getStatusActivity();
        if (statusActivity.equals("listPengajar->view->editKelasPertemuan")) {
            fab.show();
        }

        fab.setOnClickListener(this);
    }

    private void initActionBar() {
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
    }

    @Override
    public void onClick(View v) {
        Intent intent;
        if (v.getId() == R.id.fab) {
            intent = new Intent(getApplicationContext(), DataKelasAddActivity.class);
            intent.putExtra(DataKelasAddActivity.EXTRA_ID_PENGAJAR, id_pengajar);
            startActivity(intent);
        }
    }

    @Override
    public void onSetupListView(ArrayList<Kelas> dataModelArrayList) {
        adapterDataKelasList = new AdapterDataKelasList(this, dataModelArrayList);
        GridLayoutManager layoutManager = new GridLayoutManager(this, 1, GridLayoutManager.VERTICAL, false);
        recyclerView.setAdapter(adapterDataKelasList);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setNestedScrollingEnabled(true);
        adapterDataKelasList.notifyDataSetChanged();

        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if (dy > 0 && fab.getVisibility() == View.VISIBLE) {
                    fab.hide();
                } else if (dy < 0 && fab.getVisibility() != View.VISIBLE) {
                    if (statusActivity.equals("listPengajar->view->editKelasPertemuan")) {
                        fab.show();
                    }
                }
            }
        });

        adapterDataKelasList.setOnItemClickListener(new AdapterDataKelasList.ClickListener() {
            @Override
            public void onClick(View view, int position) {
                Intent intent;
                if (statusActivity.equals("listPengajar->view->editKelasPertemuan")) {
                    intent = new Intent(getApplicationContext(), DataKelasEditActivity.class);
                    intent.putExtra(DataKelasEditActivity.EXTRA_ID_KELAS_P, dataModelArrayList.get(position).getId_kelas_p());
                    intent.putExtra(DataKelasEditActivity.EXTRA_ID_PENGAJAR, dataModelArrayList.get(position).getId_pengajar());
                    intent.putExtra(DataKelasEditActivity.EXTRA_ID_MATA_PELAJARAN, dataModelArrayList.get(position).getId_mata_pelajaran());
                    intent.putExtra(DataKelasEditActivity.EXTRA_NAMA_PELAJARAN, dataModelArrayList.get(position).getNama_pelajaran());
                    intent.putExtra(DataKelasEditActivity.EXTRA_HARI, dataModelArrayList.get(position).getHari());
                    intent.putExtra(DataKelasEditActivity.EXTRA_JAM_MULAI, dataModelArrayList.get(position).getJam_mulai());
                    intent.putExtra(DataKelasEditActivity.EXTRA_JAM_BERAKHIR, dataModelArrayList.get(position).getJam_berakhir());
                    intent.putExtra(DataKelasEditActivity.EXTRA_HARGA_FEE, dataModelArrayList.get(position).getHarga_fee());
                    intent.putExtra(DataKelasEditActivity.EXTRA_HARGA_SPP, dataModelArrayList.get(position).getHarga_spp());
                    startActivity(intent);
                }
            }
        });
    }

    @Override
    public void showDialogDelete(String kode, String nama) {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                this);
        alertDialogBuilder.setTitle("Yakin Ingin Menghapus Data " + nama + " ?");
        alertDialogBuilder
                .setMessage("Klik Ya untuk Menghapus !")
                .setPositiveButton("Ya", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {

                        try {
                            dataKelasListPresenter.onDelete(kode);
                        } catch (Exception e) {
                            toastMessage.onErrorMessage("Terjadi Kesalahan Hapus " + e.toString());
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
    public void onRefreshDataList() {
        dataKelasListPresenter.onLoadDataList(id_pengajar);
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
        dataKelasListPresenter.onLoadDataList(id_pengajar);
    }
}
