package com.example.tababsenapp.Fitur.HalamanHome.Admin;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.tababsenapp.Fitur.HalamanHome.Admin.presenter.HomeAdminPresenter;
import com.example.tababsenapp.Fitur.HalamanHome.Admin.presenter.IHomeAdminPresenter;
import com.example.tababsenapp.Fitur.HalamanHome.Admin.view.IHomeAdminView;
import com.example.tababsenapp.R;
import com.google.android.material.navigation.NavigationView;

public class HalamanHomeAdminActivity extends AppCompatActivity implements IHomeAdminView {

    IHomeAdminPresenter homeAdminPresenter;

    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle actionBarDrawerToggle;
    private NavigationView navigationView;

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
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (actionBarDrawerToggle.onOptionsItemSelected(item))
            return true;

        return super.onOptionsItemSelected(item);
    }
}
