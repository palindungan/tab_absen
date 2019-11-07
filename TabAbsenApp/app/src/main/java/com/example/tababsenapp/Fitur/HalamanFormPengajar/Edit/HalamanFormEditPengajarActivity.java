package com.example.tababsenapp.Fitur.HalamanFormPengajar.Edit;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.tababsenapp.Fitur.HalamanFormPengajar.Edit.presenter.FormEditPengajarPresenter;
import com.example.tababsenapp.Fitur.HalamanFormPengajar.Edit.presenter.IFormEditPengajarPresenter;
import com.example.tababsenapp.Fitur.HalamanFormPengajar.Edit.view.IFormEditPengajarView;
import com.example.tababsenapp.R;
import com.squareup.picasso.Picasso;

import es.dmoral.toasty.Toasty;

public class HalamanFormEditPengajarActivity extends AppCompatActivity implements IFormEditPengajarView {

    IFormEditPengajarPresenter formEditPengajarPresenter;

    Toolbar toolbar;

    EditText edtNama, edtUsername, edtPassword, edtKonfirmasiPassword, edtAlamat, edtNoHp;
    ImageView ivFoto;
    Button btnUpdate;

    String EXTRA_ID_PENGAJAR = "EXTRA_ID_PENGAJAR";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_halaman_form_edit_pengajar);

        formEditPengajarPresenter = new FormEditPengajarPresenter(this, this);

        toolbar = findViewById(R.id.toolbar);
        initActionBar();

        edtNama = findViewById(R.id.edt_nama);
        edtUsername = findViewById(R.id.edt_username);
        edtPassword = findViewById(R.id.edt_password);
        edtKonfirmasiPassword = findViewById(R.id.edt_konfirmasi_password);
        edtAlamat = findViewById(R.id.edt_alamat);
        edtNoHp = findViewById(R.id.edt_no_hp);
        ivFoto = findViewById(R.id.iv_foto);

        String id_pengajar = getIntent().getStringExtra(EXTRA_ID_PENGAJAR);
        formEditPengajarPresenter.inisiasiAwal(id_pengajar);

        btnUpdate = findViewById(R.id.btn_update);
        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

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
    public void setNilaiDefault(String nama, String username, String alamat, String no_hp, String foto) {
        edtNama.setText(nama);
        edtUsername.setText(username);
        edtAlamat.setText(alamat);
        edtNoHp.setText(no_hp);
        Picasso.get().load(foto).placeholder(R.drawable.ic_default_account_circle_24dp).into(ivFoto);
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
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {

            case android.R.id.home:
                onBackPressed();
                break;
        }
        return true;
    }
}
