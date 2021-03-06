package com.example.tababsenapp.Fitur.HalamanFormPengajar.Edit;

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
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.tababsenapp.Fitur.HalamanFormPengajar.Edit.presenter.FormEditPengajarPresenter;
import com.example.tababsenapp.Fitur.HalamanFormPengajar.Edit.presenter.IFormEditPengajarPresenter;
import com.example.tababsenapp.Fitur.HalamanFormPengajar.Edit.view.IFormEditPengajarView;
import com.example.tababsenapp.R;
import com.squareup.picasso.MemoryPolicy;
import com.squareup.picasso.NetworkPolicy;
import com.squareup.picasso.Picasso;

import java.io.IOException;

import es.dmoral.toasty.Toasty;

public class HalamanFormEditPengajarActivity extends AppCompatActivity implements IFormEditPengajarView {

    IFormEditPengajarPresenter formEditPengajarPresenter;

    Toolbar toolbar;

    EditText edtNama, edtUsername, edtPassword, edtKonfirmasiPassword, edtAlamat, edtNoHp;
    ImageView ivFoto;
    Button btnUpdate;

    String EXTRA_ID_PENGAJAR = "EXTRA_ID_PENGAJAR";
    String id_pengajar = "";

    private Bitmap bitmap;
    String data_photo = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_halaman_form_edit_pengajar);

        toolbar = findViewById(R.id.toolbar);
        initActionBar();

        edtNama = findViewById(R.id.edt_nama);
        edtUsername = findViewById(R.id.edt_username);
        edtPassword = findViewById(R.id.edt_password);
        edtKonfirmasiPassword = findViewById(R.id.edt_konfirmasi_password);
        edtAlamat = findViewById(R.id.edt_alamat);
        edtNoHp = findViewById(R.id.edt_no_hp);
        ivFoto = findViewById(R.id.iv_foto);
        btnUpdate = findViewById(R.id.btn_update);

        formEditPengajarPresenter = new FormEditPengajarPresenter(this, this);
        id_pengajar = getIntent().getStringExtra(EXTRA_ID_PENGAJAR);
        formEditPengajarPresenter.inisiasiAwal(id_pengajar);

        ivFoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent, "Pilih Gambar"), 1);
            }
        });

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialogUpdate();
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
    public void setNilaiDefault(String nama, String username, String alamat, String no_hp, String foto) {
        edtNama.setText(nama);
        edtUsername.setText(username);
        edtAlamat.setText(alamat);
        edtNoHp.setText(no_hp);
        Picasso.get().load(foto).placeholder(R.drawable.ic_default_account_circle_24dp).memoryPolicy(MemoryPolicy.NO_CACHE, MemoryPolicy.NO_STORE).networkPolicy(NetworkPolicy.NO_CACHE, NetworkPolicy.NO_STORE).into(ivFoto);
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
    public void showDialogUpdate() {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                this);
        alertDialogBuilder.setTitle("Ingin Menambah Data Pegawai Baru ?");
        alertDialogBuilder
                .setMessage("Klik Ya untuk melakukan input !")
                .setPositiveButton("Ya", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {

                        String nama = edtNama.getText().toString().trim();
                        String username = edtUsername.getText().toString().trim();
                        String password = edtPassword.getText().toString().trim();
                        String konfirmasi_password = edtKonfirmasiPassword.getText().toString().trim();
                        String alamat = edtAlamat.getText().toString().trim();
                        String no_hp = edtNoHp.getText().toString().trim();
                        String foto = data_photo;

                        try {
                            formEditPengajarPresenter.onUpdatePengajar(id_pengajar, nama, username, password, konfirmasi_password, alamat, no_hp, foto);
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
    public void showDialogDelete() {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                this);
        alertDialogBuilder.setTitle("Yakin Ingin Menghapus Akun Ini ?");
        alertDialogBuilder
                .setMessage("Klik Ya untuk Menghapus !")
                .setPositiveButton("Ya", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {

                        try {
                            formEditPengajarPresenter.hapusAkun(id_pengajar);
                        } catch (Exception e) {
                            onErrorMessage("Terjadi Kesalahan Hapus " + e.toString());
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
                onErrorMessage("Gambar Error " + e.toString());
            }

            data_photo = formEditPengajarPresenter.getStringImage(bitmap);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.action_bar_halaman_form_edit_pengajar, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {

            case android.R.id.home:
                onBackPressed();
                break;
            case R.id.menu_hapus_akun:
                showDialogDelete();
                break;
        }

        return super.onOptionsItemSelected(item);
    }
}
