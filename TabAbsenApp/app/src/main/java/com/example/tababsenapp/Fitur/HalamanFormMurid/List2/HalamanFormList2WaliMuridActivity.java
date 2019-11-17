package com.example.tababsenapp.Fitur.HalamanFormMurid.List2;

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

import com.example.tababsenapp.Adapters.AdapterDaftarWaliMurid;
import com.example.tababsenapp.Fitur.HalamanFormMurid.List2.presenter.FormList2WaliMuridPresenter;
import com.example.tababsenapp.Fitur.HalamanFormMurid.List2.presenter.IFormList2WaliMuridPresenter;
import com.example.tababsenapp.Fitur.HalamanFormMurid.List2.view.IFormList2WaliMuridView;
import com.example.tababsenapp.Fitur.HalamanListMurid.HalamanListMuridActivity;
import com.example.tababsenapp.Model.waliMurid.WaliMurid;
import com.example.tababsenapp.R;

import java.util.ArrayList;

import es.dmoral.toasty.Toasty;

public class HalamanFormList2WaliMuridActivity extends AppCompatActivity implements IFormList2WaliMuridView {

    IFormList2WaliMuridPresenter formList2WaliMuridPresenter;

    private AdapterDaftarWaliMurid adapterDaftarWaliMurid;
    private RecyclerView recyclerView;

    Toolbar toolbar;

    String EXTRA_ID_MURID = "EXTRA_ID_MURID";
    String EXTRA_NAMA = "EXTRA_NAMA";
    String EXTRA_FOTO = "EXTRA_FOTO";

    private SwipeRefreshLayout swipeRefreshLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_halaman_form_list2_wali_murid);

        onInfoMessage("Pilih Wali Murid !");

        formList2WaliMuridPresenter = new FormList2WaliMuridPresenter(this, this);
        formList2WaliMuridPresenter.onLoadSemuaListWaliMurid();

        recyclerView = findViewById(R.id.recycle_list_wali_murid);

        toolbar = findViewById(R.id.toolbar);
        initActionBar();

        swipeRefreshLayout = findViewById(R.id.swipe_refresh_layout);

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                // Your code to make your refresh action
                formList2WaliMuridPresenter.onLoadSemuaListWaliMurid();

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
        adapterDaftarWaliMurid = new AdapterDaftarWaliMurid(this, dataModelArrayList);
        GridLayoutManager layoutManager = new GridLayoutManager(this, 1, GridLayoutManager.VERTICAL, false);
        recyclerView.setAdapter(adapterDaftarWaliMurid);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setNestedScrollingEnabled(true);
        adapterDaftarWaliMurid.notifyDataSetChanged();

        adapterDaftarWaliMurid.setOnItemClickListener(new AdapterDaftarWaliMurid.ClickListener() {
            @Override
            public void onClick(View view, int position) {
                showDialog(dataModelArrayList.get(position).getId_wali_murid(), dataModelArrayList.get(position).getNama(), dataModelArrayList.get(position).getAlamat());
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
    public void showDialog(String id_wali_murid, String nama_wali_murid, String alamat) {
        String id_murid = getIntent().getStringExtra(EXTRA_ID_MURID);
        String nama = getIntent().getStringExtra(EXTRA_NAMA);
        String foto = getIntent().getStringExtra(EXTRA_FOTO);

        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                this);
        alertDialogBuilder.setTitle("Ingin Mengupdate Data Murid ?");
        alertDialogBuilder
                .setMessage("Nama Murid : " + nama + "\n" + "Nama Wali Murid : " + nama_wali_murid + "\n" + "Alamat  : " + alamat)
                .setPositiveButton("Ya", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        try {
                            formList2WaliMuridPresenter.onUpdateData(id_murid, id_wali_murid, nama, foto);
                        } catch (Exception e) {
                            onErrorMessage("Terjadi Kesalahan Update " + e.toString());
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
    public void stepFinish() {
        Intent intent = new Intent(HalamanFormList2WaliMuridActivity.this, HalamanListMuridActivity.class);
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
        formList2WaliMuridPresenter.onLoadSemuaListWaliMurid();
    }
}
