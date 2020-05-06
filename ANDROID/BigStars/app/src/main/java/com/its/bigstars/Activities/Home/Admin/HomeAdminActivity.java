package com.its.bigstars.Activities.Home.Admin;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.google.android.material.navigation.NavigationView;
import com.its.bigstars.Activities.Akun.Admin.AkunAdminActivity;
import com.its.bigstars.Activities.Data.MataPelajaran.List.DataMataPelajaranListActivity;
import com.its.bigstars.Activities.Data.Murid.List.DataMuridListActivity;
import com.its.bigstars.Activities.Data.Pengajar.List.DataPengajarListActivity;
import com.its.bigstars.Activities.Data.WaliMurid.List.DataWaliMuridListActivity;
import com.its.bigstars.Controllers.SessionManager;
import com.its.bigstars.Controllers.ToastMessage;
import com.its.bigstars.R;

public class HomeAdminActivity extends AppCompatActivity implements View.OnClickListener {

    SessionManager sessionManager;
    ToastMessage toastMessage;

    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle actionBarDrawerToggle;
    private NavigationView navigationView;

    CardView linkAdminPengajar, linkAdminMurid, linkAdminWaliMurid, linkAdminMataPelajaran, linkAdminKelas, linkAdminKelasAktif;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_admin);

        sessionManager = new SessionManager(this);
        toastMessage = new ToastMessage(this);

        drawerLayout = findViewById(R.id.drawerLayout_admin);
        actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.Open, R.string.Close);
        navigationView = findViewById(R.id.navigation_view_admin);

        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();
                // Intent intent = new Intent();
                switch (id) {
                    case R.id.monitoring:
                        // intent = new Intent(getApplicationContext(), AdminPengajarTampilActivity.class);
                        // intent.putExtra(AdminPengajarTampilActivity.EXTRA_STATUS_ACTIVITY, "to_monitoring");
                        break;
                    case R.id.bayar_spp:
                        // intent = new Intent(getApplicationContext(), AdminWaliMuridTampilActivity.class);
                        // intent.putExtra(AdminWaliMuridTampilActivity.EXTRA_STATUS_ACTIVITY, "to_transaksi_spp");
                        break;
                    case R.id.riwayat_bayar_spp:
                        // intent = new Intent(getApplicationContext(), AdminWaliMuridTampilActivity.class);
                        // intent.putExtra(AdminWaliMuridTampilActivity.EXTRA_STATUS_ACTIVITY, "to_riwayat_spp");
                        break;
                    case R.id.gaji_pengajar:
                        // intent = new Intent(getApplicationContext(), AdminPengajarTampilActivity.class);
                        // intent.putExtra(AdminPengajarTampilActivity.EXTRA_STATUS_ACTIVITY, "to_transaksi_gaji");
                        break;
                    case R.id.riwayat_penggajian:
                        // intent = new Intent(getApplicationContext(), AdminPengajarTampilActivity.class);
                        // intent.putExtra(AdminPengajarTampilActivity.EXTRA_STATUS_ACTIVITY, "to_riwayat_gaji");
                        break;
                    default:
                        return true;
                }
                // startActivity(intent);
                return true;
            }
        });

        linkAdminPengajar = findViewById(R.id.link_admin_pengajar);
        linkAdminMurid = findViewById(R.id.link_admin_murid);
        linkAdminWaliMurid = findViewById(R.id.link_admin_wali_murid);
        linkAdminMataPelajaran = findViewById(R.id.link_admin_mata_pelajaran);
        linkAdminKelas = findViewById(R.id.link_admin_kelas);
        linkAdminKelasAktif = findViewById(R.id.link_admin_kelas_aktif);

        linkAdminPengajar.setOnClickListener(this);
        linkAdminMurid.setOnClickListener(this);
        linkAdminWaliMurid.setOnClickListener(this);
        linkAdminMataPelajaran.setOnClickListener(this);
        linkAdminKelas.setOnClickListener(this);
        linkAdminKelasAktif.setOnClickListener(this);
    }

    private void showDialog() {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                this);
        alertDialogBuilder.setTitle("Ingin Logout ?");
        alertDialogBuilder
                .setMessage("Klik Ya untuk Keluar Aplikasi !")
                .setPositiveButton("Ya", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {

                        try {
                            sessionManager.logout();
                        } catch (Exception e) {
                            toastMessage.onErrorMessage("Terjadi Kesalahan " + e.toString());
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
    public void onClick(View v) {
        Intent intent;
        if (v.getId() == R.id.link_admin_pengajar) {
            sessionManager.setStatusActivity("home->view->editPengajar");
            intent = new Intent(getApplicationContext(), DataPengajarListActivity.class);
            startActivity(intent);
        } else if (v.getId() == R.id.link_admin_murid) {
            sessionManager.setStatusActivity("home->view->editMurid");
            intent = new Intent(getApplicationContext(), DataMuridListActivity.class);
            startActivity(intent);
        } else if (v.getId() == R.id.link_admin_wali_murid) {
            sessionManager.setStatusActivity("home->view->editWaliMurid");
            intent = new Intent(getApplicationContext(), DataWaliMuridListActivity.class);
            startActivity(intent);
        } else if (v.getId() == R.id.link_admin_mata_pelajaran) {
            sessionManager.setStatusActivity("home->view->editMataPelajaran");
            intent = new Intent(getApplicationContext(), DataMataPelajaranListActivity.class);
            startActivity(intent);
        } else if (v.getId() == R.id.link_admin_kelas) {
//            onSuccessMessage("Pilih Pengajar");
//            startActivity(new Intent(getApplicationContext(), AdminKelasTampilPengajarActivity.class));
        } else if (v.getId() == R.id.link_admin_kelas_aktif) {
//            Intent intent = new Intent(getApplicationContext(), PengajarKelasTampilAktifActivity.class);
//            String id_pengajar = "Semua";
//            intent.putExtra(PengajarKelasTampilAktifActivity.EXTRA_ID_PENGAJAR, id_pengajar);
//            startActivity(intent);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.action_bar_home_admin, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();
        Intent intent;

        if (actionBarDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }

        if (id == R.id.menu_riwayat) {
//            String id_pengajar = "Semua";
//            intent = new Intent(getApplicationContext(), PengajarRiwayatAbsenActivity.class);
//            intent.putExtra(PengajarRiwayatAbsenActivity.EXTRA_ID_PENGAJAR, id_pengajar);
//            startActivity(intent);
            return true;
        } else if (id == R.id.menu_akun_saya) {
            intent = new Intent(getApplicationContext(), AkunAdminActivity.class);
            startActivity(intent);
            return true;
        } else if (id == R.id.menu_keluar) {
            showDialog();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onResume() {
        super.onResume();
    }
}
