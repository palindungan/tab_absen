package com.its.bigstars.Activities.Home.Admin;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.google.android.material.navigation.NavigationView;
import com.its.bigstars.Activities.Home.Admin.presenter.HomeAdminPresenter;
import com.its.bigstars.Activities.Home.Admin.presenter.IHomeAdminPresenter;
import com.its.bigstars.Activities.Home.Admin.view.IHomeAdminView;
import com.its.bigstars.Controllers.SessionManager;
import com.its.bigstars.R;

public class HomeAdminActivity extends AppCompatActivity implements View.OnClickListener, IHomeAdminView {

    IHomeAdminPresenter homeAdminPresenter;
    SessionManager sessionManager;

    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle actionBarDrawerToggle;
    private NavigationView navigationView;
    
    CardView linkAdminPengajar, linkAdminMurid, linkAdminWaliMurid, linkAdminMataPelajaran, linkAdminKelas, linkAdminKelasAktif;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_admin);

        homeAdminPresenter = new HomeAdminPresenter(this, this);
        sessionManager = new SessionManager(this);

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

    @Override
    public void onClick(View v) {

    }
}
