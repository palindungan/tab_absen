package com.example.tababsenapp.Fitur.HalamanFormMurid.Edit;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.tababsenapp.Fitur.HalamanFormMurid.Edit.view.IFormEditMuridView;
import com.example.tababsenapp.R;

public class HalamanFormEditMuridActivity extends AppCompatActivity implements IFormEditMuridView {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_halaman_form_edit_murid);
    }
}
