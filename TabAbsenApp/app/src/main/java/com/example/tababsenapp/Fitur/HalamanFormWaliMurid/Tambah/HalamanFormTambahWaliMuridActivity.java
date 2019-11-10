package com.example.tababsenapp.Fitur.HalamanFormWaliMurid.Tambah;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.tababsenapp.Fitur.HalamanFormWaliMurid.Tambah.presenter.FormTambahWaliMuridPresenter;
import com.example.tababsenapp.Fitur.HalamanFormWaliMurid.Tambah.presenter.IFormTambahWaliMuridPresenter;
import com.example.tababsenapp.Fitur.HalamanFormWaliMurid.Tambah.view.IFormTambahWaliMuridView;
import com.example.tababsenapp.R;

import es.dmoral.toasty.Toasty;

public class HalamanFormTambahWaliMuridActivity extends AppCompatActivity implements IFormTambahWaliMuridView {

    IFormTambahWaliMuridPresenter formTambahWaliMuridPresenter;

    Toolbar toolbar;
    EditText edtNama, edtUsername, edtPassword, edtKonfirmasiPassword, edtAlamat, edtNoHp;
    Button btnSubmit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_halaman_form_tambah_wali_murid);

        formTambahWaliMuridPresenter = new FormTambahWaliMuridPresenter(this, this);

        toolbar = findViewById(R.id.toolbar);
        edtNama = findViewById(R.id.edt_nama);
        edtUsername = findViewById(R.id.edt_username);
        edtPassword =findViewById(R.id.edt_password);
        edtKonfirmasiPassword = findViewById(R.id.edt_konfirmasi_password);
        edtAlamat = findViewById(R.id.edt_alamat);
        edtNoHp = findViewById(R.id.edt_no_hp);
        btnSubmit = findViewById(R.id.btn_submit);

        initActionBar();

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialog();
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
    public void showDialog() {
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

                        try {
                            formTambahWaliMuridPresenter.onSubmitWaliMurid(nama, username, password, konfirmasi_password, alamat, no_hp);
                        } catch (Exception e) {
                            onSubmitError("Terjadi Kesalahan Submit " + e.toString());
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

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {

            case android.R.id.home:
                onBackPressed();
                break;
        }
        return true;
    }
}
