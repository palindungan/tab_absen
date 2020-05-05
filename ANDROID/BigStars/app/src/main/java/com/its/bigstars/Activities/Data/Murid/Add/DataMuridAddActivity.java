package com.its.bigstars.Activities.Data.Murid.Add;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.its.bigstars.Activities.Data.Murid.Add.presenter.DataMuridAddPresenter;
import com.its.bigstars.Activities.Data.Murid.Add.presenter.IDataMuridAddPresenter;
import com.its.bigstars.Activities.Data.Murid.Add.view.IDataMuridAddView;
import com.its.bigstars.Adapters.AdapterDialogListWaliMurid;
import com.its.bigstars.Controllers.GlobalProcess;
import com.its.bigstars.Controllers.ToastMessage;
import com.its.bigstars.Models.WaliMurid;
import com.its.bigstars.R;

import java.io.IOException;
import java.util.ArrayList;

public class DataMuridAddActivity extends AppCompatActivity implements View.OnClickListener, IDataMuridAddView {

    IDataMuridAddPresenter dataMuridAddPresenter;
    ToastMessage toastMessage;
    GlobalProcess globalProcess;

    Toolbar toolbar;
    EditText edtNama, edtNamaWaliMurid, edtAlamat;
    Button btnPilih, btnSubmit;
    ImageView ivFoto;

    private Bitmap bitmap;
    String data_photo, id_wali_murid, nama_wali_murid, alamat;

    public static Dialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_murid_add);

        dataMuridAddPresenter = new DataMuridAddPresenter(this, this);
        toastMessage = new ToastMessage(this);
        globalProcess = new GlobalProcess();

        toolbar = findViewById(R.id.toolbar);
        edtNama = findViewById(R.id.edt_nama);
        edtNamaWaliMurid = findViewById(R.id.edt_nama_wali_murid);
        edtAlamat = findViewById(R.id.edt_alamat);
        ivFoto = findViewById(R.id.iv_foto);
        btnPilih = findViewById(R.id.btn_pilih);
        btnSubmit = findViewById(R.id.btn_submit);

        initActionBar();

        ivFoto.setOnClickListener(this);
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
                                dataMuridAddPresenter.onSubmit(
                                        "" + id_wali_murid,
                                        "" + inputNama,
                                        "" + inputFoto);
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
        dialog.setContentView(R.layout.dialog_list_wali_murid);

        Button btnCancel = dialog.findViewById(R.id.btn_cancel);
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        dataMuridAddPresenter.onLoadDataListWaliMurid();
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.iv_foto) {
            Intent intent = new Intent();
            intent.setType("image/*");
            intent.setAction(Intent.ACTION_GET_CONTENT);
            startActivityForResult(Intent.createChooser(intent, "Pilih Gambar"), 1);
        } else if (v.getId() == R.id.btn_pilih) {
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
    public void onSetupListView(ArrayList<WaliMurid> dataModelArrayList) {
        RecyclerView recyclerView = dialog.findViewById(R.id.recycler);
        AdapterDialogListWaliMurid adapterDialogListWaliMurid = new AdapterDialogListWaliMurid(this, dataModelArrayList);
        GridLayoutManager layoutManager = new GridLayoutManager(this, 1, GridLayoutManager.VERTICAL, false);

        recyclerView.setAdapter(adapterDialogListWaliMurid);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setNestedScrollingEnabled(true);

        adapterDialogListWaliMurid.setOnItemClickListener(new AdapterDialogListWaliMurid.ClickListener() {
            @Override
            public void onClick(View view, int position) {
                id_wali_murid = dataModelArrayList.get(position).getId_wali_murid();
                nama_wali_murid = dataModelArrayList.get(position).getNama();
                alamat = dataModelArrayList.get(position).getAlamat();

                edtNamaWaliMurid.setText(nama_wali_murid);
                edtAlamat.setText(alamat);
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

    // proses pengolahan gambar
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 1 && resultCode == RESULT_OK && data != null && data.getData() != null) {
            Uri filePath = data.getData();
            try {

                bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), filePath);
                ivFoto.setImageBitmap(bitmap);

            } catch (IOException e) {
                e.printStackTrace();
                toastMessage.onErrorMessage("Gambar Error " + e.toString());
            }

            globalProcess.setBitmap(bitmap);
            data_photo = globalProcess.getStringImage();
        }
    }
}
