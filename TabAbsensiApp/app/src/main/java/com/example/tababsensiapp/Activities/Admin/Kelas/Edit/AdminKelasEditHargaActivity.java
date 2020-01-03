package com.example.tababsensiapp.Activities.Admin.Kelas.Edit;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.example.tababsensiapp.Activities.Admin.Kelas.Edit.view.IAdminKelasEditHargaView;
import com.example.tababsensiapp.R;

public class AdminKelasEditHargaActivity extends AppCompatActivity implements View.OnClickListener, IAdminKelasEditHargaView {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_kelas_edit_harga);
    }

    @Override
    public void onClick(View v) {
        
    }
}
