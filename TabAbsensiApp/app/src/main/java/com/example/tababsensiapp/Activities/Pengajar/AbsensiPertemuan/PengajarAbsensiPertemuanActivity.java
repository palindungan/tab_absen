package com.example.tababsensiapp.Activities.Pengajar.AbsensiPertemuan;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
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

    Toolbar toolbar;

    TextView tvNamaPengajar, tvDetailKelasP, tvWaktuDetailMulai, tvLatitude, tvLongitude;
    Button btnBatal, btnNext;

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

        btnBatal = findViewById(R.id.btn_batal);

        toolbar = findViewById(R.id.toolbar);

        initActionBar();

        btnBatal.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btn_batal) {
            showDialogDelete();
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
    public void setNilaiDefault(HashMap<String, String> data) {

        String id_pengajar = data.get("id_pengajar");

        String nama_pengajar = data.get("nama_pengajar");
        String nama_mata_pelajaran = data.get("nama_mata_pelajaran");

        String hari_btn = data.get("hari_btn");
        String waktu_mulai = data.get("waktu_mulai");
        String lokasi_mulai_la = data.get("lokasi_mulai_la");
        String lokasi_mulai_lo = data.get("lokasi_mulai_lo");

        String hari_jadwal = data.get("hari_jadwal");
        String jam_mulai = data.get("jam_mulai");
        String jam_berakhir = data.get("jam_berakhir");
        String harga_fee = data.get("harga_fee");

        tvNamaPengajar.setText("Nama Pengajar : " + nama_pengajar);

        tvDetailKelasP.setText(nama_mata_pelajaran + " (" + hari_jadwal + ", " + jam_mulai + " - " + jam_berakhir + ") / Rp " + harga_fee);

        tvWaktuDetailMulai.setText("Waktu Kelas Dimulai : " + hari_btn + ", " + waktu_mulai);

        tvLatitude.setText("Latitude : (" + lokasi_mulai_la + ")");
        tvLongitude.setText("Longitude : (" + lokasi_mulai_lo + ")");

        mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(GoogleMap googleMap) {
                map = googleMap;

                map.clear();

                double latitude = Double.parseDouble(lokasi_mulai_la);
                double longitude = Double.parseDouble(lokasi_mulai_lo);

                // now add location in map
                latLng = new LatLng(latitude, longitude);
                map.addMarker(new MarkerOptions().position(latLng).title("Lokasi Sekarang"));
                map.moveCamera(CameraUpdateFactory.newLatLng(latLng));
                map.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, 15f));
            }
        });
    }

    @Override
    public void showDialogDelete() {
        androidx.appcompat.app.AlertDialog.Builder alertDialogBuilder = new androidx.appcompat.app.AlertDialog.Builder(
                this);
        alertDialogBuilder.setTitle("Yakin Ingin Membatalkan Pertemuan ?");
        alertDialogBuilder
                .setMessage("Klik Ya untuk Menghapus Data !")
                .setPositiveButton("Ya", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {

                        try {
                            pengajarAbsensiPertemuanPresenter.hapusData(id_pertemuan);
                        } catch (Exception e) {
                            onErrorMessage("Terjadi Kesalahan Hapus " + e.toString());
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
    public void backPressed() {
        onBackPressed();
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
