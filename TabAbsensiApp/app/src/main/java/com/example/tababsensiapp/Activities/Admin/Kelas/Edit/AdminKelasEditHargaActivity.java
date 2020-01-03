package com.example.tababsensiapp.Activities.Admin.Kelas.Edit;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.DialogInterface;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.tababsensiapp.Activities.Admin.Kelas.Edit.presenter.AdminKelasEditHargaPresenter;
import com.example.tababsensiapp.Activities.Admin.Kelas.Edit.presenter.IAdminKelasEditHargaPresenter;
import com.example.tababsensiapp.Activities.Admin.Kelas.Edit.view.IAdminKelasEditHargaView;
import com.example.tababsensiapp.R;

import es.dmoral.toasty.Toasty;

public class AdminKelasEditHargaActivity extends AppCompatActivity implements View.OnClickListener, IAdminKelasEditHargaView {

    IAdminKelasEditHargaPresenter adminKelasEditHargaPresenter;

    Toolbar toolbar;
    EditText edtHargaFee, edtHargaSpp;
    Button btnUpdate;

    public static final String EXTRA_ID_KELAS_P = "EXTRA_ID_KELAS_P";
    String id_kelas_p = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_kelas_edit_harga);

        toolbar = findViewById(R.id.toolbar);
        initActionBar();

        edtHargaFee = findViewById(R.id.edt_harga_fee);
        edtHargaSpp = findViewById(R.id.edt_harga_spp);
        btnUpdate = findViewById(R.id.btn_update);

        adminKelasEditHargaPresenter = new AdminKelasEditHargaPresenter(this, this);
        id_kelas_p = getIntent().getStringExtra(EXTRA_ID_KELAS_P);
        adminKelasEditHargaPresenter.inisiasiAwal(id_kelas_p);

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
    public void setNilaiDefault(String harga_fee, String harga_spp) {
        edtHargaFee.setText(harga_fee);
        edtHargaSpp.setText(harga_spp);
    }

    @Override
    public void onSuccessMessage(String message) {
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
        alertDialogBuilder.setTitle("Ingin Mengedit Data ?");
        alertDialogBuilder
                .setMessage("Klik Ya untuk melakukan edit !")
                .setPositiveButton("Ya", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {

                        String inputHargaFee = edtHargaFee.getText().toString().trim();
                        String inputHargaSpp = edtHargaSpp.getText().toString().trim();

                        boolean isEmpty = false;

                        if (TextUtils.isEmpty(inputHargaFee)) {
                            isEmpty = true;
                            edtHargaFee.setError("Isi Data Dengan Lengkap");
                        } else if (TextUtils.isEmpty(inputHargaSpp)) {
                            isEmpty = true;
                            edtHargaSpp.setError("Isi Data Dengan Lengkap");
                        }

                        try {

                            if (!isEmpty) {
                                adminKelasEditHargaPresenter.onUpdate(id_kelas_p, inputHargaFee, inputHargaSpp);
                            }

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
    public void backPressed() {
        onBackPressed();
    }
}
