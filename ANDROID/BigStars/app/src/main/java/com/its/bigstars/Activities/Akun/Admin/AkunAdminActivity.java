package com.its.bigstars.Activities.Akun.Admin;

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
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.its.bigstars.Activities.Akun.Admin.presenter.AkunAdminPresenter;
import com.its.bigstars.Activities.Akun.Admin.presenter.IAkunAdminPresenter;
import com.its.bigstars.Activities.Akun.Admin.view.IAkunAdminView;
import com.its.bigstars.Controllers.BaseUrl;
import com.its.bigstars.Controllers.SessionManager;
import com.its.bigstars.Controllers.ToastMessage;
import com.its.bigstars.R;
import com.squareup.picasso.MemoryPolicy;
import com.squareup.picasso.NetworkPolicy;
import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.util.HashMap;

public class AkunAdminActivity extends AppCompatActivity implements View.OnClickListener, IAkunAdminView {

    IAkunAdminPresenter akunAdminPresenter;
    SessionManager sessionManager;
    ToastMessage toastMessage;
    BaseUrl baseUrl;

    Toolbar toolbar;
    EditText edtNama, edtUsername, edtPassword, edtKonfirmasiPassword;
    ImageView ivFoto;
    Button btnUpdate;

    String id_admin, nama, username, foto;

    private Bitmap bitmap;
    String data_photo = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_akun_admin);

        akunAdminPresenter = new AkunAdminPresenter(this, this);
        sessionManager = new SessionManager(this);
        toastMessage = new ToastMessage(this);
        baseUrl = new BaseUrl();

        toolbar = findViewById(R.id.toolbar);
        edtNama = findViewById(R.id.edt_nama);
        edtUsername = findViewById(R.id.edt_username);
        edtPassword = findViewById(R.id.edt_password);
        edtKonfirmasiPassword = findViewById(R.id.edt_konfirmasi_password);
        ivFoto = findViewById(R.id.iv_foto);
        btnUpdate = findViewById(R.id.btn_update);

        HashMap<String, String> user = sessionManager.getDataUser();
        id_admin = user.get(sessionManager.ID_USER);
        nama = user.get(sessionManager.NAMA);
        username = user.get(sessionManager.USERNAME);
        foto = user.get(sessionManager.FOTO);

        initActionBar();

        if (!id_admin.isEmpty() && !nama.isEmpty() && !username.isEmpty() && !foto.isEmpty()) {
            setNilaiDefault(nama, username, foto);
        } else {
            sessionManager.logout();
        }

        ivFoto.setOnClickListener(this);
        btnUpdate.setOnClickListener(this);
    }

    private void initActionBar() {
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
    }

    private void setNilaiDefault(String nama, String username, String foto) {
        edtNama.setText(nama);
        edtUsername.setText(username);
        String alamat_foto = baseUrl.getUrlUpload() + "image/admin/" + foto + ".jpg";
        Picasso.get().load(alamat_foto).placeholder(R.drawable.ic_default_account_circle_24dp).memoryPolicy(MemoryPolicy.NO_CACHE, MemoryPolicy.NO_STORE).networkPolicy(NetworkPolicy.NO_CACHE, NetworkPolicy.NO_STORE).into(ivFoto);
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
                        String inputFoto = data_photo;

                        boolean isEmpty = false;
                        boolean isInvalidKonfirmasi = false;

                        if (TextUtils.isEmpty(inputNama)) {
                            isEmpty = true;
                            edtNama.setError("Isi Data Dengan Lengkap");
                        } else if (TextUtils.isEmpty(inputUsername)) {
                            isEmpty = true;
                            edtUsername.setError("Isi Data Dengan Lengkap");
                        }

                        if (!inputPassword.equals(inputKonfirmasi_password)) {
                            isInvalidKonfirmasi = true;
                            edtKonfirmasiPassword.setError("Konfirmasi Password Salah");
                        }

                        try {

                            if (!isEmpty && !isInvalidKonfirmasi) {
                                akunAdminPresenter.onUpdate(
                                        "" + id_admin,
                                        "" + inputNama,
                                        "" + inputUsername,
                                        "" + inputPassword,
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
        }
        if (v.getId() == R.id.btn_update) {
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

            data_photo = akunAdminPresenter.getStringImage(bitmap);
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