package com.example.tababsenapp.Fitur.HalamanFormPengajar.Tambah;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Base64;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.tababsenapp.Fitur.HalamanFormPengajar.Tambah.presenter.FormTambahPengajarPresenter;
import com.example.tababsenapp.Fitur.HalamanFormPengajar.Tambah.presenter.IFormTambahPengajarPresenter;
import com.example.tababsenapp.Fitur.HalamanFormPengajar.Tambah.view.IFormTambahPengajarView;
import com.example.tababsenapp.R;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import es.dmoral.toasty.Toasty;

public class HalamanFormTambahPengajarActivity extends AppCompatActivity implements IFormTambahPengajarView {

    IFormTambahPengajarPresenter formTambahPengajarPresenter;

    Toolbar toolbar;

    ImageView ivFoto;
    EditText txtNama, txtUsername, txtPassword, txtAlamat, txtNoHp;
    Button btnSubmit;

    private Bitmap bitmap;
    String data_photo;
    private static final String TAG = HalamanFormTambahPengajarActivity.class.getSimpleName(); // getting the info

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_halaman_form_tambah_pengajar);

        formTambahPengajarPresenter = new FormTambahPengajarPresenter(this, this);

        toolbar = findViewById(R.id.toolbar);
        initActionBar();

        ivFoto = findViewById(R.id.iv_foto);
        txtNama = findViewById(R.id.txt_nama);
        txtUsername = findViewById(R.id.txt_username);
        txtPassword = findViewById(R.id.txt_password);
        txtAlamat = findViewById(R.id.txt_alamat);
        txtNoHp = findViewById(R.id.txt_no_hp);
        btnSubmit = findViewById(R.id.btn_submit);

        ivFoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent, "Pilih Gambar"), 1);
            }
        });

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

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
    public void onSubmitSuccess(String message) {
        Toasty.success(this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onSubmitError(String message) {
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
                onSubmitError("Gambar Error " + e.toString());
            }

            data_photo = formTambahPengajarPresenter.getStringImage(bitmap);
        }
    }
}
