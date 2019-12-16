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

import com.example.tababsensiapp.Activities.Pengajar.AbsensiPertemuan.view.IPengajarAbsensiPertemuanView;
import com.example.tababsensiapp.R;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import es.dmoral.toasty.Toasty;

public class PengajarAbsensiPertemuanActivity extends AppCompatActivity implements View.OnClickListener, IPengajarAbsensiPertemuanView, LocationListener {

    private static final int PERMISSION_CODE = 101;
    LocationManager locationManager;
    Boolean isGpsLocation;
    Boolean isNetworkLocation;
    Location loc;
    ProgressDialog progressDialog;

    Toolbar toolbar;

    TextView tvLocation;
    Button btnLocation, btnOpenMap;

    String[] permissions_all = {Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.ACCESS_FINE_LOCATION};

    SupportMapFragment mapFragment;
    GoogleMap map;
    LatLng latLng;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pengajar_absensi_pertemuan);

        toolbar = findViewById(R.id.toolbar);

        tvLocation = findViewById(R.id.tv_location);
        btnLocation = findViewById(R.id.btn_location);
        btnOpenMap = findViewById(R.id.btn_open_map);

        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Fetching Location... ");

        initActionBar();

        btnLocation.setOnClickListener(this);
        btnOpenMap.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btn_open_map) {

            mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
            mapFragment.getMapAsync(new OnMapReadyCallback() {
                @Override
                public void onMapReady(GoogleMap googleMap) {
                    map = googleMap;

                    // currently adding marker in new delhi
                    latLng = new LatLng(28.61, 77.20);
                    map.addMarker(new MarkerOptions().position(latLng).title("New Delhi"));
                    map.moveCamera(CameraUpdateFactory.newLatLng(latLng));
                    map.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, 15f));
                }
            });
        }

        if (v.getId() == R.id.btn_location) {
            progressDialog.show();
            getLocation();
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
    public void getLocation() {
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
        ActivityCompat.requestPermissions(PengajarAbsensiPertemuanActivity.this, permissions_all, PERMISSION_CODE);
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
        AlertDialog.Builder al = new AlertDialog.Builder(this);
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

                locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 1000 * 60 * 1, 10, PengajarAbsensiPertemuanActivity.this);

                if (locationManager != null) {
                    loc = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);

                    if (loc != null) {
                        updateUi(loc);
                    }

                }

            } else if (isNetworkLocation) {

                locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 1000 * 60 * 1, 10, PengajarAbsensiPertemuanActivity.this);

                if (locationManager != null) {
                    loc = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);

                    if (loc != null) {
                        updateUi(loc);
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
    public void updateUi(Location loc) {

        if (loc.getLatitude() == 0 && loc.getLongitude() == 0) {
            getDeviceLocation();
        } else {
            progressDialog.dismiss();
            tvLocation.setText("Location : " + loc.getLatitude() + " , " + loc.getLongitude());
        }
    }

    @Override
    public void onLocationChanged(Location location) {
        updateUi(location);
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
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {

            case android.R.id.home:
                onBackPressed();
                break;
        }
        return true;
    }
}
