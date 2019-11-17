package com.example.tababsenapp.Fitur.HalamanFormMurid.Edit;

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

import com.example.tababsenapp.Fitur.HalamanFormMurid.Edit.presenter.FormEditMuridPresenter;
import com.example.tababsenapp.Fitur.HalamanFormMurid.Edit.presenter.IFormEditMuridPresenter;
import com.example.tababsenapp.Fitur.HalamanFormMurid.Edit.view.IFormEditMuridView;
import com.example.tababsenapp.Fitur.HalamanFormMurid.List2.HalamanFormList2WaliMuridActivity;
import com.example.tababsenapp.R;
import com.squareup.picasso.MemoryPolicy;
import com.squareup.picasso.NetworkPolicy;
import com.squareup.picasso.Picasso;

import java.io.IOException;

import es.dmoral.toasty.Toasty;

public class HalamanFormEditMuridActivity extends AppCompatActivity implements IFormEditMuridView {
    IFormEditMuridPresenter formEditMuridPresenter;

    Toolbar toolbar;

    EditText edtNama, edtNamaWaliMurid, edtAlamat;
    Button btnUpdate, btnUbah;
    ImageView ivFoto;

    String EXTRA_ID_MURID = "EXTRA_ID_MURID";
    String EXTRA_NAMA = "EXTRA_NAMA";
    String EXTRA_FOTO = "EXTRA_FOTO";

    String id_murid = "";
    String id_wali_murid = "";

    private Bitmap bitmap;
    String data_photo = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_halaman_form_edit_murid);

        formEditMuridPresenter = new FormEditMuridPresenter(this, this);

        toolbar = findViewById(R.id.toolbar);
        initActionBar();

        edtNama = findViewById(R.id.edt_nama);
        edtNamaWaliMurid = findViewById(R.id.edt_nama_wali_murid);
        edtAlamat = findViewById(R.id.edt_alamat);
        btnUbah = findViewById(R.id.btn_ubah);
        btnUpdate = findViewById(R.id.btn_update);
        ivFoto = findViewById(R.id.iv_foto);

        id_murid = getIntent().getStringExtra(EXTRA_ID_MURID);

        formEditMuridPresenter.inisiasiAwal(id_murid);

        ivFoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent, "Pilih Gambar"), 1);
            }
        });

        btnUbah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialogUbah();
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
    public void setNilaiDefault(String nama, String id_wali_murid, String nama_wali_murid, String alamat, String foto) {

        edtNama.setText(nama);
        this.id_wali_murid = id_wali_murid;
        edtNamaWaliMurid.setText(nama_wali_murid);
        edtAlamat.setText(alamat);
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
    public void showDialogUbah() {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                this);
        alertDialogBuilder.setTitle("Ingin Mengubah Data Murid ?");
        alertDialogBuilder
                .setMessage("Klik Ya untuk Memilih Wali Murid dan Menyimpan Data!")
                .setPositiveButton("Ya", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {

                        String nama = edtNama.getText().toString().trim();
                        String foto = data_photo;

                        try {

                            Intent intent = new Intent(HalamanFormEditMuridActivity.this, HalamanFormList2WaliMuridActivity.class);
                            intent.putExtra(EXTRA_ID_MURID, id_murid);
                            intent.putExtra(EXTRA_NAMA, nama);
                            intent.putExtra(EXTRA_FOTO, foto);
                            startActivity(intent);

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
    public void showDialogUpdate() {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                this);
        alertDialogBuilder.setTitle("Ingin Mengupdate Data Murid ?");
        alertDialogBuilder
                .setMessage("Klik Ya untuk melakukan input !")
                .setPositiveButton("Ya", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {

                        String nama = edtNama.getText().toString().trim();
                        String foto = data_photo;

                        try {
                            formEditMuridPresenter.onUpdateData(id_murid, id_wali_murid, nama, foto);
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
    public void showDialogDelete() {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                this);
        alertDialogBuilder.setTitle("Yakin Ingin Menghapus Akun Ini ?");
        alertDialogBuilder
                .setMessage("Klik Ya untuk Menghapus !")
                .setPositiveButton("Ya", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {

                        try {
                            formEditMuridPresenter.hapusAkun(id_murid);
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

            data_photo = formEditMuridPresenter.getStringImage(bitmap);
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
