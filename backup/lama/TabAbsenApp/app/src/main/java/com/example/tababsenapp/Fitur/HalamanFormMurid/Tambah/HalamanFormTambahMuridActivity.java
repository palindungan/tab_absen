package com.example.tababsenapp.Fitur.HalamanFormMurid.Tambah;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.tababsenapp.Fitur.HalamanFormMurid.List.HalamanFormListWaliMuridActivity;
import com.example.tababsenapp.Fitur.HalamanFormMurid.Tambah.presenter.FormTambahMuridPresenter;
import com.example.tababsenapp.Fitur.HalamanFormMurid.Tambah.presenter.IFormTambahMuridPresenter;
import com.example.tababsenapp.Fitur.HalamanFormMurid.Tambah.view.IFormTambahMuridView;
import com.example.tababsenapp.R;

import java.io.IOException;

import es.dmoral.toasty.Toasty;

public class HalamanFormTambahMuridActivity extends AppCompatActivity implements IFormTambahMuridView {

    IFormTambahMuridPresenter formTambahMuridPresenter;

    Toolbar toolbar;

    ImageView ivFoto;
    EditText edtNama;
    Button btnNext;

    private Bitmap bitmap;
    String data_photo = "";

    String EXTRA_NAMA = "EXTRA_NAMA";
    String EXTRA_FOTO = "EXTRA_FOTO";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_halaman_form_tambah_murid);

        formTambahMuridPresenter = new FormTambahMuridPresenter(this, this);

        toolbar = findViewById(R.id.toolbar);
        initActionBar();

        ivFoto = findViewById(R.id.iv_foto);
        edtNama = findViewById(R.id.edt_nama);
        btnNext = findViewById(R.id.btn_next);

        ivFoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent, "Pilih Gambar"), 1);
            }
        });

        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String nama = edtNama.getText().toString().trim();

                formTambahMuridPresenter.onClickNext(nama, data_photo);
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
    public void clickNext(String nama, String foto) {
        Intent intent = new Intent(HalamanFormTambahMuridActivity.this, HalamanFormListWaliMuridActivity.class);
        intent.putExtra(EXTRA_NAMA, nama);
        intent.putExtra(EXTRA_FOTO, foto);
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

            data_photo = formTambahMuridPresenter.getStringImage(bitmap);
        }
    }
}
