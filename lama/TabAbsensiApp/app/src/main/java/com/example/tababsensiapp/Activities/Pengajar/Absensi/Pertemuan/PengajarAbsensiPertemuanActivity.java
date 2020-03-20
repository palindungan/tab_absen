package com.example.tababsensiapp.Activities.Pengajar.Absensi.Pertemuan;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.DialogInterface;
import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tababsensiapp.Activities.Pengajar.Absensi.NextStep.PengajarAbsensiNextStepActivity;
import com.example.tababsensiapp.Activities.Pengajar.Absensi.Pertemuan.presenter.IPengajarAbsensiPertemuanPresenter;
import com.example.tababsensiapp.Activities.Pengajar.Absensi.Pertemuan.presenter.PengajarAbsensiPertemuanPresenter;
import com.example.tababsensiapp.Activities.Pengajar.Absensi.Pertemuan.view.IPengajarAbsensiPertemuanView;
import com.example.tababsensiapp.Activities.Pengajar.Home.PengajarHomeActivity;
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

public class PengajarAbsensiPertemuanActivity extends AppCompatActivity implements View.OnClickListener, IPengajarAbsensiPertemuanView, LocationListener {

    Toolbar toolbar;

    TextView tvNamaPelajaran, tvNamaPengajar, tvDetailKelasP, tvWaktuDetailMulai, tvWaktuDetailBerakhir, tvLatitude, tvLongitude, tvStatus, tvHargaFee, tvHargaSpp;
    Button btnBatal, btnNext, btnHapus, btnValidasi;

    SupportMapFragment mapFragment;
    GoogleMap map;
    LatLng latLng;

    public static final String EXTRA_ID_PERTEMUAN = "EXTRA_ID_PERTEMUAN";
    String id_pertemuan = "";

    String status_pertemuan = "";
    String deskripsi = "";
    String lokasi_berakhir_la = "";
    String lokasi_berakhir_lo = "";

    IPengajarAbsensiPertemuanPresenter pengajarAbsensiPertemuanPresenter;

    SessionManager sessionManager;
    String hakAkses = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pengajar_absensi_pertemuan);

        sessionManager = new SessionManager(this);

        HashMap<String, String> user = sessionManager.getDataUser();
        hakAkses = user.get(sessionManager.HAK_AKSES);

        id_pertemuan = getIntent().getStringExtra(EXTRA_ID_PERTEMUAN);

        pengajarAbsensiPertemuanPresenter = new PengajarAbsensiPertemuanPresenter(this, this);
        pengajarAbsensiPertemuanPresenter.inisiasiAwal(id_pertemuan);

        tvNamaPelajaran = findViewById(R.id.tv_nama_pelajaran);
        tvNamaPengajar = findViewById(R.id.tv_nama_pengajar);
        tvDetailKelasP = findViewById(R.id.tv_detail_kelas_p);
        tvHargaFee = findViewById(R.id.tv_harga_fee);
        tvHargaSpp = findViewById(R.id.tv_harga_spp);
        tvWaktuDetailMulai = findViewById(R.id.tv_waktu_detail_mulai);
        tvWaktuDetailBerakhir = findViewById(R.id.tv_waktu_detail_berakhir);
        tvLatitude = findViewById(R.id.tv_latitude);
        tvLongitude = findViewById(R.id.tv_longitude);
        tvStatus = findViewById(R.id.tv_status);

        btnBatal = findViewById(R.id.btn_batal);
        btnNext = findViewById(R.id.btn_next);
        btnHapus = findViewById(R.id.btn_hapus);
        btnValidasi = findViewById(R.id.btn_validasi);

        toolbar = findViewById(R.id.toolbar);

        initActionBar();

        if (!hakAkses.equals("admin")) {
            tvHargaSpp.setVisibility(View.GONE);
        }

        btnBatal.setOnClickListener(this);
        btnNext.setOnClickListener(this);
        btnHapus.setOnClickListener(this);
        btnValidasi.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btn_batal) {
            showDialogDelete();
        }
        if (v.getId() == R.id.btn_next) {
            Intent intent = new Intent(getApplicationContext(), PengajarAbsensiNextStepActivity.class);
            intent.putExtra(PengajarAbsensiNextStepActivity.EXTRA_ID_PERTEMUAN, id_pertemuan);
            intent.putExtra(PengajarAbsensiNextStepActivity.EXTRA_STATUS_PERTEMUAN, status_pertemuan);
            intent.putExtra(PengajarAbsensiNextStepActivity.EXTRA_DESKRIPSI, deskripsi);
            intent.putExtra(PengajarAbsensiNextStepActivity.EXTRA_LOKASI_BERAKHIR_LA, lokasi_berakhir_la);
            intent.putExtra(PengajarAbsensiNextStepActivity.EXTRA_LOKASI_BERAKHIR_LO, lokasi_berakhir_lo);
            startActivity(intent);
        }
        if (v.getId() == R.id.btn_hapus) {
            showDialogDelete();
        }
        if (v.getId() == R.id.btn_validasi) {
            showDialogValidasi();
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
        String waktu_berakhir = data.get("waktu_berakhir");
        String lokasi_mulai_la = data.get("lokasi_mulai_la");
        String lokasi_mulai_lo = data.get("lokasi_mulai_lo");

        String hari_jadwal = data.get("hari_jadwal");
        String jam_mulai = data.get("jam_mulai");
        String jam_berakhir = data.get("jam_berakhir");
        String harga_fee = data.get("harga_fee");
        String harga_spp = data.get("harga_spp");

        status_pertemuan = data.get("status_pertemuan");
        String status_konfirmasi = data.get("status_konfirmasi");

        deskripsi = data.get("deskripsi");

        lokasi_berakhir_la = data.get("lokasi_berakhir_la");
        lokasi_berakhir_lo = data.get("lokasi_berakhir_lo");

        if (status_pertemuan.equals("Selesai")) {
            btnBatal.setVisibility(View.GONE);
            tvWaktuDetailBerakhir.setVisibility(View.VISIBLE);

            if (hakAkses.equals("admin")) {
                btnHapus.setVisibility(View.VISIBLE);
                btnValidasi.setVisibility(View.VISIBLE);
            }

        } else if (status_pertemuan.equals("Belum Selesai") && hakAkses.equals("admin")) {
            btnBatal.setVisibility(View.GONE);
            btnNext.setVisibility(View.GONE);
        }

        tvNamaPelajaran.setText(nama_mata_pelajaran);
        tvDetailKelasP.setText("(" + hari_jadwal + ", " + jam_mulai + " - " + jam_berakhir + ")");
        tvNamaPengajar.setText("Nama Pengajar : " + nama_pengajar);
        tvHargaFee.setText("Harga Fee : " + harga_fee);
        tvHargaSpp.setText("Harga Spp : " + harga_spp);
        tvWaktuDetailMulai.setText("Dimulai : " + hari_btn + ", " + waktu_mulai);
        tvWaktuDetailBerakhir.setText("Berakhir : " + hari_btn + ", " + waktu_berakhir);
        tvLatitude.setText("Latitude : (" + lokasi_mulai_la + ")");
        tvLongitude.setText("Longitude : (" + lokasi_mulai_lo + ")");
        tvStatus.setText("Status Konfirmasi : " + status_konfirmasi);


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
                map.addMarker(new MarkerOptions().position(latLng).title("Lokasi Absen"));
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
                            onErrorMessage("Terjadi Kesalahan Validasi " + e.toString());
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
    public void showDialogValidasi() {
        androidx.appcompat.app.AlertDialog.Builder alertDialogBuilder = new androidx.appcompat.app.AlertDialog.Builder(
                this);
        alertDialogBuilder.setTitle("Yakin Ingin Mengkonfirmasi Pertemuan ?");
        alertDialogBuilder
                .setMessage("Klik Valid untuk Mengkonfirmasi Data !")
                .setPositiveButton("Valid", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {

                        try {
                            pengajarAbsensiPertemuanPresenter.onValidasi(id_pertemuan);
                        } catch (Exception e) {
                            onErrorMessage("Terjadi Kesalahan Validasi " + e.toString());
                        }

                    }
                })
                .setNegativeButton("Invalid", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        try {
                            pengajarAbsensiPertemuanPresenter.onInValidasi(id_pertemuan);
                        } catch (Exception e) {
                            onErrorMessage("Terjadi Kesalahan Validasi " + e.toString());
                        }
                    }
                });

        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }

    @Override
    public void backPressed() {

        if (hakAkses.equals("admin")) {
            onBackPressed();
        } else {
            Intent intent = new Intent(getApplicationContext(), PengajarHomeActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
        }
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
