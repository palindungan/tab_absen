package com.example.tababsensiapp.Activities.Admin.Kelas.Tampil.Kelas;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.example.tababsensiapp.Activities.Admin.Kelas.Tampil.Kelas.view.IAdminKelasTampilKelasView;
import com.example.tababsensiapp.Models.Kelas;
import com.example.tababsensiapp.R;

import java.util.ArrayList;

public class AdminKelasTampilKelasActivity extends AppCompatActivity implements View.OnClickListener , IAdminKelasTampilKelasView {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_kelas_tampil_kelas);
    }

    @Override
    public void onClick(View v) {

    }

    @Override
    public void initActionBar() {

    }

    @Override
    public void onSetupListView(ArrayList<Kelas> dataModelArrayList) {

    }

    @Override
    public void onSucceessMessage(String message) {

    }

    @Override
    public void onErrorMessage(String message) {

    }

    @Override
    public void showDialogDelete() {

    }

    @Override
    public void backPressed() {

    }
}
