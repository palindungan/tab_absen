package com.its.bigstars.Activities.Data.WaliMurid.Edit;

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

import com.its.bigstars.Activities.Data.WaliMurid.Edit.presenter.DataWaliMuridEditPresenter;
import com.its.bigstars.Activities.Data.WaliMurid.Edit.presenter.IDataWaliMuridEditPresenter;
import com.its.bigstars.Activities.Data.WaliMurid.Edit.view.IDataWaliMuridEditView;
import com.its.bigstars.Controllers.BaseUrl;
import com.its.bigstars.Controllers.GlobalProcess;
import com.its.bigstars.Controllers.ToastMessage;
import com.its.bigstars.R;

public class DataWaliMuridEditActivity extends AppCompatActivity implements View.OnClickListener, IDataWaliMuridEditView {

    IDataWaliMuridEditPresenter dataWaliMuridEditPresenter;
    BaseUrl baseUrl;
    GlobalProcess globalProcess;
    ToastMessage toastMessage;

    Toolbar toolbar;
    EditText edtNama, edtUsername, edtPassword, edtKonfirmasiPassword, edtAlamat, edtNoHp;
    Button btnUpdate;

    public static final String EXTRA_ID_WALI_MURID = "EXTRA_ID_WALI_MURID";
    public static final String EXTRA_NAMA = "EXTRA_NAMA";
    public static final String EXTRA_USERNAME = "EXTRA_USERNAME";
    public static final String EXTRA_ALAMAT = "EXTRA_ALAMAT";
    public static final String EXTRA_NO_HP = "EXTRA_NO_HP";
    String id_wali_murid, nama, username, alamat, no_hp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_wali_murid_edit);

        dataWaliMuridEditPresenter = new DataWaliMuridEditPresenter(this, this);
        baseUrl = new BaseUrl();
        globalProcess = new GlobalProcess();
        toastMessage = new ToastMessage(this);

        toolbar = findViewById(R.id.toolbar);
        edtNama = findViewById(R.id.edt_nama);
        edtUsername = findViewById(R.id.edt_username);
        edtPassword = findViewById(R.id.edt_password);
        edtKonfirmasiPassword = findViewById(R.id.edt_konfirmasi_password);
        edtAlamat = findViewById(R.id.edt_alamat);
        edtNoHp = findViewById(R.id.edt_no_hp);
        btnUpdate = findViewById(R.id.btn_update);

        id_wali_murid = getIntent().getStringExtra(EXTRA_ID_WALI_MURID);
        nama = getIntent().getStringExtra(EXTRA_NAMA);
        username = getIntent().getStringExtra(EXTRA_USERNAME);
        alamat = getIntent().getStringExtra(EXTRA_ALAMAT);
        no_hp = getIntent().getStringExtra(EXTRA_NO_HP);

        initActionBar();
        inisiasiAwal(
                "" + id_wali_murid,
                "" + nama,
                "" + username,
                "" + alamat,
                "" + no_hp);

        btnUpdate.setOnClickListener(this);
    }

    private void initActionBar() {
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
    }

    private void inisiasiAwal(String id_wali_murid, String nama, String username, String alamat, String no_hp) {
        edtNama.setText(nama);
        edtUsername.setText(username);
        edtAlamat.setText(alamat);
        edtNoHp.setText(no_hp);
    }

    private void showDialogUpdate() {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                this);
        alertDialogBuilder.setTitle("Ingin Mengupdate Data ?");
        alertDialogBuilder
                .setMessage("Klik Ya untuk melakukan update !")
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
                                dataWaliMuridEditPresenter.onUpdate(id_wali_murid, inputNama, inputUsername, inputPassword, inputAlamat, inputNo_hp);
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
        if (v.getId() == R.id.btn_update) {
            showDialogUpdate();
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
        return super.onOptionsItemSelected(item);
    }
}
