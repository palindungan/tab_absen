package com.example.tababsenapp.Fitur.HalamanHome.Admin;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.tababsenapp.Fitur.HalamanHome.Admin.presenter.HomeAdminPresenter;
import com.example.tababsenapp.Fitur.HalamanHome.Admin.presenter.IHomeAdminPresenter;
import com.example.tababsenapp.Fitur.HalamanHome.Admin.view.IHomeAdminView;
import com.example.tababsenapp.Fitur.HalamanListNotification.HalamanListNotificationActivity;
import com.example.tababsenapp.R;
import com.google.android.material.navigation.NavigationView;
import com.nex3z.notificationbadge.NotificationBadge;

public class HalamanHomeAdminActivity extends AppCompatActivity implements IHomeAdminView {

    IHomeAdminPresenter homeAdminPresenter;

    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle actionBarDrawerToggle;
    private NavigationView navigationView;

    NotificationBadge badge;
    ImageView notificationIcon;

    CardView linkAdminPengajar, linkAdminMurid, linkAdminWaliMurid, linkAdminMataPelajaran, linkAdminKelas, linkAdminKelasAktif;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_halaman_home_admin);

        homeAdminPresenter = new HomeAdminPresenter(this, this);

        drawerLayout = findViewById(R.id.drawerLayout_admin);
        navigationView = findViewById(R.id.navigation_view_admin);

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
                        Toast.makeText(HalamanHomeAdminActivity.this, "SPP", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.gaji_pengajar:
                        Toast.makeText(HalamanHomeAdminActivity.this, "Gaji", Toast.LENGTH_SHORT).show();
                        break;
                    default:
                        return true;
                }
                return true;
            }
        });

        linkAdminPengajar = findViewById(R.id.link_admin_pengajar);
        linkAdminMurid = findViewById(R.id.link_admin_murid);
        linkAdminWaliMurid = findViewById(R.id.link_admin_wali_murid);
        linkAdminMataPelajaran = findViewById(R.id.link_admin_mata_pelajaran);
        linkAdminKelas = findViewById(R.id.link_admin_kelas);
        linkAdminKelasAktif = findViewById(R.id.link_admin_kelas_aktif);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.action_bar_menu_admin, menu);

        View view = menu.findItem(R.id.menu_notification).getActionView();
        badge = view.findViewById(R.id.badge);
        notificationIcon = view.findViewById(R.id.notification_icon);

        notificationIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HalamanHomeAdminActivity.this, HalamanListNotificationActivity.class));
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
            Toast.makeText(HalamanHomeAdminActivity.this, "akun saya", Toast.LENGTH_SHORT).show();
            return true;
        }
        if (id == R.id.menu_tentang_kami) {
            Toast.makeText(HalamanHomeAdminActivity.this, "tentang kami", Toast.LENGTH_SHORT).show();
            return true;
        }
        if (id == R.id.menu_keluar) {
            Toast.makeText(HalamanHomeAdminActivity.this, "Keluar", Toast.LENGTH_SHORT).show();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
