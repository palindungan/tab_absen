package com.example.tababsensiapp.Activities.Admin.Home;

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
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tababsensiapp.Activities.Admin.AkunSaya.AdminAkunSayaActivity;
import com.example.tababsensiapp.Activities.Admin.Home.View.IAdminHomeView;
import com.example.tababsensiapp.Activities.Admin.Home.presenter.AdminHomePresenter;
import com.example.tababsensiapp.Activities.Admin.Home.presenter.IAdminHomePresenter;
import com.example.tababsensiapp.Activities.Admin.Kelas.Tampil.Pengajar.AdminKelasTampilPengajarActivity;
import com.example.tababsensiapp.Activities.Admin.MataPelajaran.Tampil.AdminMataPelajaranTampilActivity;
import com.example.tababsensiapp.Activities.Admin.Murid.Tampil.AdminMuridTampilActivity;
import com.example.tababsensiapp.Activities.Admin.Pengajar.Tampil.AdminPengajarTampilActivity;
import com.example.tababsensiapp.Activities.Admin.WaliMurid.Tampil.AdminWaliMuridTampilActivity;
import com.example.tababsensiapp.Controllers.SessionManager;
import com.example.tababsensiapp.R;
import com.google.android.material.navigation.NavigationView;
import com.nex3z.notificationbadge.NotificationBadge;

import es.dmoral.toasty.Toasty;

public class AdminHomeActivity extends AppCompatActivity implements View.OnClickListener, IAdminHomeView {

    IAdminHomePresenter adminHomePresenter;
    SessionManager sessionManager;

    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle actionBarDrawerToggle;
    private NavigationView navigationView;
    NotificationBadge badge;
    ImageView notificationIcon;
    TextView txtJmlPengajar, txtJmlMurid, txtJmlWaliMurid, txtJmlMataPelajaran;
    CardView linkAdminPengajar, linkAdminMurid, linkAdminWaliMurid, linkAdminMataPelajaran, linkAdminKelas, linkAdminKelasAktif;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_home);

        adminHomePresenter = new AdminHomePresenter(this, this);
        sessionManager = new SessionManager(this);

        drawerLayout = findViewById(R.id.drawerLayout_admin);
        navigationView = findViewById(R.id.navigation_view_admin);

        txtJmlPengajar = findViewById(R.id.txt_jml_pengajar);
        txtJmlMurid = findViewById(R.id.txt_jml_murid);
        txtJmlWaliMurid = findViewById(R.id.txt_jml_wali_murid);
        txtJmlMataPelajaran = findViewById(R.id.txt_jml_mata_pelajaran);

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

        actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.Open, R.string.Close);
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();
                switch (id) {
                    case R.id.bayar_spp:
                        Toast.makeText(getApplicationContext(), "SPP", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.gaji_pengajar:
                        Toast.makeText(getApplicationContext(), "Gaji", Toast.LENGTH_SHORT).show();
                        break;
                    default:
                        return true;
                }
                return true;
            }
        });

        adminHomePresenter.onCount();
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.link_admin_pengajar) {
            startActivity(new Intent(getApplicationContext(), AdminPengajarTampilActivity.class));
        }
        if (v.getId() == R.id.link_admin_murid) {
             startActivity(new Intent(getApplicationContext(), AdminMuridTampilActivity.class));
        }
        if (v.getId() == R.id.link_admin_wali_murid) {
            startActivity(new Intent(getApplicationContext(), AdminWaliMuridTampilActivity.class));
        }
        if (v.getId() == R.id.link_admin_mata_pelajaran) {
            startActivity(new Intent(getApplicationContext(), AdminMataPelajaranTampilActivity.class));
        }
        if (v.getId() == R.id.link_admin_kelas) {
             startActivity(new Intent(getApplicationContext(), AdminKelasTampilPengajarActivity.class));
        }
        if (v.getId() == R.id.link_admin_kelas_aktif) {
            // startActivity(new Intent(getApplicationContext(), HalamanListPengajarActivity.class));
        }
    }

    @Override
    public void setCountData(String pengajar, String murid, String waliMurid, String mataPelajaran, String kelas, String kelasAktif) {
        txtJmlPengajar.setText(pengajar);
        txtJmlMurid.setText(murid);
        txtJmlWaliMurid.setText(waliMurid);
        txtJmlMataPelajaran.setText(mataPelajaran);
    }

    @Override
    public void showDialog() {
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
    public void onSuccessMessage(String message) {
        Toasty.success(this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onErrorMessage(String message) {
        Toasty.error(this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.action_bar_home_admin, menu);

        View view = menu.findItem(R.id.menu_notification).getActionView();
        badge = view.findViewById(R.id.badge);
        notificationIcon = view.findViewById(R.id.notification_icon);

        notificationIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // startActivity(new Intent(getApplicationContext(), HalamanListNotificationActivity.class));
            }
        });

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        if (actionBarDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
        if (id == R.id.menu_notification) {
            return true;
        }
        if (id == R.id.menu_akun_saya) {
            startActivity(new Intent(getApplicationContext(), AdminAkunSayaActivity.class));
            return true;
        }
        if (id == R.id.menu_tentang_kami) {
            Toast.makeText(getApplicationContext(), "tentang kami", Toast.LENGTH_SHORT).show();
            return true;
        }
        if (id == R.id.menu_keluar) {
            showDialog();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onResume() {
        super.onResume();
        adminHomePresenter.onCount();
    }
}
