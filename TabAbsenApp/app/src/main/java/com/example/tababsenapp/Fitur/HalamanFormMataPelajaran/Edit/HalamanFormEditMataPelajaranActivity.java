package com.example.tababsenapp.Fitur.HalamanFormMataPelajaran.Edit;

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

import com.example.tababsenapp.Fitur.HalamanFormMataPelajaran.Edit.presenter.FormEditMataPelajaranPresenter;
import com.example.tababsenapp.Fitur.HalamanFormMataPelajaran.Edit.presenter.IFormEditMataPelajaranPresenter;
import com.example.tababsenapp.Fitur.HalamanFormMataPelajaran.Edit.view.IFormEditMataPelajaranView;
import com.example.tababsenapp.R;

import es.dmoral.toasty.Toasty;

public class HalamanFormEditMataPelajaranActivity extends AppCompatActivity implements IFormEditMataPelajaranView {

    IFormEditMataPelajaranPresenter formEditMataPelajaranPresenter;

    Toolbar toolbar;
    EditText edtNama;
    Button btnUpdate;

    String EXTRA_ID_MATA_PELAJARAN = "EXTRA_ID_MATA_PELAJARAN";
    String id_mata_pelajaan = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_halaman_form_edit_mata_pelajaran);

        toolbar = findViewById(R.id.toolbar);
        initActionBar();

        edtNama = findViewById(R.id.edt_nama);
        btnUpdate = findViewById(R.id.btn_update);

        formEditMataPelajaranPresenter = new FormEditMataPelajaranPresenter(this,this);
        id_mata_pelajaan = getIntent().getStringExtra(EXTRA_ID_MATA_PELAJARAN);
        formEditMataPelajaranPresenter.inisiasiAwal(id_mata_pelajaan);

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
    public void setNilaiDefault(String nama) {
        edtNama.setText(nama);
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

                        try {
                            formEditMataPelajaranPresenter.onUpdateWaliMurid(id_mata_pelajaan, nama);
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
                            formEditMataPelajaranPresenter.hapusAkun(id_mata_pelajaan);
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
