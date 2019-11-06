package com.example.tababsenapp.Fitur.HalamanFormPengajar.Edit;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.tababsenapp.Fitur.HalamanFormPengajar.Edit.presenter.FormEditPengajarPresenter;
import com.example.tababsenapp.Fitur.HalamanFormPengajar.Edit.presenter.IFormEditPengajarPresenter;
import com.example.tababsenapp.Fitur.HalamanFormPengajar.Edit.view.IFormEditPengajarView;
import com.example.tababsenapp.R;

public class HalamanFormEditPengajarActivity extends AppCompatActivity implements IFormEditPengajarView {

    IFormEditPengajarPresenter formEditPengajarPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_halaman_form_edit_pengajar);

        formEditPengajarPresenter = new FormEditPengajarPresenter(this, this);

    }
}
