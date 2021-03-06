package com.example.tababsensiapp.Activities.Admin.MataPelajaran.Tambah;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.DialogInterface;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.tababsensiapp.Activities.Admin.MataPelajaran.Tambah.presenter.AdminMataPelajaranTambahPresenter;
import com.example.tababsensiapp.Activities.Admin.MataPelajaran.Tambah.presenter.IAdminMataPelajaranTambahPresenter;
import com.example.tababsensiapp.Activities.Admin.MataPelajaran.Tambah.view.IAdminMataPelajaranTambahView;
import com.example.tababsensiapp.R;

import es.dmoral.toasty.Toasty;

public class AdminMataPelajaranTambahActivity extends AppCompatActivity implements View.OnClickListener, IAdminMataPelajaranTambahView {

    IAdminMataPelajaranTambahPresenter adminMataPelajaranTambahPresenter;

    Toolbar toolbar;
    EditText edtNama;
    Button btnSubmit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_mata_pelajaran_tambah);

        adminMataPelajaranTambahPresenter = new AdminMataPelajaranTambahPresenter(this, this);

        toolbar = findViewById(R.id.toolbar);
        edtNama = findViewById(R.id.edt_nama);
        btnSubmit = findViewById(R.id.btn_submit);

        initActionBar();

        btnSubmit.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btn_submit) {
            showDialog();
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
    public void showDialog() {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                this);
        alertDialogBuilder.setTitle("Ingin Menambah Data Baru ?");
        alertDialogBuilder
                .setMessage("Klik Ya untuk melakukan input !")
                .setPositiveButton("Ya", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {

                        String inputNama = edtNama.getText().toString().trim();

                        boolean isEmpty = false;

                        if (TextUtils.isEmpty(inputNama)) {
                            isEmpty = true;
                            edtNama.setError("Isi Data Dengan Lengkap");
                        }

                        try {
                            
                            if (!isEmpty) {
                                adminMataPelajaranTambahPresenter.onSubmit(inputNama);
                            }

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
