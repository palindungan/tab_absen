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
import com.example.tababsensiapp.Activities.Pengajar.Riwayat.Absen.PengajarRiwayatAbsenActivity;
import com.example.tababsensiapp.Controllers.SessionManager;
import com.example.tababsensiapp.R;
import com.nex3z.notificationbadge.NotificationBadge;

import java.util.HashMap;

import es.dmoral.toasty.Toasty;

public class PengajarHomeActivity extends AppCompatActivity implements View.OnClickListener, IPengajarHomeView {

    IPengajarHomePresenter pengajarHomePresenter;
    SessionManager sessionManager;

    CardView linkPengajarAbsensiPertemuan, linkPengajarKelasTampilSemua, linkPengajarKelastampilAktif, linkpengajarRiwayatAbsen;

    NotificationBadge badge;
    ImageView notificationIcon;

    String id_pengajar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pengajar_home);

        pengajarHomePresenter = new PengajarHomePresenter(this, this);
        sessionManager = new SessionManager(this);

        HashMap<String, String> user = sessionManager.getDataUser();
        id_pengajar = user.get(sessionManager.ID_USER);

        linkPengajarKelasTampilSemua = findViewById(R.id.link_pengajar_kelas_tampil_semua);
        linkPengajarKelastampilAktif = findViewById(R.id.link_pengajar_kelas_tampil_aktif);
        linkpengajarRiwayatAbsen = findViewById(R.id.link_pengajar_riwayat_absen);

        linkPengajarKelasTampilSemua.setOnClickListener(this);
        linkPengajarKelastampilAktif.setOnClickListener(this);
        linkpengajarRiwayatAbsen.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.link_pengajar_kelas_tampil_semua) {
            Intent intent = new Intent(getApplicationContext(), PengajarKelasTampilSemuaActivity.class);
            startActivity(intent);
        }
        if (v.getId() == R.id.link_pengajar_kelas_tampil_aktif) {
            Intent intent = new Intent(getApplicationContext(), PengajarKelasTampilAktifActivity.class);
            intent.putExtra(PengajarKelasTampilAktifActivity.EXTRA_ID_PENGAJAR, id_pengajar);
            startActivity(intent);
        }
        if (v.getId() == R.id.link_pengajar_riwayat_absen) {
            Intent intent = new Intent(getApplicationContext(), PengajarRiwayatAbsenActivity.class);
            intent.putExtra(PengajarRiwayatAbsenActivity.EXTRA_ID_PENGAJAR, id_pengajar);
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
        getMenuInflater().inflate(R.menu.action_bar_home_pengajar, menu);

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

        if (id == R.id.menu_keluar) {
            showDialog();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
