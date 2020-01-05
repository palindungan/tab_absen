package com.example.tababsensiapp.Activities.Pengajar.Absensi.NextStep;

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
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.tababsensiapp.Activities.Admin.Kelas.Detail.Kelas.AdminKelasDetailKelasActivity;
import com.example.tababsensiapp.Activities.Pengajar.Absensi.NextStep.presenter.IPengajarAbsensiNextStepPresenter;
import com.example.tababsensiapp.Activities.Pengajar.Absensi.NextStep.presenter.PengajarAbsensiNextStepPresenter;
import com.example.tababsensiapp.Activities.Pengajar.Absensi.NextStep.view.IPengajarAbsensiNextStepView;
import com.example.tababsensiapp.Activities.Pengajar.Home.PengajarHomeActivity;
import com.example.tababsensiapp.Activities.Pengajar.Riwayat.Absen.PengajarRiwayatAbsenActivity;
import com.example.tababsensiapp.Controllers.SessionManager;
import com.example.tababsensiapp.R;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.HashMap;

import es.dmoral.toasty.Toasty;

public class PengajarAbsensiNextStepActivity extends AppCompatActivity implements View.OnClickListener, IPengajarAbsensiNextStepView, LocationListener {

    IPengajarAbsensiNextStepPresenter pengajarAbsensiNextStepPresenter;

    Toolbar toolbar;
    Button btnFinish;

    EditText edtDeskripsi;

    public static final String EXTRA_ID_PERTEMUAN = "EXTRA_ID_PERTEMUAN";
    public static final String EXTRA_STATUS_PERTEMUAN = "EXTRA_STATUS_PERTEMUAN";
    public static final String EXTRA_DESKRIPSI = "EXTRA_DESKRIPSI";
    public static final String EXTRA_LOKASI_BERAKHIR_LA = "EXTRA_LOKASI_BERAKHIR_LA";
    public static final String EXTRA_LOKASI_BERAKHIR_LO = "EXTRA_LOKASI_BERAKHIR_LO";
    String id_pertemuan = "";
    String status_pertemuan = "";
    String deskripsi = "";

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

    String lokasi_berakhir_la = "";
    String lokasi_berakhir_lo = "";

    SessionManager sessionManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pengajar_absensi_next_step);

        sessionManager = new SessionManager(this);

        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Fetching Location... ");

        pengajarAbsensiNextStepPresenter = new PengajarAbsensiNextStepPresenter(this, this);

        id_pertemuan = getIntent().getStringExtra(EXTRA_ID_PERTEMUAN);
        status_pertemuan = getIntent().getStringExtra(EXTRA_STATUS_PERTEMUAN);
        deskripsi = getIntent().getStringExtra(EXTRA_DESKRIPSI);

        toolbar = findViewById(R.id.toolbar);
        btnFinish = findViewById(R.id.btn_finish);
        edtDeskripsi = findViewById(R.id.edt_deskripsi);

        initActionBar();

        setNilaiDefault();

        btnFinish.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btn_finish) {
            showDialogBerakhir();
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
    public void setNilaiDefault() {

        if (status_pertemuan.equals("Selesai")) {

            lokasi_berakhir_la = getIntent().getStringExtra(EXTRA_LOKASI_BERAKHIR_LA);
            lokasi_berakhir_lo = getIntent().getStringExtra(EXTRA_LOKASI_BERAKHIR_LO);

            edtDeskripsi.setText(deskripsi);
            edtDeskripsi.setFocusable(false);

            btnFinish.setVisibility(View.GONE);
        } else {
            getLocation();
            getLocation();

            lokasi_berakhir_la = String.valueOf(loc.getLatitude());
            lokasi_berakhir_lo = String.valueOf(loc.getLongitude());
        }

        mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        mapFragment.getMapAsync(new OnMapReadyCallback() {
            @Override
            public void onMapReady(GoogleMap googleMap) {
                map = googleMap;

                map.clear();

                double latitude = Double.parseDouble(lokasi_berakhir_la);
                double longitude = Double.parseDouble(lokasi_berakhir_lo);

                // now add location in map
                latLng = new LatLng(latitude, longitude);
                map.addMarker(new MarkerOptions().position(latLng).title("Lokasi Absen"));
                map.moveCamera(CameraUpdateFactory.newLatLng(latLng));
                map.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, 15f));
            }
        });
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

                        String id_pertemuan_m = id_pertemuan;
                        String deskripsi_m = edtDeskripsi.getText().toString();
                        String lokasi_berakhir_la_m = lokasi_berakhir_la;
                        String lokasi_berakhir_lo_m = lokasi_berakhir_lo;

                        boolean isEmpty = false;
                        if (TextUtils.isEmpty(deskripsi_m)) {
                            isEmpty = true;
                            edtDeskripsi.setError("Isi Data Dengan Lengkap");
                        }

                        try {
                            if (!isEmpty) {
                                pengajarAbsensiNextStepPresenter.onAkhiriPertemuan(id_pertemuan_m, deskripsi_m, lokasi_berakhir_la_m, lokasi_berakhir_lo_m);
                            }
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
        progressDialog.show();
        if (Build.VERSION.SDK_INT >= 23) {

            if (checkPermission()) {
                getDeviceLocation();
            } else {
                requestPermission();
            }

        } else {
            getDeviceLocation();
        }
    }

    @Override
    public void requestPermission() {
        ActivityCompat.requestPermissions(PengajarAbsensiNextStepActivity.this, permissions_all, PERMISSION_CODE);
    }

    @Override
    public boolean checkPermission() {
        for (int i = 0; i < permissions_all.length; i++) {
            int result = ContextCompat.checkSelfPermission(this, permissions_all[i]);
            if (result == PackageManager.PERMISSION_GRANTED) {
                continue;
            } else {
                return false;
            }
        }
        return true;
    }

    @Override
    public void getDeviceLocation() {
        // now all permission part complete now lets fetch location
        locationManager = (LocationManager) getSystemService(Service.LOCATION_SERVICE);

        isGpsLocation = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
        isNetworkLocation = locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER);

        if (!isGpsLocation && !isNetworkLocation) {
            showSettingForLocation();
            getLastLocation();

        } else {
            getFinalLocation();
        }
    }

    @Override
    public void showSettingForLocation() {
        android.app.AlertDialog.Builder al = new android.app.AlertDialog.Builder(this);
        al.setTitle("Location Not Enabled !");
        al.setMessage("Enable Location ?");
        al.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                startActivity(intent);
            }
        });
        al.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        al.show();
    }

    @Override
    public void getLastLocation() {
        if (locationManager != null) {
            try {
                Criteria criteria = new Criteria();
                String provider = locationManager.getBestProvider(criteria, false);
                Location location = locationManager.getLastKnownLocation(provider);
            } catch (SecurityException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void getFinalLocation() {
        try {

            if (isGpsLocation) {

                locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 1000 * 60 * 1, 10, PengajarAbsensiNextStepActivity.this);

                if (locationManager != null) {
                    loc = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);

                    if (loc != null) {

                        if (loc.getLatitude() == 0 && loc.getLongitude() == 0) {
                            getDeviceLocation();
                        } else {
                            progressDialog.dismiss();
                            latLng = new LatLng(loc.getLatitude(), loc.getLongitude());
                        }
                    }

                }

            } else if (isNetworkLocation) {

                locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 1000 * 60 * 1, 10, PengajarAbsensiNextStepActivity.this);

                if (locationManager != null) {
                    loc = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);

                    if (loc != null) {

                        if (loc.getLatitude() == 0 && loc.getLongitude() == 0) {
                            getDeviceLocation();
                        } else {
                            progressDialog.dismiss();
                            latLng = new LatLng(loc.getLatitude(), loc.getLongitude());
                        }
                    }
                }

            } else {
                onErrorMessage("Cant get Location");
            }

        } catch (SecurityException ex) {
            onErrorMessage("Cant get Location : " + ex);
        }
    }

    @Override
    public void keHalamanLain(String id_pertemuan) {
        Intent intent = new Intent(getApplicationContext(), PengajarHomeActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
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

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        switch (requestCode) {
            case PERMISSION_CODE:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    getFinalLocation();
                } else {
                    onErrorMessage("Permission Failed");
                }
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
    }
}
