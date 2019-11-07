package com.example.tababsenapp.Fitur.HalamanListPengajar;

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

import com.example.tababsenapp.Adapters.AdapterDaftarPengajar;
import com.example.tababsenapp.Fitur.HalamanFormPengajar.Edit.HalamanFormEditPengajarActivity;
import com.example.tababsenapp.Fitur.HalamanFormPengajar.Tambah.HalamanFormTambahPengajarActivity;
import com.example.tababsenapp.Fitur.HalamanListPengajar.presenter.IListPengajarPresenter;
import com.example.tababsenapp.Fitur.HalamanListPengajar.presenter.ListPengajarPresenter;
import com.example.tababsenapp.Fitur.HalamanListPengajar.view.IListPengajarView;
import com.example.tababsenapp.Model.pengajar.Pengajar;
import com.example.tababsenapp.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;

import es.dmoral.toasty.Toasty;

public class HalamanListPengajarActivity extends AppCompatActivity implements IListPengajarView {

    IListPengajarPresenter listPengajarPresenter;

    private AdapterDaftarPengajar adapterDaftarPengajar;
    private RecyclerView recyclerView;

    Toolbar toolbar;

    String EXTRA_ID_PENGAJAR = "EXTRA_ID_PENGAJAR";

    private SwipeRefreshLayout swipeRefreshLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_halaman_list_pengajar);

        listPengajarPresenter = new ListPengajarPresenter(this, this);
        listPengajarPresenter.onLoadSemuaListPengajar();

        recyclerView = findViewById(R.id.recycle_list_pengajar);

        toolbar = findViewById(R.id.toolbar);
        initActionBar();

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(HalamanListPengajarActivity.this, HalamanFormTambahPengajarActivity.class));
            }
        });

        swipeRefreshLayout = findViewById(R.id.swipe_refresh_layout);
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                // Your code to make your refresh action
                listPengajarPresenter.onLoadSemuaListPengajar();

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
    public void initActionBar() {
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
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
                Intent intent = new Intent(HalamanListPengajarActivity.this, HalamanFormEditPengajarActivity.class);
                intent.putExtra(EXTRA_ID_PENGAJAR, dataModelArrayList.get(position).getId_pengajar());
                startActivity(intent);
            }
        });

    }

    @Override
    public void onSucceessMessage(String message) {
        Toasty.success(this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onErrorMessage(String message) {
        Toasty.error(this, message, Toast.LENGTH_SHORT).show();
    }

    // untuk icon cart
    @Override
    protected void onResume() {
        super.onResume();
        listPengajarPresenter.onLoadSemuaListPengajar();
    }
}
