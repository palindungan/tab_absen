package com.its.bigstars.Activities.Data.Kelas.Add;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.its.bigstars.Activities.Data.Kelas.Add.presenter.DataKelasAddPresenter;
import com.its.bigstars.Activities.Data.Kelas.Add.presenter.IDataKelasAddPresenter;
import com.its.bigstars.Activities.Data.Kelas.Add.view.IDataKelasAddView;
import com.its.bigstars.Adapters.AdapterDataMataPelajaranList;
import com.its.bigstars.Controllers.GlobalProcess;
import com.its.bigstars.Controllers.ToastMessage;
import com.its.bigstars.Models.MataPelajaran;
import com.its.bigstars.R;

import java.util.ArrayList;

public class DataKelasAddActivity extends AppCompatActivity implements View.OnClickListener, IDataKelasAddView {

    IDataKelasAddPresenter dataKelasAddPresenter;
    ToastMessage toastMessage;
    GlobalProcess globalProcess;

    AdapterDataMataPelajaranList adapterDataMataPelajaranList;

    Toolbar toolbar;
    RecyclerView recyclerView;
    EditText edtNama, edtNamaWaliMurid, edtAlamat;
    Button btnPilih, btnSubmit;

    private Bitmap bitmap;
    String data_photo, id_wali_murid, nama_wali_murid, alamat;

    public static Dialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_kelas_add);

        dataKelasAddPresenter = new DataKelasAddPresenter(this, this);
        toastMessage = new ToastMessage(this);
        globalProcess = new GlobalProcess();

        toolbar = findViewById(R.id.toolbar);
        edtNama = findViewById(R.id.edt_nama);
        edtNamaWaliMurid = findViewById(R.id.edt_nama_wali_murid);
        edtAlamat = findViewById(R.id.edt_alamat);
        btnPilih = findViewById(R.id.btn_pilih);
        btnSubmit = findViewById(R.id.btn_submit);

        initActionBar();

        btnPilih.setOnClickListener(this);
        btnSubmit.setOnClickListener(this);
    }

    private void initActionBar() {
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
    }

    private void showDialog() {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                this);
        alertDialogBuilder.setTitle("Ingin Menambah Data Baru ?");
        alertDialogBuilder
                .setMessage("Klik Ya untuk melakukan input !")
                .setPositiveButton("Ya", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {

                        String inputNama = edtNama.getText().toString().trim();
                        String inputNamaWaliMurid = edtNamaWaliMurid.getText().toString().trim();
                        String inputAlamat = edtAlamat.getText().toString().trim();
                        String inputFoto = data_photo;

                        boolean isEmpty = false;

                        if (TextUtils.isEmpty(inputNama)) {
                            isEmpty = true;
                            edtNama.setError("Isi Data Dengan Lengkap");
                        } else if (TextUtils.isEmpty(inputNamaWaliMurid)) {
                            isEmpty = true;
                            edtNamaWaliMurid.setError("Isi Data Dengan Lengkap");
                            toastMessage.onErrorMessage("Pilih Wali Murid");
                        } else if (TextUtils.isEmpty(inputAlamat)) {
                            isEmpty = true;
                            edtAlamat.setError("Isi Data Dengan Lengkap");
                        }

                        try {

                            if (!isEmpty) {
//                                dataKelasAddPresenter.onSubmit(
//                                        "" + id_wali_murid,
//                                        "" + inputNama,
//                                        "" + inputFoto);
                            }

                        } catch (Exception e) {
                            toastMessage.onErrorMessage("Terjadi Kesalahan Submit " + e.toString());
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

    private void showDialogPilih() {
        dialog = new Dialog(this);
        // dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(false);
        dialog.setContentView(R.layout.dialog_list);

        Button btnCancel = dialog.findViewById(R.id.btn_cancel);
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        dataKelasAddPresenter.onLoadDataList();
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btn_pilih) {
            showDialogPilih();
        } else if (v.getId() == R.id.btn_submit) {
            showDialog();
        }
    }

    @Override
    public void backPressed() {
        onBackPressed();
    }

    @Override
    public void onSetupListView(ArrayList<MataPelajaran> dataModelArrayList) {
        recyclerView = dialog.findViewById(R.id.recycler);
        adapterDataMataPelajaranList = new AdapterDataMataPelajaranList(this, dataModelArrayList);
        GridLayoutManager layoutManager = new GridLayoutManager(this, 1, GridLayoutManager.VERTICAL, false);

        recyclerView.setAdapter(adapterDataMataPelajaranList);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setNestedScrollingEnabled(true);

        adapterDataMataPelajaranList.setOnItemClickListener(new AdapterDataMataPelajaranList.ClickListener() {
            @Override
            public void onClick(View view, int position) {
//                id_wali_murid = dataModelArrayList.get(position).getId_wali_murid();
//                nama_wali_murid = dataModelArrayList.get(position).getNama();
//                alamat = dataModelArrayList.get(position).getAlamat();
//
//                edtNamaWaliMurid.setText(nama_wali_murid);
//                edtAlamat.setText(alamat);
                dialog.dismiss();
            }
        });

        dialog.show();
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
}
