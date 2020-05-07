package com.its.bigstars.Activities.Home.Pengajar;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.its.bigstars.Controllers.SessionManager;
import com.its.bigstars.Controllers.ToastMessage;
import com.its.bigstars.R;

import java.util.HashMap;

public class HomePengajarActivity extends AppCompatActivity implements View.OnClickListener {

    SessionManager sessionManager;
    ToastMessage toastMessage;

    CardView linkPengajarKelasTampilSemua, linkPengajarKelastampilAktif, linkpengajarRiwayatAbsen;

    String id_pengajar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_pengajar);

        sessionManager = new SessionManager(this);
        toastMessage = new ToastMessage(this);

        HashMap<String, String> user = sessionManager.getDataUser();
        id_pengajar = user.get(sessionManager.ID_USER);

        linkPengajarKelasTampilSemua = findViewById(R.id.link_pengajar_kelas_tampil_semua);
        linkPengajarKelastampilAktif = findViewById(R.id.link_pengajar_kelas_tampil_aktif);
        linkpengajarRiwayatAbsen = findViewById(R.id.link_pengajar_riwayat_absen);

        linkPengajarKelasTampilSemua.setOnClickListener(this);
        linkPengajarKelastampilAktif.setOnClickListener(this);
        linkpengajarRiwayatAbsen.setOnClickListener(this);
    }

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
        if (v.getId() == R.id.link_pengajar_kelas_tampil_semua) {
//            Intent intent = new Intent(getApplicationContext(), PengajarKelasTampilSemuaActivity.class);
//            startActivity(intent);
        }
        if (v.getId() == R.id.link_pengajar_kelas_tampil_aktif) {
//            Intent intent = new Intent(getApplicationContext(), PengajarKelasTampilAktifActivity.class);
//            intent.putExtra(PengajarKelasTampilAktifActivity.EXTRA_ID_PENGAJAR, id_pengajar);
//            startActivity(intent);
        }
        if (v.getId() == R.id.link_pengajar_riwayat_absen) {
//            Intent intent = new Intent(getApplicationContext(), PengajarRiwayatAbsenActivity.class);
//            intent.putExtra(PengajarRiwayatAbsenActivity.EXTRA_ID_PENGAJAR, id_pengajar);
//            startActivity(intent);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.action_bar_home_pengajar, menu);
        return true;
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
