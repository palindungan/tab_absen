package com.example.tababsensiapp.Activities.Pengajar.Kelas.TampilAktif;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.tababsensiapp.Activities.Pengajar.Kelas.TampilAktif.presenter.IPengajarKelasTampilAktifPresenter;
import com.example.tababsensiapp.Activities.Pengajar.Kelas.TampilAktif.presenter.PengajarKelasTampilAktifPresenter;
import com.example.tababsensiapp.Activities.Pengajar.Kelas.TampilAktif.view.IPengajarKelasTampilAktifView;
import com.example.tababsensiapp.Controllers.SessionManager;
import com.example.tababsensiapp.Models.Pertemuan;
import com.example.tababsensiapp.R;

import java.util.ArrayList;
import java.util.HashMap;

import es.dmoral.toasty.Toasty;

public class PengajarKelasTampilAktifActivity extends AppCompatActivity implements View.OnClickListener, IPengajarKelasTampilAktifView {

    SessionManager sessionManager;
    String id_pengajar;
    IPengajarKelasTampilAktifPresenter pengajarKelasTampilAktifPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pengajar_kelas_tampil_aktif);

        sessionManager = new SessionManager(this);
        HashMap<String, String> user = sessionManager.getDataUser();
        id_pengajar = user.get(sessionManager.ID_USER);

        pengajarKelasTampilAktifPresenter = new PengajarKelasTampilAktifPresenter(this,this);
        pengajarKelasTampilAktifPresenter.inisiasiAwal(id_pengajar);
    }

    @Override
    public void onClick(View v) {

    }

    @Override
    public void initActionBar() {

    }

    @Override
    public void onSetupListView(ArrayList<Pertemuan> dataModelArrayList) {

    }

    @Override
    public void onSuccessMessage(String message) {
        Toasty.success(this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onErrorMessage(String message) {
        Toasty.error(this, message, Toast.LENGTH_SHORT).show();
    }
}
