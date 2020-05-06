package com.its.bigstars.Activities.Data.MataPelajaran.Add;

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

import com.its.bigstars.Activities.Data.MataPelajaran.Add.presenter.DataMataPelajaranAddPresenter;
import com.its.bigstars.Activities.Data.MataPelajaran.Add.presenter.IDataMataPelajaranAddPresenter;
import com.its.bigstars.Activities.Data.MataPelajaran.Add.view.IDataMataPelajaranAddView;
import com.its.bigstars.Controllers.GlobalProcess;
import com.its.bigstars.Controllers.ToastMessage;
import com.its.bigstars.R;

public class DataMataPelajaranAddActivity extends AppCompatActivity implements View.OnClickListener, IDataMataPelajaranAddView {

    IDataMataPelajaranAddPresenter dataMataPelajaranAddPresenter;
    ToastMessage toastMessage;
    GlobalProcess globalProcess;

    Toolbar toolbar;
    EditText edtNama;
    Button btnSubmit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_mata_pelajaran_add);

        dataMataPelajaranAddPresenter = new DataMataPelajaranAddPresenter(this, this);
        toastMessage = new ToastMessage(this);
        globalProcess = new GlobalProcess();

        toolbar = findViewById(R.id.toolbar);
        edtNama = findViewById(R.id.edt_nama);
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

                        boolean isEmpty = false;

                        if (TextUtils.isEmpty(inputNama)) {
                            isEmpty = true;
                            edtNama.setError("Isi Data Dengan Lengkap");
                        }

                        try {

                            if (!isEmpty) {
                                dataMataPelajaranAddPresenter.onSubmit(
                                        "" + inputNama
                                );
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
