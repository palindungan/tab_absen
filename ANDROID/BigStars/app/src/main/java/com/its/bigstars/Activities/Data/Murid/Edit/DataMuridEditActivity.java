package com.its.bigstars.Activities.Data.Murid.Edit;

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

import com.its.bigstars.Activities.Data.Murid.Edit.presenter.DataMuridEditPresenter;
import com.its.bigstars.Activities.Data.Murid.Edit.presenter.IDataMuridEditPresenter;
import com.its.bigstars.Activities.Data.Murid.Edit.view.IDataMuridEditView;
import com.its.bigstars.Adapters.AdapterDialogListWaliMurid;
import com.its.bigstars.Controllers.BaseUrl;
import com.its.bigstars.Controllers.GlobalProcess;
import com.its.bigstars.Controllers.ToastMessage;
import com.its.bigstars.Models.WaliMurid;
import com.its.bigstars.R;
import com.squareup.picasso.MemoryPolicy;
import com.squareup.picasso.NetworkPolicy;
import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.util.ArrayList;

public class DataMuridEditActivity extends AppCompatActivity implements View.OnClickListener, IDataMuridEditView {

    IDataMuridEditPresenter dataMuridEditPresenter;
    BaseUrl baseUrl;
    GlobalProcess globalProcess;
    ToastMessage toastMessage;

    Toolbar toolbar;
    EditText edtNama, edtNamaWaliMurid, edtAlamat;
    Button btnEdit, btnUpdate;
    ImageView ivFoto;

    public static final String EXTRA_ID_MURID = "EXTRA_ID_MURID";
    public static final String EXTRA_NAMA = "EXTRA_NAMA";
    public static final String EXTRA_ID_WALI_MURID = "EXTRA_ID_WALI_MURID";
    public static final String EXTRA_NAMA_WALI_MURID = "EXTRA_NAMA_WALI_MURID";
    public static final String EXTRA_ALAMAT = "EXTRA_ALAMAT";
    public static final String EXTRA_FOTO = "EXTRA_FOTO";
    String id_murid, nama, id_wali_murid, nama_wali_murid, alamat, foto;

    private Bitmap bitmap;
    String data_photo = "";

    public static Dialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_murid_edit);

        dataMuridEditPresenter = new DataMuridEditPresenter(this, this);
        baseUrl = new BaseUrl();
        globalProcess = new GlobalProcess();
        toastMessage = new ToastMessage(this);

        toolbar = findViewById(R.id.toolbar);
        edtNama = findViewById(R.id.edt_nama);
        edtNamaWaliMurid = findViewById(R.id.edt_nama_wali_murid);
        edtAlamat = findViewById(R.id.edt_alamat);
        btnEdit = findViewById(R.id.btn_edit);
        btnUpdate = findViewById(R.id.btn_update);
        ivFoto = findViewById(R.id.iv_foto);

        id_murid = getIntent().getStringExtra(EXTRA_ID_MURID);
        nama = getIntent().getStringExtra(EXTRA_NAMA);
        id_wali_murid = getIntent().getStringExtra(EXTRA_ID_WALI_MURID);
        nama_wali_murid = getIntent().getStringExtra(EXTRA_NAMA_WALI_MURID);
        alamat = getIntent().getStringExtra(EXTRA_ALAMAT);
        foto = getIntent().getStringExtra(EXTRA_FOTO);

        initActionBar();
        inisiasiAwal(
                "" + nama,
                "" + nama_wali_murid,
                "" + alamat,
                "" + foto);

        ivFoto.setOnClickListener(this);
        btnEdit.setOnClickListener(this);
        btnUpdate.setOnClickListener(this);
    }

    private void initActionBar() {
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
    }

    private void inisiasiAwal(String nama, String nama_wali_murid, String alamat, String foto) {
        edtNama.setText(nama);
        edtNamaWaliMurid.setText(nama_wali_murid);
        edtAlamat.setText(alamat);
        String alamatFoto = baseUrl.getUrlUpload() + "image/murid/" + foto + ".jpg";
        Picasso.get().load(alamatFoto).placeholder(R.drawable.ic_default_account_circle_24dp).memoryPolicy(MemoryPolicy.NO_CACHE, MemoryPolicy.NO_STORE).networkPolicy(NetworkPolicy.NO_CACHE, NetworkPolicy.NO_STORE).into(ivFoto);
    }

    private void showDialogEdit() {
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
        dataMuridEditPresenter.onLoadDataListWaliMurid();
    }

    private void showDialogUpdate() {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                this);
        alertDialogBuilder.setTitle("Ingin Mengupdate Data ?");
        alertDialogBuilder
                .setMessage("Klik Ya untuk melakukan update !")
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
                        } else if (TextUtils.isEmpty(inputAlamat)) {
                            isEmpty = true;
                            edtAlamat.setError("Isi Data Dengan Lengkap");
                        }

                        try {

                            if (!isEmpty) {
                                dataMuridEditPresenter.onUpdate(
                                        "" + id_murid,
                                        "" + id_wali_murid,
                                        "" + nama,
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

    @Override
    public void onClick(View v) {

        if (v.getId() == R.id.iv_foto) {
            Intent intent = new Intent();
            intent.setType("image/*");
            intent.setAction(Intent.ACTION_GET_CONTENT);
            startActivityForResult(Intent.createChooser(intent, "Pilih Gambar"), 1);
        } else if (v.getId() == R.id.btn_edit) {
            showDialogEdit();
        } else if (v.getId() == R.id.btn_update) {
            showDialogUpdate();
        }

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
    public void backPressed() {
        onBackPressed();
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

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
