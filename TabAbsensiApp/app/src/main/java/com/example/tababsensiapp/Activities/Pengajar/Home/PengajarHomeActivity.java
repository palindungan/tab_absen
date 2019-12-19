package com.example.tababsensiapp.Activities.Pengajar.Home;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.tababsensiapp.Activities.Pengajar.Home.presenter.IPengajarHomePresenter;
import com.example.tababsensiapp.Activities.Pengajar.Home.presenter.PengajarHomePresenter;
import com.example.tababsensiapp.Activities.Pengajar.Home.view.IPengajarHomeView;
import com.example.tababsensiapp.Activities.Pengajar.Kelas.TampilAktif.PengajarKelasTampilAktifActivity;
import com.example.tababsensiapp.Activities.Pengajar.Kelas.TampilSemua.PengajarKelasTampilSemuaActivity;
import com.example.tababsensiapp.Controllers.SessionManager;
import com.example.tababsensiapp.R;
import com.nex3z.notificationbadge.NotificationBadge;

import es.dmoral.toasty.Toasty;

public class PengajarHomeActivity extends AppCompatActivity implements View.OnClickListener, IPengajarHomeView {

    IPengajarHomePresenter pengajarHomePresenter;
    SessionManager sessionManager;

    CardView linkPengajarAbsensiPertemuan, linkPengajarKelasTampilSemua, linkPengajarKelastampilAktif;

    NotificationBadge badge;
    ImageView notificationIcon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pengajar_home);

        pengajarHomePresenter = new PengajarHomePresenter(this, this);
        sessionManager = new SessionManager(this);

        linkPengajarKelasTampilSemua = findViewById(R.id.link_pengajar_kelas_tampil_semua);
        linkPengajarKelastampilAktif = findViewById(R.id.link_pengajar_kelas_tampil_aktif);

        linkPengajarKelasTampilSemua.setOnClickListener(this);
        linkPengajarKelastampilAktif.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.link_pengajar_kelas_tampil_semua) {
            Intent intent = new Intent(getApplicationContext(), PengajarKelasTampilSemuaActivity.class);
            startActivity(intent);
        }
        if (v.getId() == R.id.link_pengajar_kelas_tampil_aktif) {
            Intent intent = new Intent(getApplicationContext(), PengajarKelasTampilAktifActivity.class);
            startActivity(intent);
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
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.action_bar_home_admin, menu);

        View view = menu.findItem(R.id.menu_notification).getActionView();
        badge = view.findViewById(R.id.badge);
        notificationIcon = view.findViewById(R.id.notification_icon);

        notificationIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "notif", Toast.LENGTH_SHORT).show();
            }
        });

        return true;
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
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        if (id == R.id.menu_akun_saya) {
            Toast.makeText(getApplicationContext(), "akun saya", Toast.LENGTH_SHORT).show();
//            startActivity(new Intent(getApplicationContext(), AdminAkunSayaActivity.class));
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
}
