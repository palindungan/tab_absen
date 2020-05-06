package com.its.bigstars.Activities.Data.MataPelajaran.List;

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
import com.its.bigstars.Activities.Data.MataPelajaran.Add.DataMataPelajaranAddActivity;
import com.its.bigstars.Activities.Data.MataPelajaran.Edit.DataMataPelajaranEditActivity;
import com.its.bigstars.Activities.Data.MataPelajaran.List.presenter.DataMataPelajaranListPresenter;
import com.its.bigstars.Activities.Data.MataPelajaran.List.presenter.IDataMataPelajaranListPresenter;
import com.its.bigstars.Activities.Data.MataPelajaran.List.view.IDataMataPelajaranListView;
import com.its.bigstars.Adapters.AdapterDataMataPelajaranList;
import com.its.bigstars.Controllers.SessionManager;
import com.its.bigstars.Controllers.ToastMessage;
import com.its.bigstars.Models.MataPelajaran;
import com.its.bigstars.R;

import java.util.ArrayList;

public class DataMataPelajaranListActivity extends AppCompatActivity implements View.OnClickListener, IDataMataPelajaranListView {

    IDataMataPelajaranListPresenter dataMataPelajaranListPresenter;
    ToastMessage toastMessage;
    SessionManager sessionManager;

    private AdapterDataMataPelajaranList adapterDataMataPelajaranList;

    Toolbar toolbar;
    FloatingActionButton fab;
    private RecyclerView recyclerView;
    private SwipeRefreshLayout swipeRefreshLayout;

    String statusActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_mata_pelajaran_list);

        dataMataPelajaranListPresenter = new DataMataPelajaranListPresenter(this, this);
        toastMessage = new ToastMessage(this);
        sessionManager = new SessionManager(this);

        toolbar = findViewById(R.id.toolbar);
        fab = findViewById(R.id.fab);
        recyclerView = findViewById(R.id.recycle_view);
        swipeRefreshLayout = findViewById(R.id.swipe_refresh_layout);

        initActionBar();
        dataMataPelajaranListPresenter.onLoadDataList();
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                // Your code to make your refresh action
                dataMataPelajaranListPresenter.onLoadDataList();

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
        if (statusActivity.equals("home->view->editMataPelajaran")) {
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
            startActivity(new Intent(getApplicationContext(), DataMataPelajaranAddActivity.class));
        }
    }

    @Override
    public void onSetupListView(ArrayList<MataPelajaran> dataModelArrayList) {
        adapterDataMataPelajaranList = new AdapterDataMataPelajaranList(this, dataModelArrayList);
        GridLayoutManager layoutManager = new GridLayoutManager(this, 1, GridLayoutManager.VERTICAL, false);
        recyclerView.setAdapter(adapterDataMataPelajaranList);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setNestedScrollingEnabled(true);
        adapterDataMataPelajaranList.notifyDataSetChanged();

        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if (dy > 0 && fab.getVisibility() == View.VISIBLE) {
                    fab.hide();
                } else if (dy < 0 && fab.getVisibility() != View.VISIBLE) {
                    if (statusActivity.equals("home->view->editMataPelajaran")) {
                        fab.show();
                    }
                }
            }
        });

        adapterDataMataPelajaranList.setOnItemClickListener(new AdapterDataMataPelajaranList.ClickListener() {
            @Override
            public void onClick(View view, int position) {
                Intent intent;

                if (statusActivity.equals("home->view->editMataPelajaran")) {
                    intent = new Intent(getApplicationContext(), DataMataPelajaranEditActivity.class);
                    intent.putExtra(DataMataPelajaranEditActivity.EXTRA_ID_MATA_PELAJARAN, dataModelArrayList.get(position).getId_mata_pelajaran());
                    intent.putExtra(DataMataPelajaranEditActivity.EXTRA_NAMA, dataModelArrayList.get(position).getNama());
                    startActivity(intent);
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
                            dataMataPelajaranListPresenter.onDelete(kode);
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
        dataMataPelajaranListPresenter.onLoadDataList();
    }
}
