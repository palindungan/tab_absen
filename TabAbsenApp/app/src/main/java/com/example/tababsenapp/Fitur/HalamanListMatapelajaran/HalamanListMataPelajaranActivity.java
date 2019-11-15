package com.example.tababsenapp.Fitur.HalamanListMatapelajaran;

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

import com.example.tababsenapp.Adapters.AdapterDaftarMataPelajaran;
import com.example.tababsenapp.Fitur.HalamanFormMataPelajaran.Edit.HalamanFormEditMataPelajaranActivity;
import com.example.tababsenapp.Fitur.HalamanFormMataPelajaran.Tambah.HalamanFormTambahMataPelajaranActivity;
import com.example.tababsenapp.Fitur.HalamanListMatapelajaran.presenter.IListMataPelajaranPresenter;
import com.example.tababsenapp.Fitur.HalamanListMatapelajaran.presenter.ListMataPelajaranPresenter;
import com.example.tababsenapp.Fitur.HalamanListMatapelajaran.view.IListMataPelajaranView;
import com.example.tababsenapp.Model.mataPelajaran.MataPelajaran;
import com.example.tababsenapp.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

import es.dmoral.toasty.Toasty;

public class HalamanListMataPelajaranActivity extends AppCompatActivity implements IListMataPelajaranView {

    IListMataPelajaranPresenter listMataPelajaranPresenter;

    private AdapterDaftarMataPelajaran adapterDaftarMataPelajaran;
    private  RecyclerView recyclerView;

    Toolbar toolbar;

    String EXTRA_ID_MATA_PELAJARAN = "EXTRA_ID_MATA_PELAJARAN";

    private SwipeRefreshLayout swipeRefreshLayout;
    FloatingActionButton fab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_halaman_list_mata_pelajaran);

        listMataPelajaranPresenter = new ListMataPelajaranPresenter(this, this);
        listMataPelajaranPresenter.onLoadSemuaListMataPelajaran();

        recyclerView = findViewById(R.id.recycle_list_mata_pelajaran);

        toolbar = findViewById(R.id.toolbar);
        initActionBar();

        swipeRefreshLayout = findViewById(R.id.swipe_refresh_layout);
        fab = findViewById(R.id.fab);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HalamanListMataPelajaranActivity.this, HalamanFormTambahMataPelajaranActivity.class));
            }
        });

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                // Your code to make your refresh action
                listMataPelajaranPresenter.onLoadSemuaListMataPelajaran();

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
    public void onSetupListView(ArrayList<MataPelajaran> dataModelArrayList) {

        adapterDaftarMataPelajaran = new AdapterDaftarMataPelajaran(this,dataModelArrayList);
        GridLayoutManager layoutManager = new GridLayoutManager(this,1,GridLayoutManager.VERTICAL,false);
        recyclerView.setAdapter(adapterDaftarMataPelajaran);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setNestedScrollingEnabled(true);
        adapterDaftarMataPelajaran.notifyDataSetChanged();

        adapterDaftarMataPelajaran.setOnItemClickListener(new AdapterDaftarMataPelajaran.ClickListener() {
            @Override
            public void onClick(View view, int position) {
                Intent intent = new Intent(HalamanListMataPelajaranActivity.this, HalamanFormEditMataPelajaranActivity.class);
                intent.putExtra(EXTRA_ID_MATA_PELAJARAN,dataModelArrayList.get(position).getId_mata_pelajaran());
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
        listMataPelajaranPresenter.onLoadSemuaListMataPelajaran();
    }
}
