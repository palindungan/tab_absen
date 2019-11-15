package com.example.tababsenapp.Fitur.HalamanListMurid;

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

import com.example.tababsenapp.Adapters.AdapterDaftarMurid;
import com.example.tababsenapp.Fitur.HalamanListMurid.presenter.IListMuridPresenter;
import com.example.tababsenapp.Fitur.HalamanListMurid.presenter.ListMuridPresenter;
import com.example.tababsenapp.Fitur.HalamanListMurid.view.IListMuridView;
import com.example.tababsenapp.Model.murid.Murid;
import com.example.tababsenapp.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

import es.dmoral.toasty.Toasty;

public class HalamanListMuridActivity extends AppCompatActivity implements IListMuridView {

    IListMuridPresenter listMuridPresenter;

    private AdapterDaftarMurid adapterDaftarMurid;
    private RecyclerView recyclerView;

    Toolbar toolbar;

    String EXTRA_ID_MURID = "EXTRA_ID_MURID";

    private SwipeRefreshLayout swipeRefreshLayout;
    FloatingActionButton fab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_halaman_list_murid);

        listMuridPresenter = new ListMuridPresenter(this, this);
        listMuridPresenter.onLoadSemuaListMurid();

        recyclerView = findViewById(R.id.recycle_list_murid);

        toolbar = findViewById(R.id.toolbar);
        initActionBar();

        fab = findViewById(R.id.fab);
        swipeRefreshLayout = findViewById(R.id.swipe_refresh_layout);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                startActivity(new Intent(HalamanListMuridActivity.this,HalamanFormEd));
            }
        });

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                // Your code to make your refresh action
                listMuridPresenter.onLoadSemuaListMurid();

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
    public void onSetupListView(ArrayList<Murid> dataModelArrayList) {

        adapterDaftarMurid = new AdapterDaftarMurid(this,dataModelArrayList);
        GridLayoutManager layoutManager = new GridLayoutManager(this,1,GridLayoutManager.VERTICAL,false);
        recyclerView.setAdapter(adapterDaftarMurid);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setNestedScrollingEnabled(true);
        adapterDaftarMurid.setOnItemClickListener(new AdapterDaftarMurid.ClickListener() {
            @Override
            public void onClick(View view, int position) {

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
        listMuridPresenter.onLoadSemuaListMurid();
    }

}
