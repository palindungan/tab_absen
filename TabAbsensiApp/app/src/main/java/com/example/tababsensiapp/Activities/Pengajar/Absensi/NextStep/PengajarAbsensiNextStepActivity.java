package com.example.tababsensiapp.Activities.Pengajar.Absensi.NextStep;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.tababsensiapp.Activities.Pengajar.Absensi.NextStep.presenter.IPengajarAbsensiNextStepPresenter;
import com.example.tababsensiapp.Activities.Pengajar.Absensi.NextStep.presenter.PengajarAbsensiNextStepPresenter;
import com.example.tababsensiapp.Activities.Pengajar.Absensi.NextStep.view.IPengajarAbsensiNextStepView;
import com.example.tababsensiapp.R;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;

import java.util.HashMap;

import es.dmoral.toasty.Toasty;

public class PengajarAbsensiNextStepActivity extends AppCompatActivity implements View.OnClickListener, IPengajarAbsensiNextStepView {

    IPengajarAbsensiNextStepPresenter pengajarAbsensiNextStepPresenter;

    Toolbar toolbar;
    Button btnFinish;

    EditText edtDeskripsi;

    public static final String EXTRA_ID_PERTEMUAN = "EXTRA_ID_PERTEMUAN";
    String id_pertemuan = "id_pertemuan";

    ProgressDialog progressDialog;
    private static final int PERMISSION_CODE = 101;
    String[] permissions_all = {Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.ACCESS_FINE_LOCATION};
    LocationManager locationManager;
    Boolean isGpsLocation;
    Boolean isNetworkLocation;
    Location loc;
    SupportMapFragment mapFragment;
    GoogleMap map;
    LatLng latLng;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pengajar_absensi_next_step);

        pengajarAbsensiNextStepPresenter = new PengajarAbsensiNextStepPresenter(this,this);

        id_pertemuan = getIntent().getStringExtra(EXTRA_ID_PERTEMUAN);

        toolbar = findViewById(R.id.toolbar);
        btnFinish = findViewById(R.id.btn_finish);
        edtDeskripsi = findViewById(R.id.edt_deskripsi);

        initActionBar();

        onSuccessMessage(id_pertemuan);

        btnFinish.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btn_finish) {

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
    public void onSuccessMessage(String message) {
        Toasty.success(this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onErrorMessage(String message) {
        Toasty.error(this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void backPressed() {

    }

    @Override
    public void showDialogBerakhir() {

        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                this);
        alertDialogBuilder.setTitle("Yakin Ingin Mengakhiri Kelas Pertemuan ?");
        alertDialogBuilder
                .setMessage("Klik Ya untuk Mengakhiri !")
                .setPositiveButton("Ya", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {

                        try {

                            String id_pertemuan_m = id_pertemuan;
                            String deskripsi_m = edtDeskripsi.getText().toString();

                            getLocation();
                            String lokasi_mulai_la_m = String.valueOf(loc.getLatitude());
                            String lokasi_mulai_lo_m = String.valueOf(loc.getLongitude());

                            // onSuccessMessage(id_pengajar_m + id_kelas_p_m + lokasi_mulai_la_m + lokasi_mulai_lo_m);

                            // pengajarAbsensiNextStepPresenter.onAkhiriPertemuan();

                        } catch (Exception e) {
                            onErrorMessage("Terjadi Kesalahan " + e.toString());
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
    public void getLocation() {

    }

    @Override
    public void requestPermission() {

    }

    @Override
    public boolean checkPermission() {
        return false;
    }

    @Override
    public void getDeviceLocation() {

    }

    @Override
    public void showSettingForLocation() {

    }

    @Override
    public void getLastLocation() {

    }

    @Override
    public void getFinalLocation() {

    }

    @Override
    public void keHalamanLain(String id_pertemuan) {

    }
}
