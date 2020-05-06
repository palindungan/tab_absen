package com.its.bigstars.Activities.Data.MataPelajaran.Edit;

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

import com.its.bigstars.Activities.Data.MataPelajaran.Edit.presenter.DataMataPelajaranEditPresenter;
import com.its.bigstars.Activities.Data.MataPelajaran.Edit.presenter.IDataMataPelajaranEditPresenter;
import com.its.bigstars.Activities.Data.MataPelajaran.Edit.view.IDataMataPelajaranEditView;
import com.its.bigstars.Controllers.BaseUrl;
import com.its.bigstars.Controllers.GlobalProcess;
import com.its.bigstars.Controllers.ToastMessage;
import com.its.bigstars.R;

public class DataMataPelajaranEditActivity extends AppCompatActivity implements View.OnClickListener, IDataMataPelajaranEditView {

    IDataMataPelajaranEditPresenter dataMataPelajaranEditPresenter;
    BaseUrl baseUrl;
    GlobalProcess globalProcess;
    ToastMessage toastMessage;

    Toolbar toolbar;
    EditText edtNama;
    Button btnUpdate;

    public static final String EXTRA_ID_MATA_PELAJARAN = "EXTRA_ID_MATA_PELAJARAN";
    public static final String EXTRA_NAMA = "EXTRA_NAMA";
    String id_mata_pelajaran, nama;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data_mata_pelajaran_edit);

        dataMataPelajaranEditPresenter = new DataMataPelajaranEditPresenter(this, this);
        baseUrl = new BaseUrl();
        globalProcess = new GlobalProcess();
        toastMessage = new ToastMessage(this);

        toolbar = findViewById(R.id.toolbar);
        edtNama = findViewById(R.id.edt_nama);
        btnUpdate = findViewById(R.id.btn_update);

        id_mata_pelajaran = getIntent().getStringExtra(EXTRA_ID_MATA_PELAJARAN);
        nama = getIntent().getStringExtra(EXTRA_NAMA);

        initActionBar();
        inisiasiAwal(id_mata_pelajaran, nama);

        btnUpdate.setOnClickListener(this);
    }

    private void initActionBar() {
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
    }

    private void inisiasiAwal(String id_mata_pelajaran, String nama) {
        edtNama.setText(nama);
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

                        boolean isEmpty = false;

                        if (TextUtils.isEmpty(inputNama)) {
                            isEmpty = true;
                            edtNama.setError("Isi Data Dengan Lengkap");
                        }

                        try {

                            if (!isEmpty) {
                                dataMataPelajaranEditPresenter.onUpdate(
                                        "" + id_mata_pelajaran,
                                        "" + inputNama);
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
