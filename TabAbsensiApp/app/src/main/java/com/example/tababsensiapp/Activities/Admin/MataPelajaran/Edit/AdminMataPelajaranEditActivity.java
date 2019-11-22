package com.example.tababsensiapp.Activities.Admin.MataPelajaran.Edit;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.DialogInterface;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.tababsensiapp.Activities.Admin.MataPelajaran.Edit.presenter.AdminMataPelajaranEditPresenter;
import com.example.tababsensiapp.Activities.Admin.MataPelajaran.Edit.presenter.IAdminMataPelajaranEditPresenter;
import com.example.tababsensiapp.Activities.Admin.MataPelajaran.Edit.view.IAdminMataPelajaranEditView;
import com.example.tababsensiapp.R;

import es.dmoral.toasty.Toasty;

public class AdminMataPelajaranEditActivity extends AppCompatActivity implements View.OnClickListener, IAdminMataPelajaranEditView {

    IAdminMataPelajaranEditPresenter adminMataPelajaranEditPresenter;

    Toolbar toolbar;
    EditText edtNama;
    Button btnUpdate;

    public static final String EXTRA_ID_MATA_PELAJARAN = "EXTRA_ID_MATA_PELAJARAN";
    String id_mata_pelajaan = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_mata_pelajaran_edit);

        toolbar = findViewById(R.id.toolbar);
        initActionBar();

        edtNama = findViewById(R.id.edt_nama);
        btnUpdate = findViewById(R.id.btn_update);

        adminMataPelajaranEditPresenter = new AdminMataPelajaranEditPresenter(this, this);
        id_mata_pelajaan = getIntent().getStringExtra(EXTRA_ID_MATA_PELAJARAN);
        adminMataPelajaranEditPresenter.inisiasiAwal(id_mata_pelajaan);

        btnUpdate.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btn_update) {
            showDialogUpdate();
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
        alertDialogBuilder.setTitle("Ingin Mengupdate Data ?");
        alertDialogBuilder
                .setMessage("Klik Ya untuk melakukan update !")
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
                                adminMataPelajaranEditPresenter.onUpdate(id_mata_pelajaan, inputNama);
                            }
                            
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
                            adminMataPelajaranEditPresenter.hapusAkun(id_mata_pelajaan);
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
