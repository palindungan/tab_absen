package com.example.tababsensiapp.Activities.Admin.Kelas.Detail.Kelas;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

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
import android.os.Handler;
import android.provider.Settings;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tababsensiapp.Activities.Admin.Kelas.Detail.Kelas.presenter.AdminKelasDetailKelasPresenter;
import com.example.tababsensiapp.Activities.Admin.Kelas.Detail.Kelas.presenter.IAdminKelasDetailKelasPresenter;
import com.example.tababsensiapp.Activities.Admin.Kelas.Detail.Kelas.view.IAdminKelasDetailKelasView;
import com.example.tababsensiapp.Activities.Admin.Kelas.Detail.Murid.Detail.AdminKelasDetailMuridDetailActivity;
import com.example.tababsensiapp.Activities.Admin.Kelas.Detail.Murid.Tampil.AdminKelasDetailMuridTampilActivity;
import com.example.tababsensiapp.Activities.Admin.Kelas.Detail.Pengajar.Sharing.AdminKelasDetailPengajarSharingActivity;
import com.example.tababsensiapp.Activities.Pengajar.Absensi.Pertemuan.PengajarAbsensiPertemuanActivity;
import com.example.tababsensiapp.Adapters.AdapterDaftarKelasMurid;
import com.example.tababsensiapp.Adapters.AdapterPengajarDaftarKelasMurid;
import com.example.tababsensiapp.Controllers.SessionManager;
import com.example.tababsensiapp.Models.Murid;
import com.example.tababsensiapp.R;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.HashMap;

import es.dmoral.toasty.Toasty;

public class AdminKelasDetailKelasActivity extends AppCompatActivity implements View.OnClickListener, IAdminKelasDetailKelasView, LocationListener {

    public static final String EXTRA_ID_KELAS_P = "EXTRA_ID_KELAS_P";
//    public static final String EXTRA_ID_MATA_PELAJARAN = "EXTRA_ID_MATA_PELAJARAN";
//    public static final String EXTRA_ID_PENGAJAR = "EXTRA_ID_PENGAJAR";

    public static final String EXTRA_STATUS_USER = "EXTRA_STATUS_USER";

    String id_kelas_p;
    //    String id_mata_pelajaran, id_pengajar;
    String statusUser = "";

    IAdminKelasDetailKelasPresenter adminKelasDetailKelasPresenter;

    private AdapterDaftarKelasMurid adapterDaftarKelasMurid;
    private AdapterPengajarDaftarKelasMurid adapterPengajarDaftarKelasMurid;
    private RecyclerView recyclerView;

    Toolbar toolbar;

    private SwipeRefreshLayout swipeRefreshLayout;
    FloatingActionButton fab;

    TextView tvNamaPelajaran, tvNamaPengajar, tvHari, tvJam, tvHargaFee, tvHargaSpp, tvStatus;

    ImageButton btnSharing, btnDeleteSharing, btnAbsen;

    SessionManager sessionManager;

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

    String hari_kelas = "";
    String harga_fee_m = "", harga_spp_m;

    String hakAkses = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_kelas_detail_kelas);

        sessionManager = new SessionManager(this);
        HashMap<String, String> user = sessionManager.getDataUser();
        hakAkses = user.get(sessionManager.HAK_AKSES);

        progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Fetching Location... ");

        recyclerView = findViewById(R.id.recycle_view);

        tvNamaPelajaran = findViewById(R.id.tv_nama_pelajaran);
        tvNamaPengajar = findViewById(R.id.tv_nama_pengajar);
        tvHari = findViewById(R.id.tv_hari);
        tvJam = findViewById(R.id.tv_jam);
        tvHargaFee = findViewById(R.id.tv_harga_fee);
        tvHargaSpp = findViewById(R.id.tv_harga_spp);
        tvStatus = findViewById(R.id.tv_status);

        id_kelas_p = getIntent().getStringExtra(EXTRA_ID_KELAS_P);
//        id_mata_pelajaran = getIntent().getStringExtra(EXTRA_ID_MATA_PELAJARAN);
//        id_pengajar = getIntent().getStringExtra(EXTRA_ID_PENGAJAR);

        adminKelasDetailKelasPresenter = new AdminKelasDetailKelasPresenter(this, this);
        adminKelasDetailKelasPresenter.onLoadSemuaData(id_kelas_p);

        toolbar = findViewById(R.id.toolbar);

        fab = findViewById(R.id.fab);
        swipeRefreshLayout = findViewById(R.id.swipe_refresh_layout);

        btnSharing = findViewById(R.id.btn_sharing);
        btnDeleteSharing = findViewById(R.id.btn_delete_sharing);
        btnAbsen = findViewById(R.id.btn_absen);

        statusUser = getIntent().getStringExtra(EXTRA_STATUS_USER);

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                // Your code to make your refresh action
                adminKelasDetailKelasPresenter.onLoadSemuaData(id_kelas_p);

                // CallYourRefreshingMethod();
                final Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        if (swipeRefreshLayout.isRefreshing()) {
                            swipeRefreshLayout.setRefreshing(false);
                        }
                    }
                }, 1000);
            }
        });

        initActionBar();

        fab.setOnClickListener(this);
        btnSharing.setOnClickListener(this);
        btnDeleteSharing.setOnClickListener(this);
        btnAbsen.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.fab) {
            Intent intent = new Intent(getApplicationContext(), AdminKelasDetailMuridTampilActivity.class);
            intent.putExtra(AdminKelasDetailMuridTampilActivity.EXTRA_ID_KELAS_P, id_kelas_p);
            startActivity(intent);
        }

        if (v.getId() == R.id.btn_sharing) {
            Intent intent = new Intent(getApplicationContext(), AdminKelasDetailPengajarSharingActivity.class);
            intent.putExtra(AdminKelasDetailPengajarSharingActivity.EXTRA_ID_KELAS_P, id_kelas_p);
            startActivity(intent);
        }

        if (v.getId() == R.id.btn_delete_sharing) {
            adminKelasDetailKelasPresenter.onDeleteSharing(id_kelas_p);
            adminKelasDetailKelasPresenter.onLoadSemuaDataKelas(id_kelas_p);
        }

        if (v.getId() == R.id.btn_absen) {
            showDialogMulai();
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
    public void setNilaiDefault(String nama_pelajaran, String nama_pengajar, String harga_fee, String harga_spp, String hari, String jam_mulai, String jam_berakhir, String id_sharing, String nama_sharing) {
        tvNamaPelajaran.setText(nama_pelajaran);
        tvNamaPengajar.setText("Pemilik Kelas : " + nama_pengajar);
        tvHargaFee.setText("FEE : Rp " + harga_fee);
        tvHargaSpp.setText("SPP : " + harga_spp);
        tvHari.setText("Hari : " + hari);
        tvJam.setText("Jam : " + jam_mulai + " - " + jam_berakhir);

        hari_kelas = hari;

        harga_fee_m = harga_fee;
        harga_spp_m = harga_spp;

        if (hakAkses.equals("pengajar")) {
            tvHargaSpp.setVisibility(View.GONE);
        }
        if (hakAkses.equals("wali_murid")) {
            tvHargaFee.setVisibility(View.GONE);
        }

        if (!id_sharing.equals("null")) {
            tvStatus.setText("Status : Dibagikan Kepada " + nama_sharing);
            btnSharing.setVisibility(View.GONE);
            btnDeleteSharing.setVisibility(View.VISIBLE);
        } else {
            tvStatus.setText("Status : Tidak Sedang Dibagikan");
            btnSharing.setVisibility(View.VISIBLE);
            btnDeleteSharing.setVisibility(View.GONE);
        }

        sembunyikanObject();
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
    public void showDialogMulai() {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                this);
        alertDialogBuilder.setTitle("Yakin Ingin Memulai Kelas Pertemuan ?");
        alertDialogBuilder
                .setMessage("Klik Ya untuk Mulai !")
                .setPositiveButton("Ya", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {

                        try {

                            HashMap<String, String> user = sessionManager.getDataUser();
                            String id_pengajar_m = user.get(sessionManager.ID_USER);
                            String id_kelas_p_m = id_kelas_p;

                            getLocation();
                            getLocation();
                            String lokasi_mulai_la_m = String.valueOf(loc.getLatitude());
                            String lokasi_mulai_lo_m = String.valueOf(loc.getLongitude());

                            onSuccessMessage(harga_fee_m);

                            adminKelasDetailKelasPresenter.onMulaiPertemuan(id_pengajar_m, id_kelas_p_m, lokasi_mulai_la_m, lokasi_mulai_lo_m, hari_kelas, harga_fee_m, harga_spp_m);

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
        ActivityCompat.requestPermissions(AdminKelasDetailKelasActivity.this, permissions_all, PERMISSION_CODE);
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

                locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 1000 * 60 * 1, 10, AdminKelasDetailKelasActivity.this);

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

                locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 1000 * 60 * 1, 10, AdminKelasDetailKelasActivity.this);

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
    public void backPressed() {
        onResume();
    }

    @Override
    public void onSetupListView(ArrayList<Murid> dataModelArrayList) {

        sembunyikanObject();

        adapterDaftarKelasMurid = new AdapterDaftarKelasMurid(this, dataModelArrayList);
        adapterPengajarDaftarKelasMurid = new AdapterPengajarDaftarKelasMurid(this, dataModelArrayList);

        GridLayoutManager layoutManager = new GridLayoutManager(this, 1, GridLayoutManager.VERTICAL, false);

        if (statusUser.equals("pengajar")) {
            recyclerView.setAdapter(adapterPengajarDaftarKelasMurid);
        } else {
            recyclerView.setAdapter(adapterDaftarKelasMurid);
        }

        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setNestedScrollingEnabled(true);

        adapterDaftarKelasMurid.setOnItemClickListener(new AdapterDaftarKelasMurid.ClickListener() {
            @Override
            public void onClick(View view, int position) {
                Intent intent = new Intent(getApplicationContext(), AdminKelasDetailMuridDetailActivity.class);
                intent.putExtra(AdminKelasDetailMuridDetailActivity.EXTRA_ID_MURID, dataModelArrayList.get(position).getId_murid());
                intent.putExtra(AdminKelasDetailMuridDetailActivity.EXTRA_ID_WALI_MURID, dataModelArrayList.get(position).getId_wali_murid());
                intent.putExtra(AdminKelasDetailMuridDetailActivity.EXTRA_NAMA, dataModelArrayList.get(position).getNama());
                intent.putExtra(AdminKelasDetailMuridDetailActivity.EXTRA_NAMA_WALI_MURID, dataModelArrayList.get(position).getNama_wali_murid());
                intent.putExtra(AdminKelasDetailMuridDetailActivity.EXTRA_ALAMAT, dataModelArrayList.get(position).getAlamat());
                intent.putExtra(AdminKelasDetailMuridDetailActivity.EXTRA_FOTO, dataModelArrayList.get(position).getFoto());
                startActivity(intent);
            }
        });

        adapterPengajarDaftarKelasMurid.setOnItemClickListener(new AdapterPengajarDaftarKelasMurid.ClickListener() {
            @Override
            public void onClick(View view, int position) {
                Intent intent = new Intent(getApplicationContext(), AdminKelasDetailMuridDetailActivity.class);
                intent.putExtra(AdminKelasDetailMuridDetailActivity.EXTRA_ID_MURID, dataModelArrayList.get(position).getId_murid());
                intent.putExtra(AdminKelasDetailMuridDetailActivity.EXTRA_ID_WALI_MURID, dataModelArrayList.get(position).getId_wali_murid());
                intent.putExtra(AdminKelasDetailMuridDetailActivity.EXTRA_NAMA, dataModelArrayList.get(position).getNama());
                intent.putExtra(AdminKelasDetailMuridDetailActivity.EXTRA_NAMA_WALI_MURID, dataModelArrayList.get(position).getNama_wali_murid());
                intent.putExtra(AdminKelasDetailMuridDetailActivity.EXTRA_ALAMAT, dataModelArrayList.get(position).getAlamat());
                intent.putExtra(AdminKelasDetailMuridDetailActivity.EXTRA_FOTO, dataModelArrayList.get(position).getFoto());
                startActivity(intent);
            }
        });
    }

    @Override
    public void sembunyikanObject() {
        if (statusUser.equals("pengajar")) {
            btnAbsen.setVisibility(View.VISIBLE);

            tvStatus.setVisibility(View.GONE);
            btnDeleteSharing.setVisibility(View.GONE);
            btnSharing.setVisibility(View.GONE);
            fab.hide();
        }
    }

    @Override
    public void keHalamanAbsensi(String id_pertemuan) {
        Intent intent = new Intent(getApplicationContext(), PengajarAbsensiPertemuanActivity.class);
        intent.putExtra(PengajarAbsensiPertemuanActivity.EXTRA_ID_PERTEMUAN, id_pertemuan);

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
        adminKelasDetailKelasPresenter.onLoadSemuaData(id_kelas_p);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.action_bar_form_edit_2, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {

            case android.R.id.home:
                onBackPressed();
                break;
            case R.id.menu_edit:



                break;
        }

        return super.onOptionsItemSelected(item);
    }
}
