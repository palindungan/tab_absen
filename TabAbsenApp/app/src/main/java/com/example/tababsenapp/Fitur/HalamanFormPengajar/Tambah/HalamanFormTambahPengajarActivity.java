package com.example.tababsenapp.Fitur.HalamanFormPengajar.Tambah;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.tababsenapp.Fitur.HalamanFormPengajar.Tambah.presenter.IFormTambahPengajarPresenter;
import com.example.tababsenapp.Fitur.HalamanFormPengajar.Tambah.view.IFormTambahPengajarView;
import com.example.tababsenapp.R;

import es.dmoral.toasty.Toasty;

public class HalamanFormTambahPengajarActivity extends AppCompatActivity implements IFormTambahPengajarView {

    IFormTambahPengajarPresenter formTambahPengajarPresenter;

    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_halaman_form_tambah_pengajar);

        toolbar = findViewById(R.id.toolbar);
        initActionBar();
    }

    @Override
    public void initActionBar() {
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
    }

    @Override
    public void onSubmitSuccess(String message) {
        Toasty.success(this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onSubmitError(String message) {
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
