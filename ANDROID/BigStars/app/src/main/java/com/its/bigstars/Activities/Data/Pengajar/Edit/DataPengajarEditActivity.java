package com.its.bigstars.Activities.Data.Pengajar.Edit;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.its.bigstars.Activities.Data.Pengajar.Edit.presenter.DataPengajarEditPresenter;
import com.its.bigstars.Activities.Data.Pengajar.Edit.presenter.IDataPengajarEditPresenter;
import com.its.bigstars.Activities.Data.Pengajar.Edit.view.IDataPengajarEditView;
import com.its.bigstars.Controllers.BaseUrl;
import com.its.bigstars.Controllers.GlobalProcess;
import com.its.bigstars.Controllers.ToastMessage;
import com.its.bigstars.R;
import com.squareup.picasso.MemoryPolicy;
import com.squareup.picasso.NetworkPolicy;
import com.squareup.picasso.Picasso;

import java.io.IOException;

public class DataPengajarEditActivity extends AppCompatActivity implements View.OnClickListener, IDataPengajarEditView {

    IDataPengajarEditPresenter dataPengajarEditPresenter;
    BaseUrl baseUrl;
    GlobalProcess globalProcess;
    ToastMessage toastMessage;

    Toolbar toolbar;
    EditText edtNama, edtUsername, edtPassword, edtKonfirmasiPassword, edtAlamat, edtNoHp;
    ImageView ivFoto;
    Button btnUpdate;

    public static final String EXTRA_ID_PENGAJAR = "EXTRA_ID_PENGAJAR";
    public static final String EXTRA_NAMA = "EXTRA_NAMA";
    public static final String EXTRA_USERNAME = "EXTRA_USERNAME";
    public static final String EXTRA_ALAMAT = "EXTRA_ALAMAT";
    public static final String EXTRA_NO_HP = "EXTRA_NO_HP";
    public static final String EXTRA_FOTO = "EXTRA_FOTO";
    String id_pengajar, nama, username, alamat, no_hp, foto;

    private Bitmap bitmap;
    String data_photo = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_pengajar_edit);

        dataPengajarEditPresenter = new DataPengajarEditPresenter(this, this);
        baseUrl = new BaseUrl();
        globalProcess = new GlobalProcess();
        toastMessage = new ToastMessage(this);

        toolbar = findViewById(R.id.toolbar);
        edtNama = findViewById(R.id.edt_nama);
        edtUsername = findViewById(R.id.edt_username);
        edtPassword = findViewById(R.id.edt_password);
        edtKonfirmasiPassword = findViewById(R.id.edt_konfirmasi_password);
        edtAlamat = findViewById(R.id.edt_alamat);
        edtNoHp = findViewById(R.id.edt_no_hp);
        ivFoto = findViewById(R.id.iv_foto);
        btnUpdate = findViewById(R.id.btn_update);

        id_pengajar = getIntent().getStringExtra(EXTRA_ID_PENGAJAR);
        nama = getIntent().getStringExtra(EXTRA_NAMA);
        username = getIntent().getStringExtra(EXTRA_USERNAME);
        alamat = getIntent().getStringExtra(EXTRA_ALAMAT);
        no_hp = getIntent().getStringExtra(EXTRA_NO_HP);
        foto = getIntent().getStringExtra(EXTRA_FOTO);

        initActionBar();
        inisiasiAwal(
                "" + id_pengajar,
                "" + nama,
                "" + username,
                "" + alamat,
                "" + no_hp,
                "" + foto);

        ivFoto.setOnClickListener(this);
        btnUpdate.setOnClickListener(this);
    }

    private void initActionBar() {
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
    }

    private void inisiasiAwal(String id_pengajar, String nama, String username, String alamat, String no_hp, String foto) {
        edtNama.setText(nama);
        edtUsername.setText(username);
        edtAlamat.setText(alamat);
        edtNoHp.setText(no_hp);
        String alamatFoto = baseUrl.getUrlUpload() + "image/pengajar/" + foto + ".jpg";
        Picasso.get().load(alamatFoto).placeholder(R.drawable.ic_default_account_circle_24dp).memoryPolicy(MemoryPolicy.NO_CACHE, MemoryPolicy.NO_STORE).networkPolicy(NetworkPolicy.NO_CACHE, NetworkPolicy.NO_STORE).into(ivFoto);
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
                        String inputUsername = edtUsername.getText().toString().trim();
                        String inputPassword = edtPassword.getText().toString().trim();
                        String inputKonfirmasi_password = edtKonfirmasiPassword.getText().toString().trim();
                        String inputAlamat = edtAlamat.getText().toString().trim();
                        String inputNo_hp = edtNoHp.getText().toString().trim();
                        String inputFoto = data_photo;

                        boolean isEmpty = false;
                        boolean isInvalidKonfirmasi = false;

                        if (TextUtils.isEmpty(inputNama)) {
                            isEmpty = true;
                            edtNama.setError("Isi Data Dengan Lengkap");
                        } else if (TextUtils.isEmpty(inputUsername)) {
                            isEmpty = true;
                            edtUsername.setError("Isi Data Dengan Lengkap");
                        } else if (TextUtils.isEmpty(inputAlamat)) {
                            isEmpty = true;
                            edtAlamat.setError("Isi Data Dengan Lengkap");
                        } else if (TextUtils.isEmpty(inputNo_hp)) {
                            isEmpty = true;
                            edtNoHp.setError("Isi Data Dengan Lengkap");
                        }

                        if (!inputPassword.equals(inputKonfirmasi_password)) {
                            isInvalidKonfirmasi = true;
                            edtKonfirmasiPassword.setError("Konfirmasi Password Salah");
                        }

                        try {

                            if (!isEmpty && !isInvalidKonfirmasi) {
                                dataPengajarEditPresenter.onUpdate(
                                        "" + id_pengajar,
                                        "" + inputNama,
                                        "" + inputUsername,
                                        "" + inputPassword,
                                        "" + inputAlamat,
                                        "" + inputNo_hp,
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
        } else if (v.getId() == R.id.btn_update) {
            showDialogUpdate();
        }
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
