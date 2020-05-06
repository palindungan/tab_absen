package com.its.bigstars.Activities.Data.WaliMurid.Add;

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

import com.its.bigstars.Activities.Data.WaliMurid.Add.presenter.DataWaliMuridAddPresenter;
import com.its.bigstars.Activities.Data.WaliMurid.Add.presenter.IDataWaliMuridAddPresenter;
import com.its.bigstars.Activities.Data.WaliMurid.Add.view.IDataWaliMuridAddView;
import com.its.bigstars.Controllers.GlobalProcess;
import com.its.bigstars.Controllers.ToastMessage;
import com.its.bigstars.R;

public class DataWaliMuridAddActivity extends AppCompatActivity implements View.OnClickListener, IDataWaliMuridAddView {

    IDataWaliMuridAddPresenter dataWaliMuridAddPresenter;
    ToastMessage toastMessage;
    GlobalProcess globalProcess;

    Toolbar toolbar;
    EditText edtNama, edtUsername, edtPassword, edtKonfirmasiPassword, edtAlamat, edtNoHp;
    Button btnSubmit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_wali_murid_add);

        dataWaliMuridAddPresenter = new DataWaliMuridAddPresenter(this, this);
        toastMessage = new ToastMessage(this);
        globalProcess = new GlobalProcess();

        toolbar = findViewById(R.id.toolbar);
        edtNama = findViewById(R.id.edt_nama);
        edtUsername = findViewById(R.id.edt_username);
        edtPassword = findViewById(R.id.edt_password);
        edtKonfirmasiPassword = findViewById(R.id.edt_konfirmasi_password);
        edtAlamat = findViewById(R.id.edt_alamat);
        edtNoHp = findViewById(R.id.edt_no_hp);
        btnSubmit = findViewById(R.id.btn_submit);

        initActionBar();

        btnSubmit.setOnClickListener(this);

    }

    private void initActionBar() {
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
    }

    private void showDialog() {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                this);
        alertDialogBuilder.setTitle("Ingin Menambah Data Baru ?");
        alertDialogBuilder
                .setMessage("Klik Ya untuk melakukan input !")
                .setPositiveButton("Ya", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {

                        String inputNama = edtNama.getText().toString().trim();
                        String inputUsername = edtUsername.getText().toString().trim();
                        String inputPassword = edtPassword.getText().toString().trim();
                        String inputKonfirmasi_password = edtKonfirmasiPassword.getText().toString().trim();
                        String inputAlamat = edtAlamat.getText().toString().trim();
                        String inputNo_hp = edtNoHp.getText().toString().trim();

                        boolean isEmpty = false;
                        boolean isInvalidKonfirmasi = false;

                        if (TextUtils.isEmpty(inputNama)) {
                            isEmpty = true;
                            edtNama.setError("Isi Data Dengan Lengkap");
                        } else if (TextUtils.isEmpty(inputUsername)) {
                            isEmpty = true;
                            edtUsername.setError("Isi Data Dengan Lengkap");
                        } else if (TextUtils.isEmpty(inputPassword)) {
                            isEmpty = true;
                            edtPassword.setError("Isi Data Dengan Lengkap");
                        } else if (TextUtils.isEmpty(inputKonfirmasi_password)) {
                            isEmpty = true;
                            edtKonfirmasiPassword.setError("Isi Data Dengan Lengkap");
                        } else if (TextUtils.isEmpty(inputAlamat)) {
                            isEmpty = true;
                            edtAlamat.setError("Isi Data Dengan Lengkap");
                        } else if (TextUtils.isEmpty(inputNo_hp)) {
                            isEmpty = true;
                            edtNoHp.setError("Isi Data Dengan Lengkap");
                        }

                        if (!inputPassword.equals(inputKonfirmasi_password)) {
                            isInvalidKonfirmasi = true;
                            edtKonfirmasiPassword.setError("Konfirmasi Password Salah");
                        }

                        try {

                            if (!isEmpty && !isInvalidKonfirmasi) {
                                dataWaliMuridAddPresenter.onSubmit(
                                        "" + inputNama,
                                        "" + inputUsername,
                                        "" + inputPassword,
                                        "" + inputAlamat,
                                        "" + inputNo_hp);
                            }

                        } catch (Exception e) {
                            toastMessage.onErrorMessage("Terjadi Kesalahan Submit " + e.toString());
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
    public void onClick(View v) {
        if (v.getId() == R.id.btn_submit) {
            showDialog();
        }
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
