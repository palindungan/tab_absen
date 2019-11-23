package com.example.tababsensiapp.Activities.Admin.Murid.Edit.Step1;

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
import android.widget.Toast;

import com.example.tababsensiapp.Activities.Admin.Murid.Edit.Step1.presenter.AdminMuridEditStep1Presenter;
import com.example.tababsensiapp.Activities.Admin.Murid.Edit.Step1.presenter.IAdminMuridEditStep1Presenter;
import com.example.tababsensiapp.Activities.Admin.Murid.Edit.Step1.view.IAdminMuridEditStep1View;
import com.example.tababsensiapp.Activities.Admin.Murid.Edit.Step2.AdminMuridEditStep2Activity;
import com.example.tababsensiapp.R;
import com.squareup.picasso.MemoryPolicy;
import com.squareup.picasso.NetworkPolicy;
import com.squareup.picasso.Picasso;

import java.io.IOException;

import es.dmoral.toasty.Toasty;

public class AdminMuridEditStep1Activity extends AppCompatActivity implements View.OnClickListener, IAdminMuridEditStep1View {

    IAdminMuridEditStep1Presenter adminMuridEditStep1Presenter;

    Toolbar toolbar;

    EditText edtNama, edtNamaWaliMurid, edtAlamat;
    Button btnNext;
    ImageView ivFoto;

    public static final String EXTRA_ID_MURID = "EXTRA_ID_MURID";

    String id_murid = "";
    String id_wali_murid = "";

    private Bitmap bitmap;
    String data_photo = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_murid_edit_step1);

        adminMuridEditStep1Presenter = new AdminMuridEditStep1Presenter(this, this);

        toolbar = findViewById(R.id.toolbar);
        initActionBar();

        edtNama = findViewById(R.id.edt_nama);
        edtNamaWaliMurid = findViewById(R.id.edt_nama_wali_murid);
        edtAlamat = findViewById(R.id.edt_alamat);
        btnNext = findViewById(R.id.btn_next);
        ivFoto = findViewById(R.id.iv_foto);

        id_murid = getIntent().getStringExtra(EXTRA_ID_MURID);

        adminMuridEditStep1Presenter.inisiasiAwal(id_murid);

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
                Intent intent = new Intent(getApplicationContext(), AdminMuridEditStep2Activity.class);
                intent.putExtra(AdminMuridEditStep2Activity.EXTRA_ID_MURID, id_murid);
                intent.putExtra(AdminMuridEditStep2Activity.EXTRA_NAMA, inputNama);
                intent.putExtra(AdminMuridEditStep2Activity.EXTRA_FOTO, data_photo);
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
    public void showDialogDelete() {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                this);
        alertDialogBuilder.setTitle("Yakin Ingin Menghapus Akun Ini ?");
        alertDialogBuilder
                .setMessage("Klik Ya untuk Menghapus !")
                .setPositiveButton("Ya", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {

                        try {
                            adminMuridEditStep1Presenter.hapusAkun(id_murid);
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

            data_photo = adminMuridEditStep1Presenter.getStringImage(bitmap);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.action_bar_form_edit, menu);

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
