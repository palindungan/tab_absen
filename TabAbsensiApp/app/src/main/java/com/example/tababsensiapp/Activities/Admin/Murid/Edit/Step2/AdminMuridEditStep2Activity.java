package com.example.tababsensiapp.Activities.Admin.Murid.Edit.Step2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.tababsensiapp.R;

public class AdminMuridEditStep2Activity extends AppCompatActivity {

    public static final String EXTRA_ID_MURID = "EXTRA_ID_MURID";
    public static final String EXTRA_NAMA = "EXTRA_NAMA";
    public static final String EXTRA_FOTO = "EXTRA_FOTO";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_murid_edit_step2);
    }
}
