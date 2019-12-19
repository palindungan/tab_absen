package com.example.tababsensiapp.Activities.Pengajar.AbsensiPertemuan;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.app.Service;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tababsensiapp.Activities.Pengajar.AbsensiPertemuan.presenter.IPengajarAbsensiPertemuanPresenter;
import com.example.tababsensiapp.Activities.Pengajar.AbsensiPertemuan.presenter.PengajarAbsensiPertemuanPresenter;
import com.example.tababsensiapp.Activities.Pengajar.AbsensiPertemuan.view.IPengajarAbsensiPertemuanView;
import com.example.tababsensiapp.R;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.HashMap;

import es.dmoral.toasty.Toasty;

public class PengajarAbsensiPertemuanActivity extends AppCompatActivity implements View.OnClickListener, IPengajarAbsensiPertemuanView, LocationListener {

    ProgressDialog progressDialog;

    Toolbar toolbar;

    TextView tvNamaPengajar, tvDetailKelasP, tvWaktuDetailMulai, tvLatitude, tvLongitude;
    Button btnBatal, btnNext;

    String[] permissions_all = {Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.ACCESS_FINE_LOCATION};

    SupportMapFragment mapFragment;
    GoogleMap map;
    LatLng latLng;

    public static final String EXTRA_ID_PERTEMUAN = "EXTRA_ID_PERTEMUAN";
    String id_pertemuan = "";

    IPengajarAbsensiPertemuanPresenter pengajarAbsensiPertemuanPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pengajar_absensi_pertemuan);

        id_pertemuan = getIntent().getStringExtra(EXTRA_ID_PERTEMUAN);

        pengajarAbsensiPertemuanPresenter = new PengajarAbsensiPertemuanPresenter(this, this);
        pengajarAbsensiPertemuanPresenter.inisiasiAwal(id_pertemuan);

        tvNamaPengajar = findViewById(R.id.tv_nama_pengajar);
        tvDetailKelasP = findViewById(R.id.tv_detail_kelas_p);
        tvWaktuDetailMulai = findViewById(R.id.tv_waktu_detail_mulai);
        tvLatitude = findViewById(R.id.tv_latitude);
        tvLongitude = findViewById(R.id.tv_longitude);

        toolbar = findViewById(R.id.toolbar);

        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Fetching Location... ");

        initActionBar();
    }

    @Override
    public void onClick(View v) {
//        if (v.getId() == R.id.btn_open_map) {
//        }
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
    public void setNilaiDefault(HashMap<String, String> data) {
        mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(GoogleMap googleMap) {
                map = googleMap;

                map.clear();

                // now add location in map
                latLng = new LatLng(12.023, 23.23);
                map.addMarker(new MarkerOptions().position(latLng).title("Current Location"));
                map.moveCamera(CameraUpdateFactory.newLatLng(latLng));
                map.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, 15f));
            }
        });
    }

    @Override
    public void onLocationChanged(Location location) {

    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onProviderDisabled(String provider) {

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
