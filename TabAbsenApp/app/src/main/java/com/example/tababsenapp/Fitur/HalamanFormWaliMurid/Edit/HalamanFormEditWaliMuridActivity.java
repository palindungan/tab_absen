package com.example.tababsenapp.Fitur.HalamanFormWaliMurid.Edit;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.tababsenapp.Fitur.HalamanFormWaliMurid.Edit.presenter.FormEditWaliMuridPresenter;
import com.example.tababsenapp.Fitur.HalamanFormWaliMurid.Edit.presenter.IFormEditWaliMuridPresenter;
import com.example.tababsenapp.Fitur.HalamanFormWaliMurid.Edit.view.IFormEditWaliMuridView;
import com.example.tababsenapp.R;

import es.dmoral.toasty.Toasty;

public class HalamanFormEditWaliMuridActivity extends AppCompatActivity implements IFormEditWaliMuridView {

    IFormEditWaliMuridPresenter formEditWaliMuridPresenter;

    Toolbar toolbar;
    EditText edtNama, edtUsername, edtPassword, edtKonfirmasiPassword, edtAlamat, edtNoHp;
    Button btnUpdate;

    String EXTRA_ID_WALI_MURID = "EXTRA_ID_WALI_MURID";
    String id_wali_murid = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_halaman_form_edit_wali_murid);

        toolbar = findViewById(R.id.toolbar);
        initActionBar();

        edtNama = findViewById(R.id.edt_nama);
        edtUsername = findViewById(R.id.edt_username);
        edtPassword = findViewById(R.id.edt_password);
        edtKonfirmasiPassword = findViewById(R.id.edt_konfirmasi_password);
        edtAlamat = findViewById(R.id.edt_alamat);
        edtNoHp = findViewById(R.id.edt_no_hp);
        btnUpdate = findViewById(R.id.btn_update);

        formEditWaliMuridPresenter = new FormEditWaliMuridPresenter(this, this);
        id_wali_murid = getIntent().getStringExtra(EXTRA_ID_WALI_MURID);
        formEditWaliMuridPresenter.inisiasiAwal(id_wali_murid);

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
    public void setNilaiDefault(String nama, String username, String alamat, String no_hp) {
        edtNama.setText(nama);
        edtUsername.setText(username);
        edtAlamat.setText(alamat);
        edtNoHp.setText(no_hp);
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

                        try {
                            formEditWaliMuridPresenter.onUpdateWaliMurid(id_wali_murid, nama, username, password, konfirmasi_password, alamat, no_hp);
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
                            formEditWaliMuridPresenter.hapusAkun(id_wali_murid);
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
