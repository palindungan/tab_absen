package com.example.tababsenapp.Fitur.HalamanFormMurid.List;

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
import android.widget.Toast;

import com.example.tababsenapp.Adapters.AdapterDaftarWaliMuridOnFormMurid;
import com.example.tababsenapp.Fitur.HalamanFormMurid.List.presenter.FormListWaliMuridPresenter;
import com.example.tababsenapp.Fitur.HalamanFormMurid.List.presenter.IFormListWaliMuridPresenter;
import com.example.tababsenapp.Fitur.HalamanFormMurid.List.view.IFormListWaliMuridView;
import com.example.tababsenapp.Fitur.HalamanListMurid.HalamanListMuridActivity;
import com.example.tababsenapp.Model.waliMurid.WaliMurid;
import com.example.tababsenapp.R;

import java.util.ArrayList;

import es.dmoral.toasty.Toasty;

public class HalamanFormListWaliMuridActivity extends AppCompatActivity implements IFormListWaliMuridView {

    IFormListWaliMuridPresenter formListWaliMuridPresenter;

    private AdapterDaftarWaliMuridOnFormMurid adapterDaftarWaliMuridOnFormMurid;
    private RecyclerView recyclerView;

    Toolbar toolbar;

    String EXTRA_NAMA = "EXTRA_NAMA";
    String EXTRA_FOTO = "EXTRA_FOTO";

    private SwipeRefreshLayout swipeRefreshLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_halaman_form_list_wali_murid);

        onInfoMessage("Pilih Wali Murid !");

        formListWaliMuridPresenter = new FormListWaliMuridPresenter(this, this);
        formListWaliMuridPresenter.onLoadSemuaListWaliMurid();

        recyclerView = findViewById(R.id.recycle_list_wali_murid);

        toolbar = findViewById(R.id.toolbar);
        initActionBar();

        swipeRefreshLayout = findViewById(R.id.swipe_refresh_layout);

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                // Your code to make your refresh action
                formListWaliMuridPresenter.onLoadSemuaListWaliMurid();

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
    public void onSetupListView(ArrayList<WaliMurid> dataModelArrayList) {

        adapterDaftarWaliMuridOnFormMurid = new AdapterDaftarWaliMuridOnFormMurid(this, dataModelArrayList);
        GridLayoutManager layoutManager = new GridLayoutManager(this, 1, GridLayoutManager.VERTICAL, false);
        recyclerView.setAdapter(adapterDaftarWaliMuridOnFormMurid);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setNestedScrollingEnabled(true);
        adapterDaftarWaliMuridOnFormMurid.notifyDataSetChanged();

        adapterDaftarWaliMuridOnFormMurid.setOnItemClickListener(new AdapterDaftarWaliMuridOnFormMurid.ClickListener() {
            @Override
            public void onClick(View view, int position) {
                showDialog(dataModelArrayList.get(position).getId_wali_murid());
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
    public void onInfoMessage(String message) {
        Toasty.info(this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showDialog(String id_wali_murid) {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                this);
        alertDialogBuilder.setTitle("Ingin Menambah Data Murid Baru ?");
        alertDialogBuilder
                .setMessage("Klik Ya untuk melakukan input !")
                .setPositiveButton("Ya", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {

                        String nama = getIntent().getStringExtra(EXTRA_NAMA);
                        String foto = getIntent().getStringExtra(EXTRA_FOTO);

                        try {
                            formListWaliMuridPresenter.onSendData(id_wali_murid, nama, foto);
                        } catch (Exception e) {
                            onErrorMessage("Terjadi Kesalahan Submit " + e.toString());
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
    public void finish() {

        Intent intent = new Intent(HalamanFormListWaliMuridActivity.this, HalamanListMuridActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

        startActivity(intent);
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
        formListWaliMuridPresenter.onLoadSemuaListWaliMurid();
    }
}
