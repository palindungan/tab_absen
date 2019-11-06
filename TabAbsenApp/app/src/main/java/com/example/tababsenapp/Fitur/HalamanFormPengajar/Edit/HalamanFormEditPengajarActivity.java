package com.example.tababsenapp.Fitur.HalamanFormPengajar.Edit;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.tababsenapp.Fitur.HalamanFormPengajar.Edit.presenter.FormEditPengajarPresenter;
import com.example.tababsenapp.Fitur.HalamanFormPengajar.Edit.presenter.IFormEditPengajarPresenter;
import com.example.tababsenapp.Fitur.HalamanFormPengajar.Edit.view.IFormEditPengajarView;
import com.example.tababsenapp.R;

public class HalamanFormEditPengajarActivity extends AppCompatActivity implements IFormEditPengajarView {

    IFormEditPengajarPresenter formEditPengajarPresenter;

    Toolbar toolbar;

    EditText edtNama,edtUsername,edtPassword,edtAlamat,edtNoHp;
    ImageView ivFoto;
    Button btnUpdate;

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
        edtAlamat = findViewById(R.id.edt_alamat);
        edtNoHp = findViewById(R.id.edt_no_hp);
        ivFoto = findViewById(R.id.iv_foto);

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
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {

            case android.R.id.home:
                onBackPressed();
                break;
        }
        return true;
    }
}
