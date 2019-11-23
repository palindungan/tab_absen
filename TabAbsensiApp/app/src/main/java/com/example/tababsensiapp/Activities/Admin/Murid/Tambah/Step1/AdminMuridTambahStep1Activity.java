package com.example.tababsensiapp.Activities.Admin.Murid.Tambah.Step1;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

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
import android.widget.Toast;

import com.example.tababsensiapp.Activities.Admin.Murid.Tambah.Step1.presenter.AdminMuridTambahStep1Presenter;
import com.example.tababsensiapp.Activities.Admin.Murid.Tambah.Step1.presenter.IAdminMuridTambahStep1Presenter;
import com.example.tababsensiapp.Activities.Admin.Murid.Tambah.Step1.view.IAdminMuridTambahStep1View;
import com.example.tababsensiapp.Activities.Admin.Murid.Tambah.Step2.AdminMuridTambahStep2Activity;
import com.example.tababsensiapp.R;

import java.io.IOException;

import es.dmoral.toasty.Toasty;

public class AdminMuridTambahStep1Activity extends AppCompatActivity implements View.OnClickListener, IAdminMuridTambahStep1View {

    IAdminMuridTambahStep1Presenter adminMuridTambahStep1Presenter;

    Toolbar toolbar;

    ImageView ivFoto;
    EditText edtNama;
    Button btnNext;

    private Bitmap bitmap;
    String data_photo = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_murid_tambah_step1);

        adminMuridTambahStep1Presenter = new AdminMuridTambahStep1Presenter(this, this);

        toolbar = findViewById(R.id.toolbar);
        initActionBar();

        ivFoto = findViewById(R.id.iv_foto);
        edtNama = findViewById(R.id.edt_nama);
        btnNext = findViewById(R.id.btn_next);

        ivFoto.setOnClickListener(this);
        btnNext.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.iv_foto) {
            Intent intent = new Intent();
            intent.setType("image/*");
            intent.setAction(Intent.ACTION_GET_CONTENT);
            startActivityForResult(Intent.createChooser(intent, "Pilih Gambar"), 1);
        }
        if (v.getId() == R.id.btn_next) {

            String inputNama = edtNama.getText().toString().trim();

            boolean isEmpty = false;

            if (TextUtils.isEmpty(inputNama)) {
                isEmpty = true;
                edtNama.setError("Isi Data Dengan Lengkap");
            }

            if (!isEmpty) {
                Intent intent = new Intent(getApplicationContext(), AdminMuridTambahStep2Activity.class);
                intent.putExtra(AdminMuridTambahStep2Activity.EXTRA_NAMA, inputNama);
                intent.putExtra(AdminMuridTambahStep2Activity.EXTRA_FOTO, data_photo);
                startActivity(intent);
            }
        }
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

            data_photo = adminMuridTambahStep1Presenter.getStringImage(bitmap);
        }
    }
}
