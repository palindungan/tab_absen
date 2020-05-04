package com.its.bigstars.Activities.Data.Murid.List;

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
import com.its.bigstars.Activities.Data.Murid.Add.DataMuridAddActivity;
import com.its.bigstars.Activities.Data.Murid.Edit.DataMuridEditActivity;
import com.its.bigstars.Activities.Data.Murid.List.presenter.DataMuridListPresenter;
import com.its.bigstars.Activities.Data.Murid.List.presenter.IDataMuridListPresenter;
import com.its.bigstars.Activities.Data.Murid.List.view.IDataMuridListView;
import com.its.bigstars.Adapters.AdapterDataMuridList;
import com.its.bigstars.Controllers.SessionManager;
import com.its.bigstars.Controllers.ToastMessage;
import com.its.bigstars.Models.Murid;
import com.its.bigstars.R;

import java.util.ArrayList;

public class DataMuridListActivity extends AppCompatActivity implements View.OnClickListener, IDataMuridListView {

    IDataMuridListPresenter dataMuridListPresenter;
    ToastMessage toastMessage;
    SessionManager sessionManager;

    private AdapterDataMuridList adapterDataMuridList;

    Toolbar toolbar;
    FloatingActionButton fab;
    private RecyclerView recyclerView;
    private SwipeRefreshLayout swipeRefreshLayout;

    String statusActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_murid_list);

        dataMuridListPresenter = new DataMuridListPresenter(this, this);
        toastMessage = new ToastMessage(this);
        sessionManager = new SessionManager(this);

        toolbar = findViewById(R.id.toolbar);
        fab = findViewById(R.id.fab);
        recyclerView = findViewById(R.id.recycle_view);
        swipeRefreshLayout = findViewById(R.id.swipe_refresh_layout);

        initActionBar();
        dataMuridListPresenter.onLoadDataList();
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                // Your code to make your refresh action
                dataMuridListPresenter.onLoadDataList();

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
            startActivity(new Intent(getApplicationContext(), DataMuridAddActivity.class));
        }
    }

    @Override
    public void onSetupListView(ArrayList<Murid> dataModelArrayList) {
        adapterDataMuridList = new AdapterDataMuridList(this, dataModelArrayList);
        GridLayoutManager layoutManager = new GridLayoutManager(this, 1, GridLayoutManager.VERTICAL, false);
        recyclerView.setAdapter(adapterDataMuridList);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setNestedScrollingEnabled(true);

        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if (dy > 0 && fab.getVisibility() == View.VISIBLE) {
                    fab.hide();
                } else if (dy < 0 && fab.getVisibility() != View.VISIBLE) {
                    fab.show();
                }
            }
        });

        adapterDataMuridList.setOnItemClickListener(new AdapterDataMuridList.ClickListener() {
            @Override
            public void onClick(View view, int position) {
                Intent intent = new Intent(getApplicationContext(), DataMuridEditActivity.class);
                // intent.putExtra(DataMuridEditActivity.EXTRA_ID_MURID, dataModelArrayList.get(position).getId_murid());
                startActivity(intent);
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
                            dataMuridListPresenter.onDelete(kode);
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
        dataMuridListPresenter.onLoadDataList();
    }
}
