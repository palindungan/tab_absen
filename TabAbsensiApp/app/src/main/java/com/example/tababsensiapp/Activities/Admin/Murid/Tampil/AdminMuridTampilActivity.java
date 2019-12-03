package com.example.tababsensiapp.Activities.Admin.Murid.Tampil;

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

import com.example.tababsensiapp.Activities.Admin.Murid.Edit.Step1.AdminMuridEditStep1Activity;
import com.example.tababsensiapp.Activities.Admin.Murid.Tambah.Step1.AdminMuridTambahStep1Activity;
import com.example.tababsensiapp.Activities.Admin.Murid.Tampil.presenter.AdminMuridTampilPresenter;
import com.example.tababsensiapp.Activities.Admin.Murid.Tampil.presenter.IAdminMuridTampilPresenter;
import com.example.tababsensiapp.Activities.Admin.Murid.Tampil.view.IAdminMuridTampilView;
import com.example.tababsensiapp.Adapters.AdapterDaftarMurid;
import com.example.tababsensiapp.Models.Murid;
import com.example.tababsensiapp.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

import es.dmoral.toasty.Toasty;

public class AdminMuridTampilActivity extends AppCompatActivity implements View.OnClickListener , IAdminMuridTampilView {

    IAdminMuridTampilPresenter adminMuridTampilPresenter;

    private AdapterDaftarMurid adapterDaftarMurid;
    private RecyclerView recyclerView;

    Toolbar toolbar;

    private SwipeRefreshLayout swipeRefreshLayout;
    FloatingActionButton fab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_murid_tampil);

        adminMuridTampilPresenter = new AdminMuridTampilPresenter(this, this);
        adminMuridTampilPresenter.onLoadSemuaData();

        recyclerView = findViewById(R.id.recycle_view);

        toolbar = findViewById(R.id.toolbar);
        initActionBar();

        fab = findViewById(R.id.fab);
        swipeRefreshLayout = findViewById(R.id.swipe_refresh_layout);

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                // Your code to make your refresh action
                adminMuridTampilPresenter.onLoadSemuaData();

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
        if (v.getId()==R.id.fab){
             startActivity(new Intent(getApplicationContext(), AdminMuridTambahStep1Activity.class));
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
    public void onSetupListView(ArrayList<Murid> dataModelArrayList) {
        adapterDaftarMurid = new AdapterDaftarMurid(this,dataModelArrayList);
        GridLayoutManager layoutManager = new GridLayoutManager(this,1,GridLayoutManager.VERTICAL,false);
        recyclerView.setAdapter(adapterDaftarMurid);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setNestedScrollingEnabled(true);
        adapterDaftarMurid.setOnItemClickListener(new AdapterDaftarMurid.ClickListener() {
            @Override
            public void onClick(View view, int position) {
                Intent intent = new Intent(getApplicationContext(), AdminMuridEditStep1Activity.class);
                intent.putExtra(AdminMuridEditStep1Activity.EXTRA_ID_MURID,dataModelArrayList.get(position).getId_murid());
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
        adminMuridTampilPresenter.onLoadSemuaData();
    }
}
