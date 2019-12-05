package com.example.tababsensiapp.Activities.Admin.Kelas.Tampil.Pengajar;

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

import com.example.tababsensiapp.Activities.Admin.Kelas.Tampil.Kelas.AdminKelasTampilKelasActivity;
import com.example.tababsensiapp.Activities.Admin.Kelas.Tampil.Pengajar.presenter.AdminKelasTampilPengajarPresenter;
import com.example.tababsensiapp.Activities.Admin.Kelas.Tampil.Pengajar.presenter.IAdminKelasTampilPengajarPresenter;
import com.example.tababsensiapp.Activities.Admin.Kelas.Tampil.Pengajar.view.IAdminKelasTampilPengajarView;
import com.example.tababsensiapp.Adapters.AdapterDaftarKelasPengajar;
import com.example.tababsensiapp.Models.Pengajar;
import com.example.tababsensiapp.R;

import java.util.ArrayList;

import es.dmoral.toasty.Toasty;

public class AdminKelasTampilPengajarActivity extends AppCompatActivity implements View.OnClickListener, IAdminKelasTampilPengajarView {

    IAdminKelasTampilPengajarPresenter adminKelasTampilPengajarPresenter;

    private AdapterDaftarKelasPengajar adapterDaftarKelasPengajar;
    private RecyclerView recyclerView;

    Toolbar toolbar;

    private SwipeRefreshLayout swipeRefreshLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_kelas_tampil_pengajar);

        adminKelasTampilPengajarPresenter = new AdminKelasTampilPengajarPresenter(this, this);
        adminKelasTampilPengajarPresenter.onLoadSemuaData();

        recyclerView = findViewById(R.id.recycle_view);

        toolbar = findViewById(R.id.toolbar);
        initActionBar();

        swipeRefreshLayout = findViewById(R.id.swipe_refresh_layout);

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                // Your code to make your refresh action
                adminKelasTampilPengajarPresenter.onLoadSemuaData();

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
    public void onSetupListView(ArrayList<Pengajar> dataModelArrayList) {
        adapterDaftarKelasPengajar = new AdapterDaftarKelasPengajar(this, dataModelArrayList);
        GridLayoutManager layoutManager = new GridLayoutManager(this, 2, GridLayoutManager.VERTICAL, false);
        recyclerView.setAdapter(adapterDaftarKelasPengajar);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setNestedScrollingEnabled(true);
        adapterDaftarKelasPengajar.notifyDataSetChanged();

       adapterDaftarKelasPengajar.setOnItemClickListener(new AdapterDaftarKelasPengajar.ClickListener() {
           @Override
           public void onClick(View view, int position) {
               Intent intent = new Intent(getApplicationContext(), AdminKelasTampilKelasActivity.class);
               intent.putExtra(AdminKelasTampilKelasActivity.EXTRA_ID_PENGAJAR, dataModelArrayList.get(position).getId_pengajar());
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
        adminKelasTampilPengajarPresenter.onLoadSemuaData();
    }
}
