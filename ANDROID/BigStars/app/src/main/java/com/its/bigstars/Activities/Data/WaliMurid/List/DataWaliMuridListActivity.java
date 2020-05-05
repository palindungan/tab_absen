package com.its.bigstars.Activities.Data.WaliMurid.List;

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
import com.its.bigstars.Activities.Data.WaliMurid.Add.DataWaliMuridAddActivity;
import com.its.bigstars.Activities.Data.WaliMurid.List.presenter.DataWaliMuridListPresenter;
import com.its.bigstars.Activities.Data.WaliMurid.List.presenter.IDataWaliMuridListPresenter;
import com.its.bigstars.Activities.Data.WaliMurid.List.view.IDataWaliMuridListView;
import com.its.bigstars.Adapters.AdapterDataWaliMuridList;
import com.its.bigstars.Controllers.SessionManager;
import com.its.bigstars.Controllers.ToastMessage;
import com.its.bigstars.Models.WaliMurid;
import com.its.bigstars.R;

import java.util.ArrayList;

public class DataWaliMuridListActivity extends AppCompatActivity implements View.OnClickListener, IDataWaliMuridListView {

    IDataWaliMuridListPresenter dataWaliMuridListPresenter;
    ToastMessage toastMessage;
    SessionManager sessionManager;

    private AdapterDataWaliMuridList adapterDataWaliMuridList;

    Toolbar toolbar;
    FloatingActionButton fab;
    private RecyclerView recyclerView;
    private SwipeRefreshLayout swipeRefreshLayout;

    String statusActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_wali_murid_list);

        dataWaliMuridListPresenter = new DataWaliMuridListPresenter(this, this);
        toastMessage = new ToastMessage(this);
        sessionManager = new SessionManager(this);

        toolbar = findViewById(R.id.toolbar);
        fab = findViewById(R.id.fab);
        recyclerView = findViewById(R.id.recycle_view);
        swipeRefreshLayout = findViewById(R.id.swipe_refresh_layout);

        initActionBar();
        dataWaliMuridListPresenter.onLoadDataList();
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                // Your code to make your refresh action
                dataWaliMuridListPresenter.onLoadDataList();

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
        if (statusActivity.equals("home->view->editWaliMurid")) {
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
        if (v.getId() == R.id.fab) {
            startActivity(new Intent(getApplicationContext(), DataWaliMuridAddActivity.class));
        }
    }

    @Override
    public void onSetupListView(ArrayList<WaliMurid> dataModelArrayList) {
        adapterDataWaliMuridList = new AdapterDataWaliMuridList(this, dataModelArrayList);
        GridLayoutManager layoutManager = new GridLayoutManager(this, 1, GridLayoutManager.VERTICAL, false);
        recyclerView.setAdapter(adapterDataWaliMuridList);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setNestedScrollingEnabled(true);
        adapterDataWaliMuridList.notifyDataSetChanged();

        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if (dy > 0 && fab.getVisibility() == View.VISIBLE) {
                    fab.hide();
                } else if (dy < 0 && fab.getVisibility() != View.VISIBLE) {
                    if (statusActivity.equals("home->view->editWaliMurid")) {
                        fab.show();
                    }
                }
            }
        });

        adapterDataWaliMuridList.setOnItemClickListener(new AdapterDataWaliMuridList.ClickListener() {
            @Override
            public void onClick(View view, int position) {
                Intent intent;

                if (statusActivity.equals("home->view->editWaliMurid")) {
//                    intent = new Intent(getApplicationContext(), DataPengajarEditActivity.class);
//                    intent.putExtra(DataPengajarEditActivity.EXTRA_ID_PENGAJAR, dataModelArrayList.get(position).getId_pengajar());
//                    intent.putExtra(DataPengajarEditActivity.EXTRA_NAMA, dataModelArrayList.get(position).getNama());
//                    intent.putExtra(DataPengajarEditActivity.EXTRA_USERNAME, dataModelArrayList.get(position).getUsername());
//                    intent.putExtra(DataPengajarEditActivity.EXTRA_ALAMAT, dataModelArrayList.get(position).getAlamat());
//                    intent.putExtra(DataPengajarEditActivity.EXTRA_NO_HP, dataModelArrayList.get(position).getNo_hp());
//                    intent.putExtra(DataPengajarEditActivity.EXTRA_FOTO, dataModelArrayList.get(position).getFoto());
//                    startActivity(intent);
                } else if (statusActivity.equals("xx->view->yy")) {

                }
            }
        });
    }

    @Override
    public void showDialogDelete(String kode, String nama) {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                this);
        alertDialogBuilder.setTitle("Yakin Ingin Menghapus Akun " + nama + " ?");
        alertDialogBuilder
                .setMessage("Klik Ya untuk Menghapus !")
                .setPositiveButton("Ya", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {

                        try {
                            dataWaliMuridListPresenter.onDelete(kode);
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
        dataWaliMuridListPresenter.onLoadDataList();
    }
}
