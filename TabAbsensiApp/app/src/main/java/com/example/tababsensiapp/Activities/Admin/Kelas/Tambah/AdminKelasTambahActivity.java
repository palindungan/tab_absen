package com.example.tababsensiapp.Activities.Admin.Kelas.Tambah;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.tababsensiapp.Activities.Admin.Kelas.Tambah.presenter.AdminKelasTambahPresenter;
import com.example.tababsensiapp.Activities.Admin.Kelas.Tambah.presenter.IAdminKelasTambahPresenter;
import com.example.tababsensiapp.Activities.Admin.Kelas.Tambah.view.IAdminKelasTambahView;
import com.example.tababsensiapp.Adapters.AdapterDaftarMataPelajaran;
import com.example.tababsensiapp.Controllers.SessionManager;
import com.example.tababsensiapp.Models.MataPelajaran;
import com.example.tababsensiapp.R;

import java.util.ArrayList;
import java.util.Calendar;

import es.dmoral.toasty.Toasty;

public class AdminKelasTambahActivity extends AppCompatActivity implements View.OnClickListener, IAdminKelasTambahView {

    private AdapterDaftarMataPelajaran adapterDaftarMataPelajaran;
    private RecyclerView recyclerView;

    IAdminKelasTambahPresenter adminKelasTambahPresenter;
    SessionManager sessionManager;

    public static final String EXTRA_ID_PENGAJAR = "EXTRA_ID_PENGAJAR";
    String id_pengajar = "";
    String id_mata_pelajaran = "";

    Toolbar toolbar;
    EditText edtHari;
    TextView tvNamaPelajaran, tvJamMulai, tvJamBerakhir;
    EditText edtHargaFee,edtHargaSpp;
    Button btnSubmit;

    final Calendar c = Calendar.getInstance();
    int hour = c.get(Calendar.HOUR_OF_DAY);
    int minute = c.get(Calendar.MINUTE);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_kelas_tambah);

        id_pengajar = getIntent().getStringExtra(EXTRA_ID_PENGAJAR);

        adminKelasTambahPresenter = new AdminKelasTambahPresenter(this, this);
        adminKelasTambahPresenter.onLoadSemuaData();

        sessionManager = new SessionManager(this);

        recyclerView = findViewById(R.id.recycle_view);

        toolbar = findViewById(R.id.toolbar);
        initActionBar();

        tvNamaPelajaran = findViewById(R.id.tv_nama_pelajaran);
        edtHari = findViewById(R.id.edt_hari);
        tvJamMulai = findViewById(R.id.tv_jam_mulai);
        tvJamBerakhir = findViewById(R.id.tv_jam_berakhir);
        edtHargaFee = findViewById(R.id.edt_harga_fee);
        edtHargaSpp = findViewById(R.id.edt_harga_spp);
        btnSubmit = findViewById(R.id.btn_submit);

        btnSubmit.setOnClickListener(this);
        tvJamMulai.setOnClickListener(this);
        tvJamBerakhir.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btn_submit) {
            showDialog();
        }
        if (v.getId() == R.id.tv_jam_mulai) {
            showHoursPicker();
        }
        if (v.getId() == R.id.tv_jam_berakhir) {
            showHoursPicker2();
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
    public void onSetupListView(ArrayList<MataPelajaran> dataModelArrayList) {
        adapterDaftarMataPelajaran = new AdapterDaftarMataPelajaran(this, dataModelArrayList);
        GridLayoutManager layoutManager = new GridLayoutManager(this, 1, GridLayoutManager.VERTICAL, false);
        recyclerView.setAdapter(adapterDaftarMataPelajaran);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setNestedScrollingEnabled(true);
        adapterDaftarMataPelajaran.notifyDataSetChanged();

        adapterDaftarMataPelajaran.setOnItemClickListener(new AdapterDaftarMataPelajaran.ClickListener() {
            @Override
            public void onClick(View view, int position) {
                tvNamaPelajaran.setText(dataModelArrayList.get(position).getNama());
                id_mata_pelajaran = dataModelArrayList.get(position).getId_mata_pelajaran();
            }
        });
    }

    @Override
    public void onSubmitSuccess(String message) {
        Toasty.success(this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onSubmitError(String message) {
        Toasty.error(this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showDialog() {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                this);
        alertDialogBuilder.setTitle("Ingin Menambah Data Baru ?");
        alertDialogBuilder
                .setMessage("Klik Ya untuk melakukan input !")
                .setPositiveButton("Ya", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {

                        String inputIdMataPelajaran = id_mata_pelajaran;
                        String inputHari = edtHari.getText().toString().trim();
                        String inputJamMulai = tvJamMulai.getText().toString().trim();
                        String inputJamBerakhir = tvJamBerakhir.getText().toString().trim();
                        String inputHargaFee = edtHargaFee.getText().toString().trim();
                        String inputHargaSpp = edtHargaSpp.getText().toString().trim();

                        boolean isEmpty = false;

                        if (TextUtils.isEmpty(inputIdMataPelajaran)) {
                            isEmpty = true;
                            onSubmitError("Pilih Salah Satu Mata Pelajaran");
                        } else if (TextUtils.isEmpty(inputHari)) {
                            isEmpty = true;
                            onSubmitError("Isi Hari Kelas Pertemuan");
                        } else if (inputJamMulai.equals("Jam Mulai")) {
                            isEmpty = true;
                            onSubmitError("Isi Form Jam Mulai Kelas");
                        } else if (inputJamBerakhir.equals("Jam Berakhir")) {
                            isEmpty = true;
                            onSubmitError("Isi Form Jam Berakhir Kelas");
                        } else if (TextUtils.isEmpty(inputHargaFee)) {
                            isEmpty = true;
                            edtHargaFee.setError("Isi Data Dengan Lengkap");
                        }else if (TextUtils.isEmpty(inputHargaSpp)) {
                            isEmpty = true;
                            edtHargaFee.setError("Isi Data Dengan Lengkap");
                        }

                        try {

                            if (!isEmpty) {
                                adminKelasTambahPresenter.onSubmit(id_pengajar, id_mata_pelajaran, inputHari, inputJamMulai, inputJamBerakhir, inputHargaFee,inputHargaSpp);
                            }

                        } catch (Exception e) {
                            onSubmitError("Terjadi Kesalahan Submit " + e.toString());
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
    public void showHoursPicker() {
        TimePickerDialog timePickerDialog = new TimePickerDialog(AdminKelasTambahActivity.this,
                new TimePickerDialog.OnTimeSetListener() {

                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay,
                                          int minute) {

                        tvJamMulai.setText(hourOfDay + ":" + minute);
                    }
                }, hour, minute, true);
        timePickerDialog.show();
    }

    @Override
    public void showHoursPicker2() {
        TimePickerDialog timePickerDialog = new TimePickerDialog(AdminKelasTambahActivity.this,
                new TimePickerDialog.OnTimeSetListener() {

                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay,
                                          int minute) {

                        tvJamBerakhir.setText(hourOfDay + ":" + minute);
                    }
                }, hour, minute, true);
        timePickerDialog.show();
    }

    @Override
    public void backPressed() {
        onBackPressed();
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
